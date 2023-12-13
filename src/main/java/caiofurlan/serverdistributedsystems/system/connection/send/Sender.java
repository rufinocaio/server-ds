package caiofurlan.serverdistributedsystems.system.connection.send;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public abstract class Sender {
    @JsonIgnore
    public final ObjectMapper objectMapper;
    private String action;
    private boolean error;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private JsonNode data;

    public Sender() {
        objectMapper = new ObjectMapper();
        data = objectMapper.createObjectNode();
    }

    public String getAction() {
            return action;
    }

    public void setAction(String action) {
            this.action = action;
    }

    public boolean getError() {
            return error;
    }

    public void setError(boolean error) {
            this.error = error;
    }

    public String getMessage() {
            return message;
    }

    public void setMessage(String message) {
            this.message = message;
    }

    public JsonNode getData() {
            return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    public JsonNode generateFinalData() throws JsonProcessingException {
        if (getData().isEmpty()) {
            setData(null);
        }
        return objectMapper.convertValue(this, JsonNode.class);
    }

}