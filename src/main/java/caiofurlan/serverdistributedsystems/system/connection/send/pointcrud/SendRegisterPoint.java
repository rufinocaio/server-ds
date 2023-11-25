package caiofurlan.serverdistributedsystems.system.connection.send.pointcrud;

import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendRegisterPoint extends Sender {
    public JsonNode generateRegisterPointData() throws JsonProcessingException {
        return generateFinalData("cadastro-ponto", false, "Ponto de referência cadastrado com sucesso!", null);
    }

    public String sendText() throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateRegisterPointData());
    }
}
