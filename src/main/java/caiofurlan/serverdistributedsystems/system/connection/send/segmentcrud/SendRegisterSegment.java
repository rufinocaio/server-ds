package caiofurlan.serverdistributedsystems.system.connection.send.segmentcrud;

import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendRegisterSegment extends Sender {
    public JsonNode generateRegisterSegmentData() throws JsonProcessingException {
        return generateFinalData("cadastro-segmento", false, "Segmento cadastrado com sucesso!", null);
    }

    public String sendText() throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateRegisterSegmentData());
    }
}
