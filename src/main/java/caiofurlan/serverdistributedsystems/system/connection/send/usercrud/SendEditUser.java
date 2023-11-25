package caiofurlan.serverdistributedsystems.system.connection.send.usercrud;

import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendEditUser extends Sender {
    public JsonNode generateEditUserData() throws JsonProcessingException {
        return generateFinalData("autoedicao-usuario", false, "Usuário atualizado com sucesso!", null);
    }

    public String sendText() throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateEditUserData());
    }
}
