package caiofurlan.serverdistributedsystems.system.connection.send.segmentcrud;

import caiofurlan.serverdistributedsystems.models.Segment;
import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendRequestSegmentEdit extends Sender {

    public SendRequestSegmentEdit() {
        super();
        setAction("pedido-edicao-segmento");
        setMessage("Sucesso");
    }

    public JsonNode generateRequestSegmentEditData(Segment segment) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.createObjectNode();
        ((ObjectNode) jsonNode).set("segment", objectMapper.convertValue(segment, JsonNode.class));
        setData(jsonNode);
        return generateFinalData();
    }

    public String sendText(Segment segment) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateRequestSegmentEditData(segment));
    }
}