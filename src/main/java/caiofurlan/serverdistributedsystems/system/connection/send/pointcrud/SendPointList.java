package caiofurlan.serverdistributedsystems.system.connection.send.pointcrud;

import caiofurlan.serverdistributedsystems.models.Point;
import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public class SendPointList extends Sender {
    public JsonNode generateUserListData(List<Point> pointList) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.createObjectNode();
        if(pointList == null || pointList.isEmpty()) {
            ((ObjectNode) jsonNode).set("pontos", null);
        } else {
            ArrayNode pointsArray = objectMapper.createArrayNode();
            for (Point point : pointList) {
                pointsArray.add(point.generateJson());
            }
            ((ObjectNode) jsonNode).set("pontos", pointsArray);
        }
        setData(jsonNode);
        return generateFinalData("listar-pontos", false, "Sucesso", getData());
    }

    public String sendText(List<Point> pointList) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateUserListData(pointList));
    }
}
