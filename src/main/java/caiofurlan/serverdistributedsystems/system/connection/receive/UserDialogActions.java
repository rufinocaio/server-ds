package caiofurlan.serverdistributedsystems.system.connection.receive;

import caiofurlan.serverdistributedsystems.models.Model;
import caiofurlan.serverdistributedsystems.models.Point;
import caiofurlan.serverdistributedsystems.models.Segment;
import caiofurlan.serverdistributedsystems.models.User;
import caiofurlan.serverdistributedsystems.system.connection.send.*;
import caiofurlan.serverdistributedsystems.system.connection.send.adminusercrud.*;
import caiofurlan.serverdistributedsystems.system.connection.send.pointcrud.*;
import caiofurlan.serverdistributedsystems.system.connection.send.segmentcrud.*;
import caiofurlan.serverdistributedsystems.system.connection.send.usercrud.SendAutoRegisterUser;
import caiofurlan.serverdistributedsystems.system.connection.send.usercrud.SendAutoEditUser;
import caiofurlan.serverdistributedsystems.system.utilities.JWTManager;
import caiofurlan.serverdistributedsystems.system.utilities.UserValidation;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.SQLException;
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
                    response = manageRegisterUser(data);
                    break;
                case "pedido-edicao-usuario":
                    response = manageRequestUserEdit(data);
                    break;
                case "listar-usuarios":
                    response = manageUserList(data);
                    break;
                case "edicao-usuario":
                    response = manageEditUserADM(data);
                    break;
                case "excluir-usuario":
                    response = manageDeleteUserADM(data);
                    break;
                case "autocadastro-usuario":
                    response = manageUserAutoRegister(data);
                    break;
                case "pedido-proprio-usuario":
                    response = manageUserInfo(data);
                    break;
                case "autoedicao-usuario":
                    response = manageEditUser(data);
                    break;
                case "excluir-proprio-usuario":
                    response = manageDeleteUser(data);
                    break;
                case "cadastro-ponto":
                    response = manageRegisterPoint(data);
                    break;
                case "pedido-edicao-ponto":
                    response = manageRequestPointEdit(data);
                    break;
                case "listar-pontos":
                    response = managePointList(data);
                    break;
                case "edicao-ponto":
                    response = manageEditPoint(data);
                    break;
                case "excluir-ponto":
                    response = manageDeletePoint(data);
                    break;
                case "cadastro-segmento":
                    response = manageRegisterSegment(data);
                    break;
                case "pedido-edicao-segmento":
                    response = manageRequestSegmentEdit(data);
                    break;
                case "listar-segmentos":
                    response = manageSegmentList(data);
                    break;
                case "edicao-segmento":
                    response = manageEditSegment(data);
                    break;
                case "excluir-segmento":
                    response = manageDeleteSegment(data);
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

    private static String manageLogin(String data) throws JsonProcessingException, SQLException {
        Receiver request = new Receiver(Receiver.stringToMap(data));
        String response = null;
        User user = Model.getInstance().getDatabaseDriver().getUserLogin(request.getEmail(), request.getPassword());
        if (user != null) {
            boolean isValid = JWTManager.checkPassword(request.getPassword(), user.getPassword());
            if (isValid) {
                SendLogin sender = new SendLogin();
                String token = JWTManager.generateToken(String.valueOf(user.getID()), user.getType().equals("admin"));
                response = sender.sendText(token);
                Model.getInstance().getSessionManager().addSession(token, user);
            } else {
                response = manageError(request.getAction(), "Credenciais incorretas!");
            }
        } else {
            response = manageError(request.getAction(), "Credenciais incorretas!");
        }
        return response;
    }

    private static String manageLogout(String data) throws JsonProcessingException {
        Receiver request = new Receiver(Receiver.stringToMap(data));
        String token = request.getToken();
        if (Model.getInstance().getSessionManager().validateSession(token)) {
            Model.getInstance().getSessionManager().removeSession(token);
            SendLogout sender = new SendLogout();
            return sender.sendText();
        }
        return manageError(request.getAction(), "Este token não está ativo no momento.");
    }

    private static String manageRegisterUser(String data) throws JsonProcessingException {
        Receiver request = new Receiver(Receiver.stringToMap(data));
        String response = null;
        int id = Integer.parseInt((JWTManager.getUserIdFromToken(request.getToken())));
        try {
            if (UserValidation.validate("admin", id)) {
                User user = new User(request.getName(), request.getEmail(), JWTManager.hashPassword(request.getPassword()), request.getType());
                Model.getInstance().getDatabaseDriver().addUser(user);
                SendRegisterUser sender = new SendRegisterUser();
                response = sender.sendText();
            }
        } catch (Exception e) {
            SendError errorSender = new SendError();
            response = errorSender.sendText(request.getAction(), e.getMessage());
        }
        return response;
    }

    private static String manageRequestUserEdit(String data) throws SQLException, JsonProcessingException {
        Receiver request = new Receiver(Receiver.stringToMap(data));
        String response = null;
        User user = Model.getInstance().getDatabaseDriver().getUserByID(request.getUserID());
        if (user == null) {
            response = manageError(request.getAction(), "Usuário não encontrado!");
        } else {
            SendRequestUserEdit sender = new SendRequestUserEdit();
            response = sender.sendText(user);
        }
        return response;
    }

    private static String manageUserList(String data) throws SQLException, JsonProcessingException {
        Receiver request = new Receiver(Receiver.stringToMap(data));
        String response = null;
        int id = Integer.parseInt((JWTManager.getUserIdFromToken(request.getToken())));
        try {
            if (UserValidation.validate("admin", id)) {
                List<User> userList = Model.getInstance().getDatabaseDriver().getUserList();
                if (userList == null || userList.isEmpty()) {
                    SendUserList sender = new SendUserList();
                    response = sender.sendText(null);
                } else {
                    SendUserList sender = new SendUserList();
                    response = sender.sendText(userList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private static String manageEditUserADM(String data) throws SQLException, JsonProcessingException {
        Receiver request = new Receiver(Receiver.stringToMap(data));
        String response = null;
        int adminID = Integer.parseInt((JWTManager.getUserIdFromToken(request.getToken())));
        try {
            if (UserValidation.validate("admin", adminID)) {
                User user = Model.getInstance().getDatabaseDriver().getUserByID(request.getUserID());
                if (user != null) {
                    user = new User(request.getName(), request.getEmail(), JWTManager.hashPassword(request.getPassword()), request.getType(), user.getID());
                    Model.getInstance().getDatabaseDriver().updateUser(user);
                    SendEditUser sender = new SendEditUser();
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
        Receiver request = new Receiver(Receiver.stringToMap(data));
        String response = null;
        int adminID = Integer.parseInt((JWTManager.getUserIdFromToken(request.getToken())));
        try {
            if (UserValidation.validate("admin", adminID)) {
                User user = Model.getInstance().getDatabaseDriver().getUserByID(request.getUserID());
                if (user != null) {
                    Model.getInstance().getDatabaseDriver().deleteUser(request.getUserID());
                    SendDeleteUser sender = new SendDeleteUser();
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

    private static String manageUserAutoRegister(String data) throws JsonProcessingException {
        Receiver request = new Receiver(Receiver.stringToMap(data));
        SendAutoRegisterUser sender = new SendAutoRegisterUser();
        String response = null;
        User user = new User(request.getName(), request.getEmail(), JWTManager.hashPassword(request.getPassword()), "user");
        Model.getInstance().getDatabaseDriver().addUser(user);
        response = sender.sendText();
        return response;
    }

    private static String manageUserInfo(String data) throws JsonProcessingException, SQLException {
        Receiver request = new Receiver(Receiver.stringToMap(data));
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

    private static String manageEditUser(String data) throws JsonProcessingException, SQLException {
        Receiver request = new Receiver(Receiver.stringToMap(data));
        String response = null;
        User user = Model.getInstance().getDatabaseDriver().getUserByToken(request.getToken());
        if (user != null) {
            User newUser = new User(request.getName(), request.getEmail(), JWTManager.hashPassword(request.getPassword()), "user", user.getID());
            Model.getInstance().getDatabaseDriver().updateUser(newUser);
            SendAutoEditUser sender = new SendAutoEditUser();
            response = sender.sendText();
        } else {
            response = manageError(request.getAction(), "Usuário não encontrado!");
        }
        return response;
    }

    private static String manageDeleteUser(String data) throws JsonProcessingException, SQLException {
        Receiver request = new Receiver(Receiver.stringToMap(data));
        String response = null;
        User user = Model.getInstance().getDatabaseDriver().getUserByToken(request.getToken());
        if (user != null) {
            Model.getInstance().getDatabaseDriver().deleteUser(user.getID());
            SendAutoEditUser sender = new SendAutoEditUser();
            response = sender.sendText();
        } else {
            response = manageError(request.getAction(), "Usuário não encontrado!");
        }
        return response;
    }

    private static String manageRegisterPoint(String data) throws JsonProcessingException {
        Receiver request = new Receiver(Receiver.stringToMap(data));
        String response = null;
        int id = Integer.parseInt((JWTManager.getUserIdFromToken(request.getToken())));
        try {
            if (UserValidation.validate("admin", id)) {
                Point point = new Point(request.getName(), request.getObs());
                Model.getInstance().getDatabaseDriver().addPoint(point);
                SendRegisterPoint sender = new SendRegisterPoint();
                response = sender.sendText();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private static String manageRequestPointEdit(String data) throws JsonProcessingException, SQLException {
        Receiver request = new Receiver(Receiver.stringToMap(data));
        String response = null;
        Point point = Model.getInstance().getDatabaseDriver().getPointByID(request.getPointID());
        if (point == null) {
            response = manageError(request.getAction(), "Ponto não encontrado!");
        } else {
            SendRequestPointEdit sender = new SendRequestPointEdit();
            response = sender.sendText(point);
        }
        return response;
    }

    private static String managePointList(String data) throws JsonProcessingException {
        Receiver request = new Receiver(Receiver.stringToMap(data));
        String response = null;
        int id = Integer.parseInt((JWTManager.getUserIdFromToken(request.getToken())));
        try {
            if (UserValidation.validate("admin", id)) {
                List<Point> pointList = Model.getInstance().getDatabaseDriver().getPointList();
                if (pointList == null || pointList.isEmpty()) {
                    SendPointList sender = new SendPointList();
                    response = sender.sendText(null);
                } else {
                    SendPointList sender = new SendPointList();
                    response = sender.sendText(pointList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private static String manageEditPoint(String data) throws JsonProcessingException {
        Receiver request = new Receiver(Receiver.stringToMap(data));
        String response = null;
        int adminID = Integer.parseInt((JWTManager.getUserIdFromToken(request.getToken())));
        try {
            if (UserValidation.validate("admin", adminID)) {
                Point point = Model.getInstance().getDatabaseDriver().getPointByID(request.getPointID());
                if (point != null) {
                    point = new Point(request.getName(), request.getObs(), point.getID());
                    Model.getInstance().getDatabaseDriver().updatePoint(point);
                    SendEditPoint sender = new SendEditPoint();
                    response = sender.sendText();
                } else {
                    response = manageError(request.getAction(), "Ponto não encontrado!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private static String manageDeletePoint(String data) throws JsonProcessingException {
        Receiver request = new Receiver(Receiver.stringToMap(data));
        String response = null;
        int adminID = Integer.parseInt((JWTManager.getUserIdFromToken(request.getToken())));
        try {
            if (UserValidation.validate("admin", adminID)) {
                Point point = Model.getInstance().getDatabaseDriver().getPointByID(request.getPointID());
                if (point != null) {
                    Model.getInstance().getDatabaseDriver().deletePoint(request.getPointID());
                    SendDeletePoint sender = new SendDeletePoint();
                    response = sender.sendText();
                } else {
                    response = manageError(request.getAction(), "Ponto não encontrado!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private static String manageRegisterSegment(String data) throws JsonProcessingException {
        Receiver request = new Receiver(Receiver.stringToMap(data));
        String response = null;
        int id = Integer.parseInt((JWTManager.getUserIdFromToken(request.getToken())));
        try {
            if (UserValidation.validate("admin", id)) {
                Segment segment = new Segment(request.getSegment().getPontoOrigem(), request.getSegment().getPontoDestino(), request.getSegment().getDirecao(), request.getSegment().getDistancia(), request.getSegment().getObs());
                Model.getInstance().getDatabaseDriver().addSegment(segment);
                SendRegisterSegment sender = new SendRegisterSegment();
                response = sender.sendText();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private static String manageRequestSegmentEdit(String data) throws JsonProcessingException, SQLException {
        Receiver request = new Receiver(Receiver.stringToMap(data));
        String response = null;
        Segment segment = Model.getInstance().getDatabaseDriver().getSegmentByID(request.getSegmentID());
        if (segment == null) {
            response = manageError(request.getAction(), "Segmento não encontrado!");
        } else {
            SendRequestSegmentEdit sender = new SendRequestSegmentEdit();
            response = sender.sendText(segment);
        }
        return response;
    }

    private static String manageSegmentList(String data) throws JsonProcessingException {
        Receiver request = new Receiver(Receiver.stringToMap(data));
        String response = null;
        int id = Integer.parseInt((JWTManager.getUserIdFromToken(request.getToken())));
        try {
            if (UserValidation.validate("admin", id)) {
                List<Segment> segmentList = Model.getInstance().getDatabaseDriver().getSegmentList();
                if (segmentList == null || segmentList.isEmpty()) {
                    SendSegmentList sender = new SendSegmentList();
                    response = sender.sendText(null);
                } else {
                    SendSegmentList sender = new SendSegmentList();
                    response = sender.sendText(segmentList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private static String manageEditSegment(String data) throws JsonProcessingException {
        Receiver request = new Receiver(Receiver.stringToMap(data));
        String response = null;
        int adminID = Integer.parseInt((JWTManager.getUserIdFromToken(request.getToken())));
        try {
            if (UserValidation.validate("admin", adminID)) {
                Segment segment = Model.getInstance().getDatabaseDriver().getSegmentByID(request.getSegmentID());
                if (segment != null) {
                    segment = new Segment(request.getSegment().getPontoOrigem(), request.getSegment().getPontoDestino(), request.getSegment().getDirecao(), request.getSegment().getDistancia(), request.getObs(), segment.getId());
                    Model.getInstance().getDatabaseDriver().updateSegment(segment);
                    SendEditSegment sender = new SendEditSegment();
                    response = sender.sendText();
                } else {
                    response = manageError(request.getAction(), "Segmento não encontrado!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private static String manageDeleteSegment(String data) throws JsonProcessingException {
        Receiver request = new Receiver(Receiver.stringToMap(data));
        String response = null;
        int adminID = Integer.parseInt((JWTManager.getUserIdFromToken(request.getToken())));
        try {
            if (UserValidation.validate("admin", adminID)) {
                Segment segment = Model.getInstance().getDatabaseDriver().getSegmentByID(request.getSegmentID());
                if (segment != null) {
                    Model.getInstance().getDatabaseDriver().deleteSegment(request.getSegmentID());
                    SendDeleteSegment sender = new SendDeleteSegment();
                    response = sender.sendText();
                } else {
                    response = manageError(request.getAction(), "Segmento não encontrado!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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