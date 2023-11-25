package caiofurlan.serverdistributedsystems.system.connection.send.pointcrud;

import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendEditPoint extends Sender{
    public JsonNode generateEditPointData() throws JsonProcessingException {
        return generateFinalData("edicao-usuario", false, "Usuário atualizado com sucesso!", null);
    }

    public String sendText() throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateEditPointData());
    }
}
