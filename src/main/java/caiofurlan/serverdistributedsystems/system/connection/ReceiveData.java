package caiofurlan.serverdistributedsystems.system.connection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class ReceiveData {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private String action;
    private JsonNode data;

    public ReceiveData(JsonNode response) {
        this.action = response.get("action").asText();
        if (response.has("data")) {
            this.data = response.get("data");
        } else {
            this.data = response;
        }
    }

    public ReceiveData(String action, JsonNode data) {
        this.action = action;
        this.data = data;
    }

    public ReceiveData() {}

    public static JsonNode stringToMap(String json) throws JsonProcessingException {
        JsonNode map = objectMapper.readTree(json);
        return map;
    }

    public String getName() {
        if (data.has("name")) {
            return data.get("name").asText();
        } else {
            System.out.println("Não há nome neste objeto.");
            return null;
        }
    }

    public String getEmail() {
        if (data.has("email")) {
            return data.get("email").asText();
        } else {
            System.out.println("Não há email neste objeto.");
            return null;
        }
    }

    public String getPassword() {
        if (data.has("password")) {
            return data.get("password").asText();
        } else {
            System.out.println("Não há senha neste objeto.");
            return null;
        }
    }

    public int getID() {
        if (data.has("id")) {
            return data.get("id").asInt();
        } else {
            System.out.println("Não há ID de usuário neste objeto.");
            return 0;
        }
    }

    public int getUserID() {
        if (data.has("user_id")) {
            return data.get("user_id").asInt();
        } else {
            System.out.println("Não há ID de usuário neste objeto.");
            return 0;
        }
    }

    public String getType() {
        if (data.has("type")) {
            return data.get("type").asText();
        } else {
            System.out.println("Não há tipo de conta neste objeto.");
            return null;
        }
    }

    public String getToken() {
        if (data.has("token")) {
            return data.get("token").asText();
        } else {
            System.out.println("Não há token neste objeto.");
            return null;
        }
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

}
