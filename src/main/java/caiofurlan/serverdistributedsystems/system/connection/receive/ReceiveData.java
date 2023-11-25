package caiofurlan.serverdistributedsystems.system.connection.receive;

import caiofurlan.serverdistributedsystems.models.Point;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        return objectMapper.readTree(json);
    }

    // Common

    public String getToken() {
        if (data.has("token")) {
            return data.get("token").asText();
        } else {
            System.out.println("Não há token neste objeto.");
            return null;
        }
    }

    public String getName() {
        if (data.has("name")) {
            return data.get("name").asText();
        } else {
            System.out.println("Não há nome neste objeto.");
            return null;
        }
    }

    // User CRUD

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

    // Point CRUD

    public int getPointID() {
        if (data.has("ponto_id")) {
            return data.get("ponto_id").asInt();
        } else {
            System.out.println("Não há ID de ponto neste objeto.");
            return 0;
        }
    }

    public String getObs() {
        if (data.has("obs")) {
            return data.get("obs").asText();
        } else {
            System.out.println("Não há observação neste objeto.");
            return null;
        }
    }

    // Segment CRUD

    public int getSegmentID() {
        if (data.has("segmento_id")) {
            return data.get("segmento_id").asInt();
        } else {
            System.out.println("Não há ID de segmento neste objeto.");
            return 0;
        }
    }

    public Point getStartPoint() {
        if (data.has("ponto_origem")) {
            return objectMapper.convertValue(data.get("ponto_origem"), Point.class);
        } else {
            System.out.println("Não há ponto inicial neste objeto.");
            return null;
        }
    }

    public Point getEndPoint() {
        if (data.has("ponto_destino")) {
            return objectMapper.convertValue(data.get("ponto_destino"), Point.class);
        } else {
            System.out.println("Não há ponto final neste objeto.");
            return null;
        }
    }

    public String getDirection() {
        if (data.has("direcao")) {
            return data.get("direcao").asText();
        } else {
            System.out.println("Não há direção neste objeto.");
            return null;
        }
    }

    public int getDistance() {
        if (data.has("distancia")) {
            return data.get("distancia").asInt();
        } else {
            System.out.println("Não há distância neste objeto.");
            return 0;
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
