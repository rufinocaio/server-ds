package caiofurlan.serverdistributedsystems.system.connection.send;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendUnknownAction extends Sender {

    public SendUnknownAction() {
        super();
        setError(true);
        setMessage("Ação desconhecida");
    }

    public JsonNode generateUnknownActionData(String action) throws JsonProcessingException {
        setAction(action);
        return generateFinalData();
    }

    public String sendText(String action) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateUnknownActionData(action));
    }
}
