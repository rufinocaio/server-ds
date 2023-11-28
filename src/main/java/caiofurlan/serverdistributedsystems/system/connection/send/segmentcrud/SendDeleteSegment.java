package caiofurlan.serverdistributedsystems.system.connection.send.segmentcrud;

import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendDeleteSegment extends Sender {

    public SendDeleteSegment() {
        super();
        setAction("excluir-segmento");
        setMessage("Segmento removido com sucesso!");
    }

    public JsonNode generateDeleteSegmentData() throws JsonProcessingException {
        return generateFinalData();
    }

    public String sendText() throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateDeleteSegmentData());
    }
}
