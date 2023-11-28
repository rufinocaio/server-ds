package caiofurlan.serverdistributedsystems.system.connection.send.adminusercrud;

import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendDeleteUser extends Sender {

    public SendDeleteUser() {
        super();
        setAction("excluir-usuario");
        setMessage("Usuário removido com sucesso!");
    }

    public JsonNode generateDeleteUserADMData() throws JsonProcessingException {
        return generateFinalData();
    }

    public String sendText() throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateDeleteUserADMData());
    }
}
