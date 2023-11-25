package caiofurlan.serverdistributedsystems.system.connection.send.segmentcrud;

import caiofurlan.serverdistributedsystems.models.Segment;
import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public class SendSegmentList extends Sender {
    public JsonNode generateUserListData(List<Segment> segmentList) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.createObjectNode();
        if(segmentList == null || segmentList.isEmpty()) {
            ((ObjectNode) jsonNode).set("segmentos", null);
        } else {
            ArrayNode usersArray = objectMapper.createArrayNode();
            for (Segment segment : segmentList) {
                usersArray.add(segment.generateJson());
            }
            ((ObjectNode) jsonNode).set("segmentos", usersArray);
        }
        setData(jsonNode);
        return generateFinalData("listar-segmentos", false, "Sucesso", getData());
    }

    public String sendText(List<Segment> ClientList) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateUserListData(ClientList));
    }
}
