package caiofurlan.serverdistributedsystems.system.connection.send.segmentcrud;

import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendEditSegment extends Sender {
    public JsonNode generateEditSegmentData() throws JsonProcessingException {
        return generateFinalData("edicao-segmento", false, "Segmento atualizado com sucesso!", null);
    }

    public String sendText() throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateEditSegmentData());
    }
}
