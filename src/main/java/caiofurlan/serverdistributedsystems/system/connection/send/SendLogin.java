package caiofurlan.serverdistributedsystems.system.connection.send;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendLogin extends Sender {

    public JsonNode generateLoginData(String token) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.createObjectNode();
        ((ObjectNode) jsonNode).put("token", token);
        setData(jsonNode);
        return generateFinalData("login", false, "logado com sucesso", getData());
    }

    public String sendText(String token) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateLoginData(token));
    }
}
