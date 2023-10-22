package caiofurlan.serverdistributedsystems.system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class JsonHandler {
    public static String handleRequest(Socket socket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String inputLine;
        StringBuilder jsonBuilder = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            jsonBuilder.append(inputLine);
        }

        // Imprimir o JSON no console
        String receivedJson = jsonBuilder.toString();
        System.out.println("JSON recebido do client: " + receivedJson);
        return receivedJson;
    }

    public static void sendJson(Socket socket, String json) throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(json);
    }
}