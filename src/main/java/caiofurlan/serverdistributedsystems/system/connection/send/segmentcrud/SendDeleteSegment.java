package caiofurlan.serverdistributedsystems.system.connection.send.segmentcrud;

import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendDeleteSegment extends Sender {
    public JsonNode generateDeleteSegmentData() throws JsonProcessingException {
        return generateFinalData("excluir-segmento", false, "Segmento removido com sucesso!", null);
    }

    public String sendText() throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateDeleteSegmentData());
    }
}
