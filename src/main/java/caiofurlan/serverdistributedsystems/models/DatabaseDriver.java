package caiofurlan.serverdistributedsystems.models;

import caiofurlan.serverdistributedsystems.system.utilities.JWTManager;
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

    public ResultSet getUserLogin(String email, String password) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM user WHERE email = '" + email + "' ");
            if (!resultSet.isBeforeFirst() || resultSet == null || !BCrypt.checkpw(password, resultSet.getString("password"))) {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void addUser(User user) {
        Statement statement;
        try {
            statement = this.connection.createStatement();
            statement.executeUpdate("INSERT INTO user (name, email, password, type) VALUES ('" + user.getName() + "', '" + user.getEmail() + "', '" + user.getPassword() + "', '" + user.getType() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        Statement statement;
        try {
            statement = this.connection.createStatement();
            statement.executeUpdate("UPDATE user SET name = '" + user.getName() + "', email = '" + user.getEmail() + "', password = '" + user.getPassword() + "', type = '" + user.getType() + "' WHERE userID = '" + user.getID() + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userID) {
        Statement statement;
        try {
            statement = this.connection.createStatement();
            statement.executeUpdate("DELETE FROM user WHERE userID = '" + userID + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByEmail(String email) {
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

    public User getUserByID(long userID) throws SQLException {
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

    public User getUserByToken(String token) throws SQLException {
        return getUserByID(Integer.parseInt(JWTManager.getUserIdFromToken(token)));
    }

    public User getUserFromResultSet(ResultSet resultSet) {
        if (resultSet == null) {
            return null;
        }
        User userModel = null;
        try {
            userModel = new User(resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("type"), resultSet.getInt("userID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userModel;
    }

    public ResultSet getUserList() throws SQLException {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.connection.createStatement();
            resultSet = statement.executeQuery("SELECT userID, name, type, email FROM user");
        } catch (SQLException e) {
            e.printStackTrace();
        } if (!resultSet.isBeforeFirst()) {
            System.out.println("AA");
            return null;
        }
        return resultSet;
    }

    public void addSession(String token, long userID) {
        Statement statement;
        try {
            statement = this.connection.createStatement();
            statement.executeUpdate("INSERT INTO session (token, userID) VALUES ('" + token + "', '" + userID + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeSession(String token) {
        Statement statement;
        try {
            statement = this.connection.createStatement();
            statement.executeUpdate("DELETE FROM session WHERE token = '" + token + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eraseSessions() {
        Statement statement;
        try {
            statement = this.connection.createStatement();
            statement.executeUpdate("DELETE FROM session");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isTokenActive(String token) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM session WHERE token = '" + token + "'");
            if (!resultSet.isBeforeFirst()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (resultSet == null) throw new AssertionError();
        return true;
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
