package Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Set;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final Set<Socket> clients;
    private String clientName;
    private PrintWriter out;

    public ClientHandler(Socket clientSocket, Set<Socket> clients) {
        this.clientSocket = clientSocket;
        this.clients = clients;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Ask client to choose a username
            out.println("Please choose your username:");

            // Read the username from the client
            clientName = in.readLine();
            System.out.println("User " + clientName + " connected.");

            // Notify all clients that a new user has joined
            broadcast(clientName + " has joined the chat.");

            String clientMessage;
            while ((clientMessage = in.readLine()) != null) {
                System.out.println("Received from " + clientName + ": " + clientMessage);
                broadcast(clientName + ": " + clientMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clients.remove(clientSocket);
                clientSocket.close();
                // Notify all clients that this user has left
                broadcast(clientName + " has left the chat.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void broadcast(String message) {
        for (Socket client : clients) {
            if (client != clientSocket) {
                try {
                    PrintWriter clientOut = new PrintWriter(client.getOutputStream(), true);
                    clientOut.println(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


