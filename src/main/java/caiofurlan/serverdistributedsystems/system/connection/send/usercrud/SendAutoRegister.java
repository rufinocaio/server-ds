package caiofurlan.serverdistributedsystems.system.connection.send.usercrud;

import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendAutoRegister extends Sender {

    public JsonNode generateAutoRegisterData() throws JsonProcessingException {
        return generateFinalData("autocadastro-usuario", false, "Usuário cadastrado com sucesso!", null);
    }

    public String sendText() throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateAutoRegisterData());
    }

}
