package caiofurlan.serverdistributedsystems.system.connection.send.segmentcrud;

import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendRegisterSegment extends Sender {

    public SendRegisterSegment() {
        super();
        setAction("cadastro-segmento");
        setMessage("Segmento cadastrado com sucesso!");
    }

    public JsonNode generateRegisterSegmentData() throws JsonProcessingException {
        return generateFinalData();
    }

    public String sendText() throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateRegisterSegmentData());
    }
}
