package caiofurlan.serverdistributedsystems.system.connection.send.usercrud;

import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendAutoRegisterUser extends Sender {

    public SendAutoRegisterUser() {
        super();
        setAction("autocadastro-usuario");
        setMessage("Usuário cadastrado com sucesso!");
    }

    public JsonNode generateAutoRegisterData() throws JsonProcessingException {
        return generateFinalData();
    }

    public String sendText() throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateAutoRegisterData());
    }

}
