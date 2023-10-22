package caiofurlan.serverdistributedsystems.models;

public class ConnectionModel {
    private final String port;

    public ConnectionModel(String port) {
        this.port = port;
    }

    public boolean validate() throws Exception {
        if (port == null || port.isEmpty()) {
            throw new Exception("Porta é obrigatório");
        }

        return true;
    }

    public String getPort() {
        return port;
    }

}