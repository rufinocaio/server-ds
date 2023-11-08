package caiofurlan.serverdistributedsystems.system.connection.send;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Sender {
    public static final ObjectMapper objectMapper = new ObjectMapper();
    private JsonNode data;

    public Sender(String action, String message) {
        this.data = objectMapper.createObjectNode();
        ((ObjectNode) this.data).put("action", action);
        ((ObjectNode) this.data).put("message", message);
    }

    public Sender(JsonNode data) {
        this.data = data;
    }

    public Sender(String action) {
        this.data = objectMapper.createObjectNode();
        ((ObjectNode) this.data).put("action", action);
    }

    public Sender() {
    }

    public JsonNode getData() {
            return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    public  JsonNode generateFinalData(String action, boolean error, String message, JsonNode data) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.createObjectNode();
        ((ObjectNode) jsonNode).put("action", action);
        ((ObjectNode) jsonNode).put("error", error);
        if (message != null) {
            ((ObjectNode) jsonNode).put("message", message);
        }
        if (data != null) {
            ((ObjectNode) jsonNode).put("data", data);
        }

        return jsonNode;
    }

}