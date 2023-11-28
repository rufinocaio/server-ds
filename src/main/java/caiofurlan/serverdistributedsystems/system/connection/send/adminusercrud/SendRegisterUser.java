package caiofurlan.serverdistributedsystems.system.connection.send.adminusercrud;

import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendRegisterUser extends Sender {

    public SendRegisterUser() {
        super();
        setAction("cadastro-usuario");
        setMessage("Usuário cadastrado com sucesso!");
    }

    public JsonNode generateRegisterUserData() throws JsonProcessingException {
        return generateFinalData();
    }

    public String sendText() throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateRegisterUserData());
    }

}
