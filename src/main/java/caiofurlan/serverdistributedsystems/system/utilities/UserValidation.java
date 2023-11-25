package caiofurlan.serverdistributedsystems.system.utilities;

import caiofurlan.serverdistributedsystems.models.Model;
import caiofurlan.serverdistributedsystems.models.User;

import java.util.Objects;

public class UserValidation {
    public static boolean validate(String type, long userID) throws Exception {
        User user = Model.getInstance().getDatabaseDriver().getUserByID(userID);
        if (!Objects.equals(user.getType(), type)) {
            throw new Exception("Não autorizado");
        }
        return true;
    }
}
