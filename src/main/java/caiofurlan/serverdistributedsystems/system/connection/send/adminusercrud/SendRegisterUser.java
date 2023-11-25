package caiofurlan.serverdistributedsystems.system.connection.send.adminusercrud;

import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendRegisterUser extends Sender {

    public JsonNode generateRegisterUserData() throws JsonProcessingException {
        return generateFinalData("cadastro-usuario", false, "Usuário cadastrado com sucesso!", null);
    }

    public String sendText() throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateRegisterUserData());
    }

}
