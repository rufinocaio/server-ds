package caiofurlan.serverdistributedsystems.models;

public class Session {
    private String token;
    private User user;

    public Session(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }
}
