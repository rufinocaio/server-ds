package caiofurlan.serverdistributedsystems.system.connection.send;

import caiofurlan.serverdistributedsystems.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public class SendClientList extends Sender{

    public JsonNode generateUserListData(List<User> clientList) throws JsonProcessingException {

        JsonNode jsonNode = objectMapper.createObjectNode();
        if(clientList == null || clientList.isEmpty()) {
            ((ObjectNode) jsonNode).set("users", null);
        } else {
            ArrayNode usersArray = objectMapper.createArrayNode();
            for (User client : clientList) {
                ObjectNode userNode = objectMapper.createObjectNode();
                userNode.put("id", client.getID());
                userNode.put("name", client.getName());
                userNode.put("type", client.getType());
                userNode.put("email", client.getEmail());
                usersArray.add(userNode);
            }

            ((ObjectNode) jsonNode).set("users", usersArray);
        }
        setData(jsonNode);
        return generateFinalData("listar-usuarios", false, "Sucesso", getData());
    }

    public String sendText(List<User> ClientList) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateUserListData(ClientList));
    }
}
