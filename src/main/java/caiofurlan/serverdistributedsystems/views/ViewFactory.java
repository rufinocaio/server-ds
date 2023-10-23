package caiofurlan.serverdistributedsystems.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;

public class ViewFactory {

    public ViewFactory() {
    }

    private void createStage(FXMLLoader loader, String title) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(title);
        stage.show();
    }

    /* Common views */
    public void closeStage(Stage stage) {
        stage.close();
    }

    public void showChoosePortWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/caiofurlan/serverdistributedsystems/fxFiles/chooseport.fxml"));
        createStage(loader, "Conecte-se!");
    }

    public void showHomeWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/caiofurlan/serverdistributedsystems/fxFiles/home.fxml"));
        createStage(loader, "Servidor aberto!");
    }

    /* ----------------- */

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
