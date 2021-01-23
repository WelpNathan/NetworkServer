package com.welpnathan.networkserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

@SuppressWarnings("InfiniteLoopStatement")
public class NetworkServer {
    private static final int portNumber = 12345;
    private static final NetworkState networkState = new NetworkState();

    /**
     * Creates a new client in the system using the
     * client's assigned socket.
     * @param clientSocket Client's socket
     */
    private static NetworkClient createNewClient(Socket clientSocket) {
        UUID clientUuid = UUID.randomUUID();
        System.out.println("New client detected. Assigned client UUID " + clientUuid + ".");

        // attempt to create a new client from ClientHandler
        NetworkClient networkClient = null;
        try {
            networkClient = new NetworkClient(clientSocket, clientUuid);
        } catch (IOException e) {
            System.out.println("Unable to add client" + clientUuid + " to the server.");
            e.printStackTrace();
        }

        return networkClient;
    }

    /**
     * Gets the network's state.
     * @return Network state.
     */
    public static NetworkState getNetworkState() {
        return networkState;
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
            // client from that socket connection
            while (true) {
                Socket socket = serverSocket.accept();
                NetworkClient networkClient = createNewClient(socket);
                networkState.registerNewClient(networkClient); // initialise thread
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
