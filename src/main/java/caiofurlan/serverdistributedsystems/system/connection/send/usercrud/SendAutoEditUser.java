package caiofurlan.serverdistributedsystems.system.connection.send.usercrud;

import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendAutoEditUser extends Sender {

    public SendAutoEditUser() {
        super();
        setAction("autoedicao-usuario");
        setMessage("Usuário atualizado com sucesso!");
    }

    public JsonNode generateEditUserData() throws JsonProcessingException {
        return generateFinalData();
    }

    public String sendText() throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateEditUserData());
    }
}
