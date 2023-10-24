package caiofurlan.serverdistributedsystems.system.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{

    private final int PORT;

    public Server(int port) {
        PORT = port;
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor iniciou na porta " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Novo cliente: " + clientSocket);

                // Inicie uma nova thread para tratar a conexão do cliente
                Thread clientThread = new Thread(new UserDialogger(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (serverSocket != null && !serverSocket.isClosed()) {
                System.out.println("Servidor finalizado.");
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}