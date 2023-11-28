package caiofurlan.serverdistributedsystems.system.connection.send;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendError extends Sender {

    public SendError() {
        super();
        setError(true);
    }

    public JsonNode generateErrorData(String action, String message) throws JsonProcessingException {
        setAction(action);
        setMessage(message);
        return generateFinalData();
    }

    public String sendText(String action, String message) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateErrorData(action, message));
    }
}
