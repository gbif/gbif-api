package org.gbif.api.jackson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.gbif.api.model.occurrence.DownloadFormat;
import org.gbif.api.model.occurrence.DownloadRequest;
import org.gbif.api.model.occurrence.PredicateDownloadRequest;
import org.gbif.api.model.occurrence.predicate.Predicate;
import org.gbif.api.util.VocabularyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.base.Throwables;

/**
 * Download request deserializer.
 *
 * For most of the time, the serialization has been to "notificationAddresses" and "sendNotification".  For a few months
 * in 2018-2019, it was "notification_address" and "send_notification".
 *
 * The API documentation has previously specified "notification_address" and "sendNotification".
 *
 * We therefore accept all combinations.
 *
 * https://github.com/gbif/portal-feedback/issues/2046
 */
public class DownloadRequestSerde extends JsonDeserializer<DownloadRequest> {

  private static final String PREDICATE = "predicate";
  private static final List<String> SEND_NOTIFICATION = ImmutableList.of("sendNotification", "send_notification");
  private static final List<String> NOTIFICATION_ADDRESSES =
    ImmutableList.of("notificationAddresses", "notificationAddress", "notification_addresses", "notification_address");
  private static final String CREATOR = "creator";
  private static final String FORMAT = "format";
  private static final Logger LOG = LoggerFactory.getLogger(DownloadRequestSerde.class);
  private static final ObjectMapper MAPPER = new ObjectMapper();

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
    String creator = Optional.ofNullable(node.get(CREATOR)).map(JsonNode::asText).orElse(null);

    List<String> notificationAddresses = new ArrayList<>();
    for (final String jsonKey : NOTIFICATION_ADDRESSES) {
      notificationAddresses.addAll(Optional.ofNullable(node.get(jsonKey)).map(jsonNode -> {
        try {
          return Arrays.asList(MAPPER.readValue(jsonNode, String[].class));
        } catch (Exception e) {
          throw Throwables.propagate(e);
        }
      }).orElse(new ArrayList<>()));
    }

    boolean sendNotification = false;
    for (final String jsonKey : SEND_NOTIFICATION) {
      sendNotification |= Optional.ofNullable(node.get(jsonKey)).map(JsonNode::asBoolean).orElse(Boolean.FALSE);
    }
    JsonNode predicate = Optional.ofNullable(node.get(PREDICATE)).orElse(null);
    Predicate predicateObj = predicate == null ? null : MAPPER.readValue(predicate, Predicate.class);
    return new PredicateDownloadRequest(predicateObj, creator, notificationAddresses, sendNotification, format);
  }
}
