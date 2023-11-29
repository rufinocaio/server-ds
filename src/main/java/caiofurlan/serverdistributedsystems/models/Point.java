package caiofurlan.serverdistributedsystems.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Point {
    private String name;
    private String obs;
    private int id;

    public Point() {
    }

    public Point(String name, String obs) {
        this.name = name;
        this.obs = obs == null || obs.isEmpty() ? null : obs;
    }

    public Point(String name, String obs, int id) {
        this.name = name;
        this.obs = obs == null || obs.isEmpty() ? null : obs;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getObs() {
        return obs;
    }

    public int getID(){
        return id;
    }

}