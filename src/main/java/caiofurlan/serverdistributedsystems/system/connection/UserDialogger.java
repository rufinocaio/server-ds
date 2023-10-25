package caiofurlan.serverdistributedsystems.system.connection;

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
            String message = null;
            while ((message = reader.readLine()) != null) {
                System.out.println("JSON recebido do cliente: " + message);

                JsonNode jsonNode = objectMapper.readTree(message);
                String action = jsonNode.get("action").asText();
                String response = UserDialogActions.chooseAction(action, message);
                System.out.println("Enviando para o cliente: " + response);
                writer.println(response);
            }
        } catch (IOException e) {
            try {
                System.out.println("Socket fechado!");;
                clientSocket.close();
            } catch (IOException ex) {
                e.printStackTrace();
            }
        }
    }
}
