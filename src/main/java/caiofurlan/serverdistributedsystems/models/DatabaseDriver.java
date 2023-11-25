package caiofurlan.serverdistributedsystems.models;

import caiofurlan.serverdistributedsystems.system.utilities.JWTManager;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDriver {
    private Connection connection;

    public DatabaseDriver() {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // User CRUD
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

    public List<User> getUserList() throws SQLException {
        Statement statement;
        ResultSet resultSet = null;
        List<User> clientList = new ArrayList<>();
        try {
            statement = this.connection.createStatement();
            resultSet = statement.executeQuery("SELECT userID, name, type, email FROM user");
        } catch (SQLException e) {
            e.printStackTrace();
        } if (!resultSet.isBeforeFirst()) {
            return null;
        }
        while (resultSet.next()) {
            int id = resultSet.getInt("userID");
            String name = resultSet.getString("name");
            String type = resultSet.getString("type");
            String email = resultSet.getString("email");

            User client = new User(name, email, type, id);
            clientList.add(client);
        }
        return clientList;
    }

    public User getUserByID(long userID) throws SQLException {
        Statement statement;
        ResultSet resultSet = null;
        statement = this.connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM user WHERE userID = '" + userID + "'");
        if (resultSet == null || !resultSet.isBeforeFirst()) {
            return null;
        }
        return getUserFromResultSet(resultSet);
    }

    public User getUserByToken(String token) throws SQLException {
        return getUserByID(Integer.parseInt(JWTManager.getUserIdFromToken(token)));
    }

    public User getUserLogin(String email, String password) {
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
        User user = getUserFromResultSet(resultSet);
        return user;
    }

    public User getUserFromResultSet(ResultSet resultSet) {
        if (resultSet == null) {
            return null;
        }
        User user = null;
        try {
            user = new User(resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("type"), resultSet.getInt("userID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Point CRUD

    public void addPoint(Point point) {
        Statement statement;
        try {
            statement = this.connection.createStatement();
            statement.executeUpdate("INSERT INTO point (name, obs) VALUES ('" + point.getName() + "', '" + point.getObs() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePoint(Point point) {
        Statement statement;
        try {
            statement = this.connection.createStatement();
            statement.executeUpdate("UPDATE point SET name = '" + point.getName() + "', obs = '" + point.getObs() + "' WHERE pointID = '" + point.getID() + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePoint(int pointID) {
        Statement statement;
        try {
            statement = this.connection.createStatement();
            statement.executeUpdate("DELETE FROM point WHERE pointID = '" + pointID + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Point> getPointList() throws SQLException {
        Statement statement;
        ResultSet resultSet = null;
        List<Point> pointList = new ArrayList<>();
        try {
            statement = this.connection.createStatement();
            resultSet = statement.executeQuery("SELECT pointID, name, obs FROM point");
        } catch (SQLException e) {
            e.printStackTrace();
        } if (!resultSet.isBeforeFirst()) {
            return null;
        }
        while (resultSet.next()) {
            int id = resultSet.getInt("pointID");
            String name = resultSet.getString("name");
            String obs = resultSet.getString("obs");

            Point point = new Point(name, obs, id);
            pointList.add(point);
        }
        return pointList;
    }

    public Point getPointByID(long pointID) throws SQLException {
        Statement statement;
        ResultSet resultSet = null;
        statement = this.connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM point WHERE pointID = '" + pointID + "'");
        if (resultSet == null || !resultSet.isBeforeFirst()) {
            return null;
        }
        return getPointFromResultSet(resultSet);
    }

    public Point getPointFromResultSet(ResultSet resultSet) {
        if (resultSet == null) {
            return null;
        }
        Point point = null;
        try {
            point = new Point(resultSet.getString("name"), resultSet.getString("obs"), resultSet.getInt("pointID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return point;
    }

    // Segment CRUD

    public void addSegment(Segment segment) {
        Statement statement;
        try {
            statement = this.connection.createStatement();
            statement.executeUpdate("INSERT INTO user (startPointID, endPointID, direction, distance, obs) VALUES ('" + segment.getPontoOrigem().getID() + "', '" + segment.getPontoDestino().getID() + "', '" + segment.getDirecao() + "', '" + segment.getDistancia() + "', '" + segment.getObs() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSegment(Segment segment) {
        Statement statement;
        try {
            statement = this.connection.createStatement();
            statement.executeUpdate("UPDATE segment SET startPointID = '" + segment.getPontoOrigem().getID() + "', endPointID = '" + segment.getPontoDestino().getID() + "', direction = '" + segment.getDirecao() + "', distance = '" + segment.getDistancia() + "', obs = '" + segment.getObs() + "' WHERE segmentID = '" + segment.getID() + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSegment(int segmentID) {
        Statement statement;
        try {
            statement = this.connection.createStatement();
            statement.executeUpdate("DELETE FROM segment WHERE segmentID = '" + segmentID + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Segment> getSegmentList() throws SQLException {
        Statement statement;
        ResultSet resultSet = null;
        List<Segment> segmentList = new ArrayList<>();
        try {
            statement = this.connection.createStatement();
            resultSet = statement.executeQuery("SELECT segmentID, startPointID, endPointID, direction, distance, obs FROM segment");
        } catch (SQLException e) {
            e.printStackTrace();
        } if (!resultSet.isBeforeFirst()) {
            return null;
        }
        while (resultSet.next()) {
            int id = resultSet.getInt("segmentID");
            int startPointID = resultSet.getInt("startPointID");
            int endPointID = resultSet.getInt("endPointID");
            String direction = resultSet.getString("direction");
            int distance = resultSet.getInt("distance");
            String obs = resultSet.getString("obs");

            Segment segment = new Segment(getPointByID(startPointID), getPointByID(endPointID), direction, distance, obs, id);
            segmentList.add(segment);
        }
        return segmentList;
    }

    public Segment getSegmentByID(long segmentID) throws SQLException {
        Statement statement;
        ResultSet resultSet = null;
        statement = this.connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM segment WHERE segmentID = '" + segmentID + "'");
        if (resultSet == null || !resultSet.isBeforeFirst()) {
            return null;
        }
        return getSegmentFromResultSet(resultSet);
    }

    public Segment getSegmentFromResultSet(ResultSet resultSet) {
        if (resultSet == null) {
            return null;
        }
        Segment segment = null;
        try {
            segment = new Segment(getPointByID(resultSet.getInt("startPointID")), getPointByID(resultSet.getInt("endPointID")), resultSet.getString("direction"), resultSet.getInt("distance"), resultSet.getString("obs"), resultSet.getInt("segmentID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return segment;
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
