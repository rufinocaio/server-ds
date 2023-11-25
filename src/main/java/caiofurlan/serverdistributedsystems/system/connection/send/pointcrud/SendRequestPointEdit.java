package caiofurlan.serverdistributedsystems.system.connection.send.pointcrud;

import caiofurlan.serverdistributedsystems.models.Point;
import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendRequestPointEdit extends Sender {
    public JsonNode generateRequestPointEditData(Point point) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.createObjectNode();
        ((ObjectNode) jsonNode).set("ponto", objectMapper.convertValue(point, JsonNode.class));
        setData(jsonNode);
        return generateFinalData("pedido-edicao-ponto", false, "Sucesso", getData());
    }

    public String sendText(Point point) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateRequestPointEditData(point));
    }
}
