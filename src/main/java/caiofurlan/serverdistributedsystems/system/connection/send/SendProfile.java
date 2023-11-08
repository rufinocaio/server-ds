package caiofurlan.serverdistributedsystems.system.connection.send;

import caiofurlan.serverdistributedsystems.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendProfile extends Sender {

    public JsonNode generateProfileData(User userData) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.createObjectNode();
        ((ObjectNode) jsonNode).set("user", userData.generateJson());
        setData(jsonNode);
        return generateFinalData("pedido-proprio-usuario", false, "Sucesso", getData());
    }

    public String sendText(User userData) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateProfileData(userData));
    }
}
