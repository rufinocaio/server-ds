package caiofurlan.serverdistributedsystems.models;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class DatabaseDriver {
    private Connection connection;

    public DatabaseDriver() {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUserLogin(String email, String password) throws SQLException {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM user WHERE email = '" + email + "'");
            if(BCrypt.checkpw(password, resultSet.getString("password"))){
                return resultSet;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (resultSet == null) throw new AssertionError();
        if (!resultSet.isBeforeFirst()) {
            return null;
        }
        return resultSet;
    }

    public void addUser(UserModel user) {
        Statement statement;
        try {
            statement = this.connection.createStatement();
            statement.executeUpdate("INSERT INTO user (name, email, password, type) VALUES ('" + user.getName() + "', '" + user.getEmail() + "', '" + user.getPassword() + "', '" + user.getType() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserModel getUserByEmail(String email) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM user WHERE email = '" + email + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getUserFromResultSet(resultSet);
    }

    public UserModel getUserByID(long userID) throws SQLException {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM user WHERE userID = '" + userID + "'");
        } catch (SQLException e){
            e.printStackTrace();
        }
        if (!resultSet.isBeforeFirst()) {
            return null;
        }
        return getUserFromResultSet(resultSet);
    }

    public UserModel getUserFromResultSet(ResultSet resultSet) {
        if (resultSet == null) {
            return null;
        }
        UserModel userModel = null;
        try {
            userModel = new UserModel(resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("type"), resultSet.getLong("userID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userModel;
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
