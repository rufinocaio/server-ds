package caiofurlan.serverdistributedsystems.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private static Label home_text;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        home_text = new Label();
        setHome_text("Servidor aberto!");
    }

    public static void setHome_text(String text) {
        home_text.setText(text);
    }
}
