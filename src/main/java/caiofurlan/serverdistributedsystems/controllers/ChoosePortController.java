package caiofurlan.serverdistributedsystems.controllers;

import caiofurlan.serverdistributedsystems.App;
import caiofurlan.serverdistributedsystems.models.Model;
import caiofurlan.serverdistributedsystems.models.UserModel;
import caiofurlan.serverdistributedsystems.system.utilities.JWTManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class ChoosePortController implements Initializable {

    private App app = new App();

    @FXML
    private Label error_text;

    @FXML
    private Button button_start;

    @FXML
    private TextField port_field;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        port_field.setText("12345");
        error_text.setText("");
        try {
            if (Model.getInstance().getDatabaseDriver().getUserByID(1) == null)
            {
                UserModel user = new UserModel("Admin", "admin@admin.com", JWTManager.hashPassword("0192023A7BBD73250516F069DF18B500"), "admin");
                Model.getInstance().getDatabaseDriver().addUser(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        button_start.setOnAction(event -> {
            if (port_field.getText().isEmpty()) {
                error_text.setText("O número da porta não pode ser vazio.");
            }
            else {
                Model.getInstance().getViewFactory().closeStage((Stage) port_field.getScene().getWindow());
                Model.getInstance().getViewFactory().showHomeWindow();
                app.startServer(port_field.getText());
            }
        });
    }
}
