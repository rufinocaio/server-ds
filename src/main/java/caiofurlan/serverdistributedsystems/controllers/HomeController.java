package caiofurlan.serverdistributedsystems.controllers;

import caiofurlan.serverdistributedsystems.models.Model;
import caiofurlan.serverdistributedsystems.models.Session;
import caiofurlan.serverdistributedsystems.views.SessionCellFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private ListView<Session> session_list_view;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        session_list_view.setItems(Model.getInstance().getSessionManager().getLoginSessions());
        session_list_view.setCellFactory(studentListView -> new SessionCellFactory());

        // Listen for changes in the list of login sessions
        Model.getInstance().getSessionManager().getLoginSessions().addListener((javafx.collections.ListChangeListener.Change<? extends Session> c) -> {
            session_list_view.refresh();
        });    }

}
