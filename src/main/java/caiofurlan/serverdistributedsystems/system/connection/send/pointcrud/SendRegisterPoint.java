package caiofurlan.serverdistributedsystems.system.connection.send.pointcrud;

import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendRegisterPoint extends Sender {

    public SendRegisterPoint() {
        super();
        setAction("cadastro-ponto");
        setMessage("Ponto de referência cadastrado com sucesso!");
    }

    public JsonNode generateRegisterPointData() throws JsonProcessingException {
        return generateFinalData();
    }

    public String sendText() throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateRegisterPointData());
    }
}
