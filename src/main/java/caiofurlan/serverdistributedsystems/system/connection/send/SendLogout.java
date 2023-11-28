package caiofurlan.serverdistributedsystems.system.connection.send;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SendLogout extends Sender {

    public SendLogout() {
        super();
        setAction("logout");
        setMessage("logout efetuado com sucesso");
    }

    public JsonNode generateLogoutData() throws JsonProcessingException {
        return generateFinalData();
    }

    public String sendText() throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateLogoutData());
    }
}