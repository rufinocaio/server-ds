package caiofurlan.serverdistributedsystems.system.connection.send;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendUnknownAction extends Sender {

    public JsonNode generateUnknownActionData(String action) throws JsonProcessingException {
        return generateFinalData(action, true, "Ação desconhecida", null);
    }

    public String sendText(String action) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateUnknownActionData(action));
    }
}
