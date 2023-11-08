package caiofurlan.serverdistributedsystems.system.connection.send;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendLogout extends Sender {

    public JsonNode generateLogoutData() throws JsonProcessingException {
        return generateFinalData("logout", false, "logout efetuado com sucesso", null);
    }

    public String sendText() throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateLogoutData());
    }
}