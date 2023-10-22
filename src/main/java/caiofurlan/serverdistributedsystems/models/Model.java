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
    private UserModel user;
    private boolean userLoginSucessFlag;


    private Model() {
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();
        this.userAccountType = AccountType.USER;
        this.user = new UserModel("", "", "", "", 0);
        this.userLoginSucessFlag = false;
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
    public boolean getUserLoginSucessFLag() {
        return userLoginSucessFlag;
    }
    public void setUserLoginSucessFlag(boolean userLoginSucessFlag) {
        this.userLoginSucessFlag = userLoginSucessFlag;
    }

    public UserModel getUser() {
        return user;
    }

    public void verifyLoginData(String email, String password) throws SQLException {
        ResultSet resultSet = databaseDriver.getUserLogin(email, password);
        try {
            if (resultSet.isBeforeFirst()) {
                this.user.setName(resultSet.getString("name"));
                this.user.setEmail(resultSet.getString("email"));
                this.user.setUserID(resultSet.getLong("user_id"));
                this.user.setAdmin(resultSet.getString("type"));
                this.userLoginSucessFlag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}