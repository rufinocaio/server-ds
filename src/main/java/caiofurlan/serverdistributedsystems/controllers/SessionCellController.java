package caiofurlan.serverdistributedsystems.controllers;

import caiofurlan.serverdistributedsystems.models.Session;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SessionCellController implements Initializable {
    @FXML
    private Label ip_label;
    @FXML
    private Label username_label;
    @FXML
    private Label type_label;

    private final Session session;

    public SessionCellController(Session session) {
        this.session = session;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ip_label.setText("IP: " + session.getIp());
        username_label.setText("Usuário: " + session.getUser().getName());
        String type = session.getUser().getType().equals("Admin") ? "Administrador" : "Usuário";
        type_label.setText("Tipo: " + type);
    }
}
