package caiofurlan.serverdistributedsystems.system.connection.send.pointcrud;

import caiofurlan.serverdistributedsystems.models.Point;
import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendRequestPointEdit extends Sender {

    public SendRequestPointEdit() {
        super();
        setAction("pedido-edicao-ponto");
        setMessage("Sucesso");
    }

    public JsonNode generateRequestPointEditData(Point point) throws JsonProcessingException {
        ((ObjectNode) getData()).set("ponto", objectMapper.convertValue(point, JsonNode.class));
        return generateFinalData();
    }

    public String sendText(Point point) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateRequestPointEditData(point));
    }
}
