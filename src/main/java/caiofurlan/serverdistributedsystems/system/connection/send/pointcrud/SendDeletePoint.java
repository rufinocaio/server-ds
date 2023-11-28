package caiofurlan.serverdistributedsystems.system.connection.send.pointcrud;

import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendDeletePoint extends Sender {

    public SendDeletePoint() {
        super();
        setAction("excluir-ponto");
        setMessage("Ponto removido com sucesso!");
    }

    public JsonNode generateDeletePointData() throws JsonProcessingException {
        return generateFinalData();
    }

    public String sendText() throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateDeletePointData());
    }
}
