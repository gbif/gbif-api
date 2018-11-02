package org.gbif.api.model.occurrence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.gbif.api.model.occurrence.predicate.Predicate;
import com.google.common.base.Throwables;

/**
 * 
 * Download request deserializer.
 *
 */
public class DownloadRequestDeserializer extends JsonDeserializer<DownloadRequest>{

  @Override
  public DownloadRequest deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    JsonNode node = jp.getCodec().readTree(jp);
    
    String format = Optional.ofNullable(node.get("format")).map(JsonNode::asText).orElse(DownloadFormat.DWCA.name());
    String creator = Optional.ofNullable(node.get("creator")).map(JsonNode::asText).orElseThrow(RuntimeException::new);
    List<String> notificationAddress = Optional.ofNullable(node.get("notification_address")).map( jsonNode -> {
      try {
        return Arrays.asList(new ObjectMapper().readValue(jsonNode, String[].class));
      } catch (Exception e) {
        Throwables.propagate(e);
      }
      return new ArrayList<String>();
    }).orElse(new ArrayList<>());
    boolean sendNotification = Optional.ofNullable(node.get("send_notification")).map(JsonNode::asBoolean).orElse(Boolean.FALSE);
    
    if(format.equals(DownloadFormat.SQL.name())) {
      String sql = Optional.ofNullable(node.get("sql")).map(JsonNode::asText).orElseThrow(RuntimeException::new);
      
      return new SqlDownloadRequest(sql, creator, notificationAddress, sendNotification);
    }
    else {
      JsonNode predicate = Optional.ofNullable(node.get("predicate")).orElse(null);
      Predicate predicateObj = new ObjectMapper().readValue(predicate, Predicate.class);
      return new PredicateDownloadRequest(predicateObj, creator, notificationAddress, sendNotification,DownloadFormat.valueOf(format));
    }
  }

}

