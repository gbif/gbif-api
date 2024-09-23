/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.api.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;

import org.gbif.api.model.occurrence.DownloadFormat;
import org.gbif.api.model.occurrence.DownloadRequest;
import org.gbif.api.model.occurrence.DownloadType;
import org.gbif.api.model.occurrence.PredicateDownloadRequest;
import org.gbif.api.model.occurrence.SqlDownloadRequest;
import org.gbif.api.model.occurrence.search.OccurrenceSearchParameter;
import org.gbif.api.model.predicate.Predicate;
import org.gbif.api.util.VocabularyUtils;
import org.gbif.api.vocabulary.Extension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Download request deserializer.
 * <p>
 * For most of the time, the serialization has been to "notificationAddresses" and
 * "sendNotification".  For a few months in 2018-2019, it was "notification_address" and
 * "send_notification".
 * <p>
 * The API documentation has previously specified "notification_address" and "sendNotification".
 * <p>
 * We therefore accept all combinations.
 * <p>
 * https://github.com/gbif/portal-feedback/issues/2046
 */
public class DownloadRequestSerde extends JsonDeserializer<DownloadRequest> {

  private static final String PREDICATE = "predicate";
  private static final List<String> SEND_NOTIFICATION = Collections
    .unmodifiableList(Arrays.asList("sendNotification", "send_notification"));
  private static final String SQL = "sql";
  private static final List<String> NOTIFICATION_ADDRESSES =
    Collections.unmodifiableList(
      Arrays.asList("notificationAddresses", "notificationAddress", "notification_addresses",
        "notification_address"));
  private static final String CREATOR = "creator";
  private static final String FORMAT = "format";
  private static final String TYPE = "type";
  private static final String VERBATIM_EXTENSIONS = "verbatimExtensions";

  // Properties we ignore.
  private static final List<String> IGNORED_PROPERTIES =
    Collections.unmodifiableList(
      Arrays.asList("created"));

  private static final Set<String> ALL_PROPERTIES;
  private static final Logger LOG = LoggerFactory.getLogger(DownloadRequestSerde.class);
  private static final ObjectMapper MAPPER = new ObjectMapper();

  static {
    Set<String> allProperties = new HashSet<>(Arrays.asList(PREDICATE, SQL, CREATOR, FORMAT, TYPE, VERBATIM_EXTENSIONS));
    allProperties.addAll(SEND_NOTIFICATION);
    allProperties.addAll(NOTIFICATION_ADDRESSES);
    allProperties.addAll(IGNORED_PROPERTIES);
    ALL_PROPERTIES = Collections.unmodifiableSet(allProperties);
  }

  @Override
  public DownloadRequest deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    JsonNode node = jp.getCodec().readTree(jp);
    LOG.debug("DownloadRequest for deserialization: {}", node);
    //at least one element must be defined
    if (node.size() == 0) {
      return null;
    }
    DownloadFormat format = Optional.ofNullable(node.get(FORMAT))
      .map(n -> VocabularyUtils.lookupEnum(n.asText(), DownloadFormat.class)).orElse(DownloadFormat.DWCA);

    DownloadType type = Optional.ofNullable(node.get(TYPE))
      .map(n -> VocabularyUtils.lookupEnum(n.asText(), DownloadType.class)).orElse(DownloadType.OCCURRENCE);

    String creator = Optional.ofNullable(node.get(CREATOR)).map(JsonNode::asText).orElse(null);

    List<String> notificationAddresses = new ArrayList<>();
    for (final String jsonKey : NOTIFICATION_ADDRESSES) {
      notificationAddresses.addAll(Optional.ofNullable(node.get(jsonKey)).map(jsonNode -> {
        try {
          return Arrays.asList(MAPPER.treeToValue(jsonNode, String[].class));
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }).orElse(new ArrayList<>()));
    }

    boolean sendNotification = false;
    for (final String jsonKey : SEND_NOTIFICATION) {
      sendNotification |= Optional.ofNullable(node.get(jsonKey)).map(JsonNode::asBoolean).orElse(Boolean.FALSE);
    }

    Set<Extension> extensions = Optional.ofNullable(node.get(VERBATIM_EXTENSIONS)).map(jsonNode -> {
      try {
        return Arrays.stream(MAPPER.treeToValue(jsonNode, String[].class))
                .map(Extension::fromRowType)
                .collect(Collectors.toSet());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }).orElse(Collections.emptySet());

    // Check requested extensions are available for download.
    for (Extension e : extensions) {
      if (!Extension.availableExtensions().contains(e)) {
        throw new RuntimeException("The "+e.getRowType()+" extension is not available for downloads.");
      }
    }

    String sql = Optional.ofNullable(node.get(SQL)).map(JsonNode::asText).orElse(null);

    // Reject if unknown field names are present
    // https://github.com/gbif/occurrence/issues/273
    node.fieldNames().forEachRemaining(n -> { if (!ALL_PROPERTIES.contains(n)) { throw new RuntimeException("Unknown JSON property '"+n+"'."); }});

    if (sql != null) {
      if (format != DownloadFormat.SQL_TSV_ZIP) {
        throw new RuntimeException("SQL downloads must use a suitable download format: SQL_TSV_ZIP.");
      }
      return new SqlDownloadRequest(sql, creator, notificationAddresses, sendNotification, type, format);
    } else {
      if (format == DownloadFormat.SQL_TSV_ZIP) {
        throw new RuntimeException("Predicate downloads must not use an SQL download format.");
      }
      JsonNode predicate = Optional.ofNullable(node.get(PREDICATE)).orElse(null);
      // Not yet enforced, we would need e.g. http://api.gbif.org/v1/occurrence/download/request/predicate?format=DWCA
      // to return 'predicate: {}' etc.
      //if (predicate == null) {
      //  throw new RuntimeException("A predicate must be specified. Use {} for everything.");
      //}
      MAPPER.registerModule(
          new SimpleModule().addDeserializer(
            OccurrenceSearchParameter.class,
            new OccurrenceSearchParameter.OccurrenceSearchParameterDeserializer()
          )
        );

      Predicate predicateObj = predicate == null ? null : MAPPER.treeToValue(predicate, Predicate.class);
      return new PredicateDownloadRequest(predicateObj, creator, notificationAddresses, sendNotification, format, type, extensions);
    }
  }
}
