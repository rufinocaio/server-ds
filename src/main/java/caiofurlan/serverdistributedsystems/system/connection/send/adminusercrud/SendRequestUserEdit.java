package caiofurlan.serverdistributedsystems.system.connection.send.adminusercrud;

import caiofurlan.serverdistributedsystems.models.User;
import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendRequestUserEdit extends Sender {

public SendRequestUserEdit() {
        super();
        setAction("pedido-edicao-usuario");
        setMessage("Sucesso");
    }

    public JsonNode generateRequestUserEditData(User user) throws JsonProcessingException {
        ((ObjectNode) getData()).set("user", objectMapper.convertValue(user, JsonNode.class));
        return generateFinalData();
    }

    public String sendText(User user) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateRequestUserEditData(user));
    }
}
