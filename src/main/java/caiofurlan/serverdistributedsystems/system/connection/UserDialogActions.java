package caiofurlan.serverdistributedsystems.system.connection;

import caiofurlan.serverdistributedsystems.models.Model;
import caiofurlan.serverdistributedsystems.models.User;
import caiofurlan.serverdistributedsystems.system.connection.send.*;
import caiofurlan.serverdistributedsystems.system.utilities.JWTManager;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDialogActions {

    public static String chooseAction(String action, String data) throws JsonProcessingException {
        String response = null;

        try {
            switch (action) {
                case "login":
                    response = manageLogin(data);
                    break;
                case "logout":
                    response = manageLogout(data);
                    break;
                case "cadastro-usuario":
                    response = manageUserRegister(data);
                    break;
                case "autocadastro-usuario":
                    response = manageUserAutoRegister(data);
                    break;
                case "pedido-proprio-usuario":
                    response = manageUserInfo(data);
                    break;
                case "autoedicao-usuario":
                    response = ManageEditUser(data);
                    break;
                case "listar-usuarios":
                    response = manageUserList(data);
                    break;
                case "excluir-proprio-usuario":
                    response = manageDeleteUser(data);
                    break;
                case "edicao-usuario":
                    response = ManageEditUserADM(data);
                    break;
                case "excluir-usuario":
                    response = manageDeleteUserADM(data);
                    break;
                default:
                    response = unknownAction(action);
            }
        } catch (JsonProcessingException ex) {
            System.out.println(ex.getMessage());
            SendError errorSender = new SendError();
            response = errorSender.sendText(action, ex.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    private static String manageUserRegister(String data) throws JsonProcessingException {
        ReceiveData request = new ReceiveData(ReceiveData.stringToMap(data));
        String response = null;
        int id = Integer.parseInt((JWTManager.getUserIdFromToken(request.getToken())));
        try {
            if (UserValidation.validate("admin", id)) {
                SendAutoRegister sender = new SendAutoRegister();
                User user = new User(request.getName(), request.getEmail(), JWTManager.hashPassword(request.getPassword()), request.getType());
                Model.getInstance().getDatabaseDriver().addUser(user);
                response = sender.sendText();
            }
        } catch (Exception e) {
            SendError errorSender = new SendError();
            response = errorSender.sendText(request.getAction(), e.getMessage());
        }
        return response;
    }

    private static String manageUserAutoRegister(String data) throws JsonProcessingException {
        ReceiveData request = new ReceiveData(ReceiveData.stringToMap(data));
        SendAutoRegister sender = new SendAutoRegister();
        String response = null;
        User user = new User(request.getName(), request.getEmail(), JWTManager.hashPassword(request.getPassword()), "user");
        Model.getInstance().getDatabaseDriver().addUser(user);
        response = sender.sendText();
        return response;
    }

    private static String manageLogin(String data) throws JsonProcessingException, SQLException {
        ReceiveData request = new ReceiveData(ReceiveData.stringToMap(data));
        String response = null;
        ResultSet resultSet = Model.getInstance().getDatabaseDriver().getUserLogin(request.getEmail(), request.getPassword());
        User user = Model.getInstance().getDatabaseDriver().getUserFromResultSet(resultSet);
        if (user != null) {
            boolean isValid = JWTManager.checkPassword(request.getPassword(), user.getPassword());
            if (isValid) {
                SendLogin sender = new SendLogin();
                String token = JWTManager.generateToken(String.valueOf(user.getID()), user.getType().equals("admin"));
                response = sender.sendText(token);
                Model.getInstance().getDatabaseDriver().addSession(token, user.getID());
            } else {
                response = manageError(request.getAction(), "Credenciais incorretas!");
            }
        } else {
            response = manageError(request.getAction(), "Credenciais incorretas!");
        }
        return response;
    }

    private static String manageUserInfo(String data) throws JsonProcessingException, SQLException {
        ReceiveData request = new ReceiveData(ReceiveData.stringToMap(data));
        String response = null;
        User user = Model.getInstance().getDatabaseDriver().getUserByToken(request.getToken());
        if (user != null) {
            SendProfile sender = new SendProfile();
            response = sender.sendText(user);
        } else {
            response = manageError(request.getAction(), "Usuário não encontrado!");
        }
        return response;
    }

    private static String ManageEditUser(String data) throws JsonProcessingException, SQLException {
        ReceiveData request = new ReceiveData(ReceiveData.stringToMap(data));
        String response = null;
        User user = Model.getInstance().getDatabaseDriver().getUserByToken(request.getToken());
        if (user != null) {
            User newUser = new User(request.getName(), request.getEmail(), JWTManager.hashPassword(request.getPassword()), "user", user.getID());
            Model.getInstance().getDatabaseDriver().updateUser(newUser);
            SendEditUser sender = new SendEditUser();
            response = sender.sendText();
        } else {
            response = manageError(request.getAction(), "Usuário não encontrado!");
        }
        return response;
    }

    private static String manageLogout(String data) throws JsonProcessingException {
        ReceiveData request = new ReceiveData(ReceiveData.stringToMap(data));
        String token = request.getToken();
        if (Model.getInstance().getDatabaseDriver().isTokenActive(token)) {
            Model.getInstance().getDatabaseDriver().removeSession(token);
            SendLogout sender = new SendLogout();
            return sender.sendText();
        }
        return manageError(request.getAction(), "Este token não está ativo no momento.");
    }

    private static String manageUserList(String data) throws SQLException, JsonProcessingException {
        ReceiveData request = new ReceiveData(ReceiveData.stringToMap(data));
        String response = null;
        ResultSet resultSet = Model.getInstance().getDatabaseDriver().getUserList();
        if (resultSet == null) {
            SendClientList sender = new SendClientList();
            response = sender.sendText(null);
        } else {
            List<User> clientList = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("userID");
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                String email = resultSet.getString("email");

                User client = new User(name, email, type, id);
                clientList.add(client);
            }
            SendClientList sender = new SendClientList();
            response = sender.sendText(clientList);
        }
        return response;
    }

    private static String manageDeleteUser(String data) throws JsonProcessingException, SQLException {
        ReceiveData request = new ReceiveData(ReceiveData.stringToMap(data));
        String response = null;
        User user = Model.getInstance().getDatabaseDriver().getUserByToken(request.getToken());
        if (user != null) {
            Model.getInstance().getDatabaseDriver().deleteUser(user.getID());
            SendEditUser sender = new SendEditUser();
            response = sender.sendText();
        } else {
            response = manageError(request.getAction(), "Usuário não encontrado!");
        }
        return response;
    }

    private static String ManageEditUserADM(String data) throws SQLException, JsonProcessingException {
        ReceiveData request = new ReceiveData(ReceiveData.stringToMap(data));
        String response = null;
        User user = Model.getInstance().getDatabaseDriver().getUserByID(request.getUserID());
        int adminID = Integer.parseInt((JWTManager.getUserIdFromToken(request.getToken())));
        try {
            if (UserValidation.validate("admin", adminID)) {
                if (user != null) {
                    user = new User(request.getName(), request.getEmail(), JWTManager.hashPassword(request.getPassword()), request.getType(), user.getID());
                    Model.getInstance().getDatabaseDriver().updateUser(user);
                    SendEditUserADM sender = new SendEditUserADM();
                    response = sender.sendText();
                } else {
                    response = manageError(request.getAction(), "Usuário não encontrado!");
                }
            }
        } catch (Exception e) {
            SendError errorSender = new SendError();
            response = errorSender.sendText(request.getAction(), e.getMessage());
        }
        return response;
    }

    private static String manageDeleteUserADM(String data) throws JsonProcessingException, SQLException {
        ReceiveData request = new ReceiveData(ReceiveData.stringToMap(data));
        String response = null;
        User user = Model.getInstance().getDatabaseDriver().getUserByID(request.getUserID());
        int adminID = Integer.parseInt((JWTManager.getUserIdFromToken(request.getToken())));
        try {
            if (UserValidation.validate("admin", adminID)) {
                if (user != null) {
                    Model.getInstance().getDatabaseDriver().deleteUser(request.getUserID());
                    SendDeleteUserADM sender = new SendDeleteUserADM();
                    response = sender.sendText();
                } else {
                    response = manageError(request.getAction(), "Usuário não encontrado!");
                }
            }
        } catch (Exception e) {
            SendError errorSender = new SendError();
            response = errorSender.sendText(request.getAction(), e.getMessage());
        }
        return response;
    }

    private static String unknownAction(String action) throws JsonProcessingException {
        SendUnknownAction sender = new SendUnknownAction();
        return sender.sendText(action);
    }
    private static String manageError(String action, String message) throws JsonProcessingException {
        SendError sender = new SendError();
        return sender.sendText(action, message);
    }

}