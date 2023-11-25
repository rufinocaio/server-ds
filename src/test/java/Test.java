import caiofurlan.serverdistributedsystems.models.Point;
import caiofurlan.serverdistributedsystems.models.Segment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Test {

    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws JsonProcessingException {
        testFunction();
    }

    public static void testFunction() throws JsonProcessingException
    {
        Point point = new Point("portaria", "", 1);
        Point point2 = new Point("igreja", "", 2);
        Segment segment = new Segment(point, point2, "frente", 50, "");
        JsonNode jsonNode = objectMapper.createObjectNode();
        ((ObjectNode) jsonNode).set("segment", objectMapper.convertValue(segment, JsonNode.class));
        System.out.println(objectMapper.writeValueAsString(jsonNode));
    }

}
