package caiofurlan.serverdistributedsystems.system.connection;

import caiofurlan.serverdistributedsystems.models.Model;
import caiofurlan.serverdistributedsystems.models.UserModel;
import caiofurlan.serverdistributedsystems.system.utilities.JWTManager;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.Long.parseLong;

public class UserDialogActions {

    public UserDialogActions() {
    }

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
                default:
                    response = unknownAction(action);
            }
        } catch (JsonProcessingException ex) {
            System.out.println(ex.getMessage());
            SendData sender = new SendData();
            response = sender.stringSendError(action, ex.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    private static String manageUserRegister(String data) throws JsonProcessingException {
        ReceiveData request = new ReceiveData(ReceiveData.stringToMap(data));
        SendData sender = new SendData();
        String response = null;
        request.setPassword(JWTManager.hashPassword(request.getPassword()));
        long id = parseLong(JWTManager.getUserIdFromToken(request.getToken()));
        try {
            if (UserValidation.validate("admin", id)) {
                UserModel user = new UserModel(request.getName(), request.getEmail(), request.getPassword(), request.getType());
                Model.getInstance().getDatabaseDriver().addUser(user);
                response = sender.stringSendRegisterUser();
            }
        } catch (Exception e) {
            response = sender.stringSendError(request.getAction(), e.getMessage());
        }
        return response;
    }

    private static String manageUserAutoRegister(String data) throws JsonProcessingException {
        ReceiveData request = new ReceiveData(ReceiveData.stringToMap(data));
        SendData sender = new SendData();
        String response = null;
        request.setPassword(JWTManager.hashPassword(request.getPassword()));
        UserModel user = new UserModel(request.getName(), request.getEmail(), request.getPassword(), "user");
        Model.getInstance().getDatabaseDriver().addUser(user);
        response = sender.stringSendAutoRegister();
        return response;
    }

    private static String manageLogin(String data) throws JsonProcessingException, SQLException {
        ReceiveData request = new ReceiveData(ReceiveData.stringToMap(data));
        SendData sender = new SendData();
        String response = null;
        ResultSet resultSet = Model.getInstance().getDatabaseDriver().getUserLogin(request.getEmail(), request.getPassword());
        UserModel user = Model.getInstance().getDatabaseDriver().getUserFromResultSet(resultSet);
        if (user != null) {
            boolean isValid = JWTManager.checkPassword(request.getPassword(), user.getPassword());
            if (isValid) {
                String token = JWTManager.generateToken(String.valueOf(user.getUserID()), user.isAdmin());

                response = sender.stringSendLogin(token);
            } else {
                response = manageError(request.getAction(), "Credenciais incorretas!");
            }
        } else {
            response = manageError(request.getAction(), "Credenciais incorretas!");
        }
        return response;
    }

    private static String manageLogout(String data) throws JsonProcessingException {
        ReceiveData request = new ReceiveData("logout", ReceiveData.stringToMap(data));
        SendData sender = new SendData();

        return sender.stringSendLogout();
    }

    private static String unknownAction(String action) throws JsonProcessingException {
        SendData sender = new SendData();
        return sender.stringSendUnknownAction(action);
    }
    private static String manageError(String action, String message) throws JsonProcessingException {
        SendData sender = new SendData();
        return sender.stringSendError(action, message);
    }

}