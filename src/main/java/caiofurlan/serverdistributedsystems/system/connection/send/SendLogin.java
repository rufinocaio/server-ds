package caiofurlan.serverdistributedsystems.system.connection.send;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendLogin extends Sender {

    public SendLogin() {
        super();
        setAction("login");
        setMessage("logado com sucesso");
    }

    public JsonNode generateLoginData(String token) throws JsonProcessingException {
        ((ObjectNode) getData()).put("token", token);
        return generateFinalData();
    }

    public String sendText(String token) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateLoginData(token));
    }
}
