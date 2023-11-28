package caiofurlan.serverdistributedsystems.system.connection.send.segmentcrud;

import caiofurlan.serverdistributedsystems.models.Segment;
import caiofurlan.serverdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public class SendSegmentList extends Sender {

    public SendSegmentList() {
        super();
        setAction("listar-segmentos");
        setMessage("Sucesso");
    }

    public JsonNode generateUserListData(List<Segment> segmentList) throws JsonProcessingException {
        if(segmentList == null || segmentList.isEmpty()) {
            ((ObjectNode) getData()).set("segmentos", null);
        } else {
            ArrayNode segmentsArray = objectMapper.createArrayNode();
            for (Segment segment : segmentList) {
                segmentsArray.add(objectMapper.convertValue(segment, JsonNode.class));
            }
            ((ObjectNode) getData()).set("segmentos", segmentsArray);
        }
        return generateFinalData();
    }

    public String sendText(List<Segment> ClientList) throws JsonProcessingException {
        return objectMapper.writeValueAsString(generateUserListData(ClientList));
    }
}
