package caiofurlan.serverdistributedsystems.system.connection;

import caiofurlan.serverdistributedsystems.models.Model;
import caiofurlan.serverdistributedsystems.models.UserModel;

import java.util.Objects;

public class UserValidation {
    public static boolean validate(String type, long userID) throws Exception {
        UserModel user = Model.getInstance().getDatabaseDriver().getUserByID(userID);
        if (!Objects.equals(user.getType(), type)) {
            throw new Exception("Não autorizado");
        }

        return true;
    }
}
