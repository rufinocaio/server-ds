package caiofurlan.serverdistributedsystems.controllers;

import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {


    public Text home_text;

    public void setHomeText(String text) {
        home_text.setText(text);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
