package caiofurlan.serverdistributedsystems.system.connection.send;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendError extends Sender {

    public JsonNode generateErrorData(String action, String message) throws JsonProcessingException {
        return generateFinalData(action, true, message, null);
    }

    public String sendText(String action, String message) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateErrorData(action, message));
    }
}
