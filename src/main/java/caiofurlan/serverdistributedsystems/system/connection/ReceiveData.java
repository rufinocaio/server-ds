package caiofurlan.serverdistributedsystems.system.connection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class ReceiveData {
    private static final ObjectMapper jackson = new ObjectMapper();

    private String action;
    private Map<String, Object> data;

    public ReceiveData(Map<String, Object> response) {
        this.action = (String) response.get("action");
        if (response.containsKey("data")) {
            this.data = (Map<String, Object>) response.get("data");
        }
    }

    public ReceiveData(String action, Map<String, Object> data) {
        this.action = action;
        this.data = data;
    }

    public ReceiveData() {}

    public static Map<String, Object> stringToMap(String json) throws JsonProcessingException {
        Map<String, Object> map = jackson.readValue(json, Map.class);
        return map;
    }

    public String getName() {
        if (data.containsKey("name")) {
            return (String) data.get("name");
        } else {
            System.out.println("Não há nome neste objeto.");
            return null;
        }
    }
    public void setName(String name) {
        if (data.containsKey("name")) {
            data.replace("name  ", name);
        } else {
            System.out.println("Não há nome neste objeto.");
        }
    }

    public String getEmail() {
        if (data.containsKey("email")) {
            return (String) data.get("email");
        } else {
            System.out.println("Não há email neste objeto.");
            return null;
        }
    }

    public void setEmail(String email) {
        if (data.containsKey("email")) {
            data.replace("email", email);
        } else {
            System.out.println("Não há email neste objeto.");
        }
    }

    public String getPassword() {
        if (data.containsKey("password")) {
            return (String) data.get("password");
        } else {
            System.out.println("Não há senha neste objeto.");
            return null;
        }
    }
    public void setPassword(String password) {
        if (data.containsKey("password")) {
            data.replace("password", password);
        } else {
            System.out.println("Não há senha neste objeto.");
        }
    }

    public long getUserID() {
        if (data.containsKey("user_id")) {
            return (long) data.get("user_id");
        } else {
            System.out.println("Não há ID de usuário neste objeto.");
            return 0;
        }
    }

    public void setUserID(long userID) {
        if (data.containsKey("user_id")) {
            data.replace("user_id", userID);
        } else {
            System.out.println("Não há ID de usuário neste objeto.");
        }
    }

    public String getType() {
        if (data.containsKey("type")) {
            return (String) data.get("type");
        } else {
            System.out.println("Não há tipo de conta neste objeto.");
            return null;
        }
    }
    public void setType(String accountType) {
        if (data.containsKey("type")) {
            data.replace("type", accountType);
        } else {
            System.out.println("Não há tipo de conta neste objeto.");
        }
    }

    public String getToken() {
        if (data.containsKey("token")) {
            return (String) data.get("token");
        } else {
            System.out.println("Não há token neste objeto.");
            return null;
        }
    }
    public void setToken(String token) {
        if (data.containsKey("token")) {
            data.replace("token", token);
        } else {
            System.out.println("Não há token neste objeto.");
        }
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

}
