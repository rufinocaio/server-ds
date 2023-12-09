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
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO user (name, email, password, type) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getType());
            preparedStatement.executeUpdate();
        }
    }

    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE user SET name = ?, email = ?, password = ?, type = ? WHERE userID = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getType());
            preparedStatement.setInt(5, user.getID());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteUser(int userID) throws SQLException {
        String sql = "DELETE FROM user WHERE userID = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userID);
            preparedStatement.executeUpdate();
        }
    }

    public List<User> getUserList() throws SQLException {
        String sql = "SELECT userID, name, type, email FROM user";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> clientList = new ArrayList<>();
            while (resultSet.next()) {
                User client = getUserFromResultSet(resultSet);
                if (client != null) {
                    clientList.add(client);
                }
            }
            return clientList;
        }
    }

    public User getUserByID(long userID) throws SQLException {
        String sql = "SELECT * FROM user WHERE userID = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return null;
            }
            return getUserFromResultSet(resultSet);
        }
    }

    public User getUserByToken(String token) throws SQLException {
        return getUserByID(Integer.parseInt(JWTManager.getUserIdFromToken(token)));
    }

    public User getUserLogin(String email, String password) throws SQLException {
        String sql = "SELECT * FROM user WHERE email = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return null;
            }
            User user = getUserFromResultSet(resultSet);
            if (user != null && BCrypt.checkpw(password, user.getPassword())) {
                return user;
            }
            return null;
        }
    }

    public User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String type = resultSet.getString("type");
        int userID = resultSet.getInt("userID");
        return new User(name, email, password, type, userID);
    }

    // Point CRUD
    public void addPoint(Point point) throws SQLException {
        String sql = "INSERT INTO point (name, obs) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, point.getName());
            preparedStatement.setString(2, point.getObs());
            preparedStatement.executeUpdate();
        }
    }

    public void updatePoint(Point point) throws SQLException {
        String sql = "UPDATE point SET name = ?, obs = ? WHERE pointID = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, point.getName());
            preparedStatement.setString(2, point.getObs());
            preparedStatement.setInt(3, point.getID());
            preparedStatement.executeUpdate();
        }
    }

    public void deletePoint(int pointID) throws SQLException {
        String sql = "DELETE FROM point WHERE pointID = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, pointID);
            preparedStatement.executeUpdate();
        }
        deleteSegmentFromPointID(pointID);
    }

    public List<Point> getPointList() throws SQLException {
        String sql = "SELECT pointID, name, obs FROM point";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Point> pointList = new ArrayList<>();
            while (resultSet.next()) {
                Point point = getPointFromResultSet(resultSet);
                if (point != null) {
                    pointList.add(point);
                }
            }
            return pointList;
        }
    }

    public Point getPointByID(int pointID) throws SQLException {
        String sql = "SELECT * FROM point WHERE pointID = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, pointID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return null;
            }
            return getPointFromResultSet(resultSet);
        }
    }

    public Point getPointFromResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        String name = resultSet.getString("name");
        String obs = resultSet.getString("obs");
        if (obs != null && (obs.isEmpty() || obs.equals("null"))) {
            obs = null;
        }
        int pointID = resultSet.getInt("pointID");
        return new Point(name, obs, pointID);
    }

    // Segment CRUD
    public void addSegment(Segment segment) throws SQLException {
        String sql = "INSERT INTO segment (startPointID, endPointID, direction, distance, blocked, obs) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, segment.getPontoOrigem().getID());
            preparedStatement.setInt(2, segment.getPontoDestino().getID());
            preparedStatement.setString(3, segment.getDirecao());
            preparedStatement.setInt(4, segment.getDistancia());
            preparedStatement.setBoolean(5, segment.getBloqueado());
            preparedStatement.setString(6, segment.getObs());
            preparedStatement.executeUpdate();
        }
    }

    public void updateSegment(Segment segment) throws SQLException {
        String sql = "UPDATE segment SET startPointID = ?, endPointID = ?, direction = ?, distance = ?, blocked = ?, obs = ? WHERE segmentID = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, segment.getPontoOrigem().getID());
            preparedStatement.setInt(2, segment.getPontoDestino().getID());
            preparedStatement.setString(3, segment.getDirecao());
            preparedStatement.setInt(4, segment.getDistancia());
            preparedStatement.setBoolean(5, segment.getBloqueado());
            preparedStatement.setString(6, segment.getObs());
            preparedStatement.setInt(7, segment.getId());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteSegment(int segmentID) throws SQLException {
        String sql = "DELETE FROM segment WHERE segmentID = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, segmentID);
            preparedStatement.executeUpdate();
        }
    }

    public List<Segment> getSegmentList() throws SQLException {
        String sql = "SELECT * FROM segment";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Segment> segmentList = new ArrayList<>();
            while (resultSet.next()) {
                Segment segment = getSegmentFromResultSet(resultSet);
                if (segment != null) {
                    segmentList.add(segment);
                }
            }
            return segmentList;
        }
    }

    public List<Segment> getNotBlockedSegmentList() throws SQLException {
        String sql = "SELECT * FROM segment WHERE blocked = 0";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Segment> segmentList = new ArrayList<>();
            while (resultSet.next()) {
                Segment segment = getSegmentFromResultSet(resultSet);
                if (segment != null) {
                    segmentList.add(segment);
                }
            }
            return segmentList;
        }
    }

    public Segment getSegmentByID(long segmentID) throws SQLException {
        String sql = "SELECT * FROM segment WHERE segmentID = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, segmentID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return null;
            }
            return getSegmentFromResultSet(resultSet);
        }
    }

    public void deleteSegmentFromPointID(int pointId) throws SQLException {
        String sql = "DELETE FROM segment WHERE startPointID = ? OR endPointID = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, pointId);
            preparedStatement.setInt(2, pointId);
            preparedStatement.executeUpdate();
        }
    }

    public Segment getSegmentFromResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        String obs = resultSet.getString("obs");
        if (obs != null && (obs.isEmpty() || obs.equals("null"))) {
            obs = null;
        }
        return new Segment(getPointByID(resultSet.getInt("startPointID")), getPointByID(resultSet.getInt("endPointID")), resultSet.getString("direction"), resultSet.getInt("distance"), resultSet.getBoolean("blocked"), obs, resultSet.getInt("segmentID"));
    }

    public void closeConnection() throws SQLException {
        this.connection.close();
    }
}