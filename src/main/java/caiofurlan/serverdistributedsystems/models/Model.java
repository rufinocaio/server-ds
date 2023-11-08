package caiofurlan.serverdistributedsystems.models;

import caiofurlan.serverdistributedsystems.views.AccountType;
import caiofurlan.serverdistributedsystems.views.ViewFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;
    private AccountType userAccountType = AccountType.USER;
    private User user;

    private Model() {
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();
        this.userAccountType = AccountType.USER;
        this.user = new User();
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

    public DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }

    public AccountType getUserAccountType() {
        return userAccountType;
    }

    public void setUserAccountType(AccountType userAccountType) {
        this.userAccountType = userAccountType;
    }


    /* User method section */

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void verifyLoginData(String email, String password) throws SQLException {
        ResultSet resultSet = databaseDriver.getUserLogin(email, password);
        try {
            if (resultSet.isBeforeFirst()) {
                User tmpUser = databaseDriver.getUserFromResultSet(resultSet);
                setUser(tmpUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}