package caiofurlan.serverdistributedsystems.models;

public class Session {

    private String ip;
    private String token;
    private User user;

    public Session(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
