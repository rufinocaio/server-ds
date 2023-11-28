package caiofurlan.serverdistributedsystems.system.connection.send.adminusercrud;

import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendEditUser extends Sender {

    public SendEditUser() {
        super();
        setAction("edicao-usuario");
        setMessage("Usuário atualizado com sucesso!");
    }

    public JsonNode generateEditUserData() throws JsonProcessingException {
        return generateFinalData();
    }

    public String sendText() throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateEditUserData());
    }
}
