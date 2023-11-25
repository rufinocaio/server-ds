package caiofurlan.serverdistributedsystems.system.connection.send.adminusercrud;

import caiofurlan.serverdistributedsystems.models.User;
import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendRequestUserEdit extends Sender {
    public JsonNode generateRequestUserEditData(User user) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.createObjectNode();
        ((ObjectNode) jsonNode).set("user", objectMapper.convertValue(user, JsonNode.class));
        setData(jsonNode);
        return generateFinalData("pedido-edicao-usuario", false, "Sucesso", getData());
    }

    public String sendText(User user) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateRequestUserEditData(user));
    }
}
