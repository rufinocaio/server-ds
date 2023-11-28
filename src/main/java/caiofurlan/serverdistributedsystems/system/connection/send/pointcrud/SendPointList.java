package caiofurlan.serverdistributedsystems.system.connection.send.pointcrud;

import caiofurlan.serverdistributedsystems.models.Point;
import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public class SendPointList extends Sender {

    public SendPointList() {
        super();
        setAction("listar-pontos");
        setMessage("Sucesso");
    }

    public JsonNode generateUserListData(List<Point> pointList) throws JsonProcessingException {
        if(pointList == null || pointList.isEmpty()) {
            ((ObjectNode) getData()).set("pontos", null);
        } else {
            ArrayNode pointsArray = objectMapper.createArrayNode();
            for (Point point : pointList) {
                pointsArray.add(objectMapper.convertValue(point, JsonNode.class));
            }
            ((ObjectNode) getData()).set("pontos", pointsArray);
        }
        return generateFinalData();
    }

    public String sendText(List<Point> pointList) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateUserListData(pointList));
    }
}
