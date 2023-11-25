package caiofurlan.serverdistributedsystems.system.connection.send.adminusercrud;

import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendDeleteUserADM extends Sender {
    public JsonNode generateDeleteUserADMData() throws JsonProcessingException {
        return generateFinalData("excluir-usuario", false, "Usuário removido com sucesso!", null);
    }

    public String sendText() throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateDeleteUserADMData());
    }
}
