package caiofurlan.serverdistributedsystems.system.connection.send.routes;

import caiofurlan.serverdistributedsystems.models.Segment;
import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import caiofurlan.serverdistributedsystems.system.utilities.JacksonViews;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public class SendRequestRoute extends Sender {
    public SendRequestRoute() {
        super();
        setAction("pedido-rotas");
        setMessage("Rota recuperada com sucesso");
    }

    public JsonNode generateRequestRouteData(List<Segment> segments) throws JsonProcessingException {
        ArrayNode arrayNode = objectMapper.createArrayNode();
        for (Segment segment : segments) {
            String jsonString = objectMapper.writerWithView(JacksonViews.Public.class).writeValueAsString(segment);
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            arrayNode.add(jsonNode);
        }
        ((ObjectNode) this.getData()).set("segmentos", arrayNode);
        JsonNode jsonNode = objectMapper.createObjectNode();
        ((ObjectNode) jsonNode).put("error", getError());
        ((ObjectNode) jsonNode).put("message", getMessage());
        ((ObjectNode) jsonNode).set("segmentos", arrayNode);
        return jsonNode;
    }

    public String sendText(List<Segment> segments) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateRequestRouteData(segments));
    }
}
