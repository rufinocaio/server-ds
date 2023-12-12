package caiofurlan.serverdistributedsystems.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SessionManager {
    private final ObservableList<Session> sessions;
    private final ObservableList<Session> loginSessions;

    public SessionManager() {
        this.sessions = FXCollections.observableArrayList();
        this.loginSessions = FXCollections.observableArrayList();
    }

    public ObservableList<Session> getSessions() {
        return sessions;
    }

    public ObservableList<Session> getLoginSessions() {
        return loginSessions;
    }

    public void addSession(String ip) throws JsonProcessingException {
        sessions.add(new Session(ip));
    }

    public void updateSession(String ip,String token, User user) {
        sessions.stream().filter(session -> session.getIp().equals(ip)).forEach(session -> {
            session.setToken(token);
            session.setUser(user);
        });
        updateLoginSessions();
    }

    public  void removeLoginSession(String ip) throws JsonProcessingException {
        sessions.stream().filter(session -> session.getIp().equals(ip)).forEach(session -> {
            session.setToken(null);
            session.setUser(null);
        });
        updateLoginSessions();
    }

    public void removeSessions(String ip) {
        sessions.removeIf(session -> session.getIp().equals(ip));
        updateLoginSessions();
    }

    public void updateLoginSessions() {
        loginSessions.clear();
        sessions.stream().filter(session -> session.getToken() != null).forEach(loginSessions::add);
    }

    public boolean validateSession(String token) {
        return sessions.stream().anyMatch(session -> session.getToken().equals(token));
    }
}
