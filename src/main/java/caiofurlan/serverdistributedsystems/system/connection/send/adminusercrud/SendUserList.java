package caiofurlan.serverdistributedsystems.system.connection.send.adminusercrud;

import caiofurlan.serverdistributedsystems.models.User;
import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public class SendUserList extends Sender {

    public SendUserList() {
        super();
        setAction("listar-usuarios");
        setMessage("Sucesso");
    }

    public JsonNode generateUserListData(List<User> userList) throws JsonProcessingException {
        if(userList == null || userList.isEmpty()) {
            ((ObjectNode) getData()).set("users", null);
        } else {
            ArrayNode usersArray = objectMapper.createArrayNode();
            for (User user : userList) {
                //usersArray.add(user.generateJson());
                usersArray.add(objectMapper.convertValue(user, JsonNode.class));
            }
            ((ObjectNode) getData()).set("users", usersArray);
        }
        return generateFinalData();
    }

    public String sendText(List<User> ClientList) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateUserListData(ClientList));
    }
}
