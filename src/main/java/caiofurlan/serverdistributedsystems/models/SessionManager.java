package caiofurlan.serverdistributedsystems.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SessionManager {
    private final ObservableList<Session> sessions;

    public SessionManager() {
        this.sessions = FXCollections.observableArrayList();
    }

    public ObservableList<Session> getSessions() {
        return sessions;
    }

    public void addSession(String token, User user) throws JsonProcessingException {
        sessions.add(new Session(token, user));
    }

    public  void removeSession(String token) throws JsonProcessingException {
        sessions.removeIf(session -> session.getToken().equals(token));
    }

    public boolean validateSession(String token) {
        return sessions.stream().anyMatch(session -> session.getToken().equals(token));
    }
}
