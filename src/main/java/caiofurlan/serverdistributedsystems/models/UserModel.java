package caiofurlan.serverdistributedsystems.models;

public class UserModel {
    private String name;
    private String email;
    private String password;
    private String type;
    private long userID;

    public UserModel(String name, String email, String password, String type) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
    }
    public UserModel(String name, String email, String password, String type, long userID) {
        this.name = name;
        this.email = email;
        this.type = type;
        this.password = password;
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public long getUserID(){
        return userID;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setUserID(long userID){
        this.userID = userID;
    }

    public void setAdmin(String admin){
        this.type = admin;
    }

    public boolean isAdmin() {
        return this.type.equals("admin");
    }

}