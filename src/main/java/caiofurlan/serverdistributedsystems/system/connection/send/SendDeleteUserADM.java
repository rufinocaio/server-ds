package caiofurlan.serverdistributedsystems.system.connection.send;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendDeleteUserADM extends Sender {
    public JsonNode generateAutoDeleteUserData() throws JsonProcessingException {
        return generateFinalData("excluir-usuario", false, "Usuário removido com sucesso!", null);
    }

    public String sendText() throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateAutoDeleteUserData());
    }
}
