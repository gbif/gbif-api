package org.gbif.api.jackson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.gbif.api.model.occurrence.DownloadFormat;
import org.gbif.api.model.occurrence.DownloadRequest;
import org.gbif.api.model.occurrence.PredicateDownloadRequest;
import org.gbif.api.model.occurrence.SqlDownloadRequest;
import org.gbif.api.model.occurrence.predicate.Predicate;
import org.gbif.api.util.VocabularyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.base.Throwables;

/**
 * 
 * Download request deserializer.
 *
 */
public class DownloadRequestSerde extends JsonDeserializer<DownloadRequest> {

  private static final String PREDICATE = "predicate";
  private static final String SQL = "sql";
  private static final String SEND_NOTIFICATION = "send_notification";
  private static final String NOTIFICATION_ADDRESS = "notification_address";
  private static final String CREATOR = "creator";
  private static final String FORMAT = "format";
  private static final Logger LOG = LoggerFactory.getLogger(DownloadRequestSerde.class);
  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Override
  public DownloadRequest deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    JsonNode node = jp.getCodec().readTree(jp);
    LOG.info("DownloadRequest for deserialization: {}", node.toString());
    DownloadFormat format = Optional.ofNullable(node.get(FORMAT))
      .map(n -> VocabularyUtils.lookupEnum(n.asText(), DownloadFormat.class)).orElse(DownloadFormat.DWCA);
    String creator = Optional.ofNullable(node.get(CREATOR)).map(JsonNode::asText).orElse(null);

    List<String> notificationAddress = Optional.ofNullable(node.get(NOTIFICATION_ADDRESS)).map(jsonNode -> {
      try {
        return Arrays.asList(MAPPER.readValue(jsonNode, String[].class));
      } catch (Exception e) {
       throw Throwables.propagate(e);
      }
    }).orElse(new ArrayList<>());

    boolean sendNotification = Optional.ofNullable(node.get(SEND_NOTIFICATION)).map(JsonNode::asBoolean).orElse(Boolean.FALSE);

    if (DownloadFormat.SQL == format) {
      String sql = Optional.ofNullable(node.get(SQL)).map(JsonNode::asText).orElse(null);
      return new SqlDownloadRequest(sql, creator, notificationAddress, sendNotification);
    } else {
      JsonNode predicate = Optional.ofNullable(node.get(PREDICATE)).orElse(null);
      Predicate predicateObj = predicate == null ? null : MAPPER.readValue(predicate, Predicate.class);
      return new PredicateDownloadRequest(predicateObj, creator, notificationAddress, sendNotification, format);
    }
  }
}

