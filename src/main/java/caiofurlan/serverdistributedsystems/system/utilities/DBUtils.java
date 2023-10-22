package caiofurlan.serverdistributedsystems.system.utilities;

import java.sql.*;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;

public class DBUtils {

    public static void signUpUser(String name, String email, String password, boolean isAdm) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/distributed_systems", "root", "1606");
            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("Email já cadastrado!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Você não pode usar esse email!");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO users (name, email, password, user_type) VALUES (?, ?, ?, ?)");
                psInsert.setString(1, name);
                psInsert.setString(2, email);
                psInsert.setString(3, password);
                psInsert.setString(4, isAdm ? "admin" : "user");
                psInsert.executeUpdate();

                //changeScene(actionEvent, "caiofurlan/clientdistributedsystems/fxFiles/logged-in.fxml", "You are logged in!", name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void logInUser(ActionEvent actionEvent, String email, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/distributed_systems", "root", "1606");
            preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Usuário não encontrado!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Usuário não encontrado!");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    String retrievedName = resultSet.getString("name");
                    if (retrievedPassword.equals(password)) {
                        //changeScene(actionEvent, "caiofurlan/clientdistributedsystems/fxFiles/logged-in.fxml", "You are logged in!", name);
                    } else {
                        System.out.println("Senha incorreta!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Senha incorreta!");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
