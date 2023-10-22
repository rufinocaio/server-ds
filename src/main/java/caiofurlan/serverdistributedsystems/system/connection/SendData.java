package caiofurlan.serverdistributedsystems.system.connection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class SendData {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private Map<String, Object> data;

    public SendData(String action, String message) {
        this.data = new HashMap<>();
        this.data.put("action", action);
        this.data.put("message", message);
    }

    public SendData(Map<String, Object> data) {
        this.data = data;
    }

    public SendData(String action) {
        this.data = new HashMap<>();
        this.data.put("action", action);
    }

    public SendData() {
    }

    public Map<String, Object> getData() {
            return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Map<String, Object> generateLoginData(String token) throws JsonProcessingException {
        this.data = new HashMap<>();
        this.data.put("token", token);
        return generateFinalData("login", false, "logado com sucesso", this.data);
    }

    public Map<String, Object> generateRegisterUserData() throws JsonProcessingException {
        return generateFinalData("cadastro-usuario", false, "Usuário cadastrado com sucesso!", null);
    }

    public Map<String, Object> generateLogoutData() throws JsonProcessingException {
        return generateFinalData("logout", false, "logout efetuado com sucesso", null);
    }

    public Map<String, Object> generateErrorData(String action, String message) throws JsonProcessingException {
        this.data = new HashMap<>();
        return generateFinalData(action, true, message, null);
    }

    public Map<String, Object> generateUnknownActionData(String action) throws JsonProcessingException {
        return generateFinalData(action, true, "Ação desconhecida", null);
    }

    public  Map<String, Object> generateFinalData(String action, boolean error, String message, Map<String, Object> data) throws JsonProcessingException {
        this.data = new HashMap<>();
        this.data.put("action", action);
        this.data.put("error", error);
        if (message != null) {
            this.data.put("message", message);
        }
        if (data != null) {
            this.data.put("data", data);
        }
        return this.data;
    }

    /* ----------------- */
    public String stringSendLogin(String token) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateLoginData(token));
    }

    public String stringSendLogout() throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateLogoutData());
    }

    public String stringSendRegisterUser() throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateRegisterUserData());
    }

    public String stringSendError(String action, String message) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateErrorData(action, message));
    }

    public String stringSendUnknownAction(String action) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateUnknownActionData(action));
    }
    /* ----------------- */


}