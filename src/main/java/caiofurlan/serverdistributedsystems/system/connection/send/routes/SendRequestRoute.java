package caiofurlan.serverdistributedsystems.system.connection.send.routes;

import caiofurlan.serverdistributedsystems.models.Segment;
import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
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
        int i = 0;
        for (Segment segment : segments) {
            JsonNode jsonNode = objectMapper.convertValue(segment, JsonNode.class);
            ((ObjectNode) jsonNode).put("id", i++);
            arrayNode.add(jsonNode);
        }
        ((ObjectNode) this.getData()).set("segmentos", arrayNode);
        return generateFinalData();
    }

    public String sendText(List<Segment> segments) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateRequestRouteData(segments));
    }
}
