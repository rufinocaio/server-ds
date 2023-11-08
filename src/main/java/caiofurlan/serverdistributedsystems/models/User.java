package caiofurlan.serverdistributedsystems.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class User {
    private String name;
    private String email;
    private String password;
    private String type;
    private int id;

    public User() {
    }

    public User(String name, String email, String type, int id) {
        this.name = name;
        this.email = email;
        this.type = type;
        this.id = id;
    }

    public User(String name, String email, String password, String type, int id) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
        this.id = id;
    }

    public User(String name, String email, String password, String type) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String getType() {
        return type;
    }

    public int getID(){
        return id;
    }

    public JsonNode generateJson() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.createObjectNode();
        ((ObjectNode) json).put("name", this.name);
        ((ObjectNode) json).put("email", this.email);
        ((ObjectNode) json).put("id", this.id);
        ((ObjectNode) json).put("type", this.type);
        return json;
    }

}
