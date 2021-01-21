package com.welpnathan.networkserver;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.welpnathan.networkserver.models.responses.SuccessResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket client;
    private String clientUuid;
    private PrintWriter toClient;
    private BufferedReader fromClient;

    /**
     * Creates a new instance of ClientHandler.
     * @param client Client's socket connection
     * @param clientUuid Unique client number
     * @throws IOException
     */
    public ClientHandler(Socket client, String clientUuid) throws IOException {
        this.client = client;
        this.clientUuid = clientUuid;
        toClient = new PrintWriter(client.getOutputStream(), true);
        fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    /**
     * Attempts to get the RequestType from a JSON string.
     * @param json
     * @return Type of request
     */
    public String GetRequestType(String json) {
        JsonObject obj = new Gson().fromJson(json, JsonObject.class);
        JsonElement classType = obj.get("_class");
        return classType == null ? null : classType.getAsString();
    }

    /**
     * Constantly waits for client data and then handles it
     * respectively depending on the type of request.
     */
    public void run() {
        try {
            String inputLine;

            // wait until the client sends us data
            while ((inputLine = fromClient.readLine()) != null) {
                System.out.println("[CLIENT-DATA] Request from " + clientUuid + " is " + inputLine + ".");

                String requestType = GetRequestType(inputLine);
                System.out.println("Request type is: " + requestType);

                // respond back to the client with something
                toClient.println(new SuccessResponse().toJson());
            }
        } catch (IOException e) {
            System.out.println("[CLIENT-EXCEPTION] Client " + clientUuid + " exception: " + e.getMessage());
        }
    }
}
