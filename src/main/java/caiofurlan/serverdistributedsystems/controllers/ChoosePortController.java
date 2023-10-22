package caiofurlan.serverdistributedsystems.controllers;

import caiofurlan.serverdistributedsystems.App;
import caiofurlan.serverdistributedsystems.models.Model;
import caiofurlan.serverdistributedsystems.models.UserModel;
import caiofurlan.serverdistributedsystems.system.utilities.JWTManager;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class ChoosePortController implements Initializable {

    private App app = new App();

    public Text error_text;
    public Button button_start;
    public TextField port_field;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        port_field.setText("12345");
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
            Stage stage = (Stage) port_field.getScene().getWindow();
            if (port_field.getText().isEmpty()) {
                error_text.setText("Porta não pode ser vazia");
            }
            else {
                Model.getInstance().getViewFactory().closeStage(stage);
                Model.getInstance().getViewFactory().showHomeWindow();
                app.startServer(port_field.getText());
            }
        });
    }
}
