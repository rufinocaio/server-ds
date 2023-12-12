package caiofurlan.serverdistributedsystems.views;

import caiofurlan.serverdistributedsystems.controllers.SessionCellController;
import caiofurlan.serverdistributedsystems.models.Session;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class SessionCellFactory extends ListCell<Session> {
    @Override
    protected void updateItem(Session session, boolean empty) {
        super.updateItem(session, empty);
        Platform.runLater(() -> {
            if(empty || session == null) {
                setText(null);
                setGraphic(null);
            } else {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/caiofurlan/serverdistributedsystems/fxFiles/sessioncell.fxml"));
                SessionCellController controller = new SessionCellController(session);
                fxmlLoader.setController(controller);
                setText(null);
                try {
                    setGraphic(fxmlLoader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
