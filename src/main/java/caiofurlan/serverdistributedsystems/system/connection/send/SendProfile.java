package caiofurlan.serverdistributedsystems.system.connection.send;

import caiofurlan.serverdistributedsystems.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendProfile extends Sender {

    public SendProfile() {
        super();
        setAction("pedido-proprio-usuario");
        setMessage("Sucesso");
    }

    public JsonNode generateProfileData(User user) throws JsonProcessingException {
        ((ObjectNode) getData()).set("user", objectMapper.convertValue(user, JsonNode.class));
        return generateFinalData();
    }

    public String sendText(User userData) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateProfileData(userData));
    }
}
