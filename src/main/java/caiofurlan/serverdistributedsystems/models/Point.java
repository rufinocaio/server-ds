package caiofurlan.serverdistributedsystems.models;

import java.util.Objects;

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

    public int getId(){
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return id == point.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}