package caiofurlan.serverdistributedsystems.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Label home_text;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.home_text.setText("Servidor aberto!");
    }

}
