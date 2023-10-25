package caiofurlan.serverdistributedsystems;

import caiofurlan.serverdistributedsystems.models.Model;
import caiofurlan.serverdistributedsystems.system.connection.Server;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        openChoosePortWindow();
    }

    public static void main(String[] args) {
        launch();
    }

    public void openChoosePortWindow() {
        Model.getInstance().getViewFactory().showChoosePortWindow();
    }

    public void startServer(String port) {
        Thread serverThread = new Thread(new Server(Integer.parseInt(port)));
        serverThread.start();
    }

}
