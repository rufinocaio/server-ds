package caiofurlan.serverdistributedsystems.system.connection.send.pointcrud;

import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendEditPoint extends Sender{

    public SendEditPoint() {
        super();
        setAction("edicao-ponto");
        setMessage("Ponto atualizado com sucesso!");
    }

    public JsonNode generateEditPointData() throws JsonProcessingException {
        return generateFinalData();
    }

    public String sendText() throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateEditPointData());
    }
}
