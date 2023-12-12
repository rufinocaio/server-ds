package caiofurlan.serverdistributedsystems.models;

import caiofurlan.serverdistributedsystems.views.ViewFactory;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;
    private final SessionManager sessionManager;

    private Model() {
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();
        this.sessionManager = new SessionManager();
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public synchronized DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }
}