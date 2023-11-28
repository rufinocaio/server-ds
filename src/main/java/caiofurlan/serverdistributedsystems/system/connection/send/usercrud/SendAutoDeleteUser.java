package caiofurlan.serverdistributedsystems.system.connection.send.usercrud;

import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendAutoDeleteUser extends Sender {

    public SendAutoDeleteUser() {
        super();
        setAction("excluir-proprio-usuario");
        setMessage("Usuário removido com sucesso!");
    }

    public JsonNode generateAutoDeleteUserData() throws JsonProcessingException {
        return generateFinalData();
    }

    public String sendText() throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateAutoDeleteUserData());
    }
}
