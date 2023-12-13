package caiofurlan.serverdistributedsystems.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
    private String name;
    private String email;
    @JsonIgnore
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

    }
