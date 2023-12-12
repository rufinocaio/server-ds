package caiofurlan.serverdistributedsystems.system.connection;

import caiofurlan.serverdistributedsystems.models.Model;
import caiofurlan.serverdistributedsystems.system.connection.receive.UserDialogActions;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UserDialogger implements Runnable {
    private final Socket clientSocket;
    private final ObjectMapper objectMapper;

    public UserDialogger(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            UserDialogActions userDialogActions = new UserDialogActions(clientSocket.getInetAddress().toString());
            Model.getInstance().getSessionManager().addSession(clientSocket.getInetAddress().toString());
            String message = null;
            while ((message = reader.readLine()) != null) {
                JsonNode jsonNode = objectMapper.readTree(message);
                String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
                System.out.println("JSON recebido do cliente: \n" + prettyJson);
                String action = jsonNode.get("action").asText();
                String response = userDialogActions.chooseAction(action, message);
                jsonNode  = objectMapper.readTree(response);
                prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
                System.out.println("Enviando para o cliente: \n" + prettyJson);
                writer.println(response);
            }
        } catch (IOException e) {
            try {
                Model.getInstance().getSessionManager().removeSessions(clientSocket.getInetAddress().toString());
                System.out.println("Socket closed!");
                clientSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
