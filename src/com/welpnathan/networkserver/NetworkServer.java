package com.welpnathan.networkserver;

/*
 * How the application works:
 *  - Clients have to establish their identity
 *    by typing o [Username] which allows them
 *    to publish a message to that channel they
 *    set as [Username].
 *
 *  - Clients can subscribe to other people's
 *    channels by doing s [Username] and can
 *    unsubscribe by typing u [Username].
 *
 *  - To get the messages the clients have
 *    subscribed to, they simply type g.
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

public class NetworkServer {
    private static final int portNumber = 12345;

    /**
     * Creates a new client in the system using the
     * client's assigned socket.
     * @param clientSocket Client's socket
     */
    private static void CreateNewClient(Socket clientSocket) {
        String clientUuid = UUID.randomUUID().toString();

        // attempt to create a new client from ClientHandler
        try {
            new ClientHandler(clientSocket, clientUuid).start();
            System.out.println("[NEW-CLIENT] New client detected. Assigned client UUID " + clientUuid + ".");
        } catch (IOException e) {
            System.out.println("[NEW-CLIENT ERROR] Unable to add client" + clientUuid + " to the server.");
            System.out.println(e);
        }
    }

    /**
     * Entry point into program. Sets up the server socket
     * and client connections.
     */
    public static void main(String[] args) {
        try {
            // open a new socket with the specified port number
            ServerSocket serverSocket = new ServerSocket(portNumber);

            // constantly await client socket connections and create new
            // handler from that socket connection
            while (true) CreateNewClient(serverSocket.accept());
        } catch (IOException e) {
            System.out.println("[SERVER-ERROR] Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
