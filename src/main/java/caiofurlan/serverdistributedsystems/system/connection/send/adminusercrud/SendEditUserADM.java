package caiofurlan.serverdistributedsystems.system.connection.send.adminusercrud;

import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendEditUserADM extends Sender {
    public JsonNode generateEditUserData() throws JsonProcessingException {
        return generateFinalData("edicao-usuario", false, "Usuário atualizado com sucesso!", null);
    }

    public String sendText() throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateEditUserData());
    }
}
