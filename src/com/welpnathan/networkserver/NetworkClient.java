package com.welpnathan.networkserver;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.welpnathan.networkserver.models.requests.*;
import com.welpnathan.networkserver.models.responses.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class NetworkClient extends Thread {
    private final UUID uuid;
    private String identity;
    private final PrintWriter toClient;
    private final BufferedReader fromClient;
    private final ArrayList<String> channelSubscriptions = new ArrayList<>();

    /**
     * Creates a new instance of NetworkClient.
     * @param socket Client's socket connection
     * @param uuid Unique client number
     */
    public NetworkClient(Socket socket, UUID uuid) throws IOException {
        this.uuid = uuid;
        toClient = new PrintWriter(socket.getOutputStream(), true);
        fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    /**
     * Attempts to get the RequestType from a JSON string.
     * @param inputLine JSON string from client
     * @return Type of request
     */
    private String getRequestType(String inputLine) {
        JsonObject obj = new Gson().fromJson(inputLine, JsonObject.class);
        JsonElement classType = obj.get("_class");
        return classType == null ? null : classType.getAsString();
    }

    public UUID getUuid() { return uuid; }
    public String getIdentity() { return identity; }
    public ArrayList<String> getChannelSubscriptions() { return channelSubscriptions; }

    public void setClientIdentity(String identity) { this.identity = identity; }


    /**
     * Adds a channel name to the client's subscriptions.
     * @param channelName Channel name
     * @return Status if succeeded
     */
    public boolean addChannelSubscription(String channelName) {
        if (NetworkServer.getNetworkState().doesChannelExist(channelName)) {
            if (!channelSubscriptions.contains(channelName)) {
                channelSubscriptions.add(channelName);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a channel name from the client's subscriptions.
     * @param channelName Channel name
     */
    public boolean removeChannelSubscription(String channelName) {
        if (NetworkServer.getNetworkState().doesChannelExist(channelName)) {
            channelSubscriptions.remove(channelName);
            return true;
        }
        return false;
    }

    /**
     * Handles what the client has sent to the server.
     * This essentially performs different actions depending
     * on the request type.
     * @param inputLine JSON string from client
     * @return Type of request
     */
    private Response handleRequest(String inputLine) {
        String requestType = getRequestType(inputLine);

        // create new gson object to convert json to object
        Gson gson = new Gson();

        // perform different actions depending on request type
        return switch (Objects.requireNonNull(requestType)) {
            case "GetRequest" -> {
                Request request = gson.fromJson(inputLine, GetRequest.class);
                yield request.performRequest(this);
            }
            case "OpenRequest" -> {
                Request request = gson.fromJson(inputLine, OpenRequest.class);
                yield request.performRequest(this);
            }
            case "PublishRequest" -> {
                Request request = gson.fromJson(inputLine, PublishRequest.class);
                yield request.performRequest(this);
            }
            case "SubscribeRequest" -> {
                Request request = gson.fromJson(inputLine, SubscribeRequest.class);
                yield request.performRequest(this);
            }
            case "UnsubscribeRequest" -> {
                Request request = gson.fromJson(inputLine, UnsubscribeRequest.class);
                yield request.performRequest(this);
            }
            default -> throw new IllegalStateException("Unexpected value: " + Objects.requireNonNull(requestType));
        };
    }

    /**
     * Constantly waits for client data and then handles it
     * respectively depending on the type of request.
     */
    public void run() {
        try {
            // wait until the client sends us data
            String inputLine;
            while ((inputLine = fromClient.readLine()) != null) {
                System.out.println("[I] " + uuid + " | " + inputLine);

                // get response to return to client
                Response clientResponse = handleRequest(inputLine);

                // get response in json format
                String clientJson = clientResponse.toJson();
                System.out.println("[O] " + uuid + " | " + clientJson);

                // respond to client via stream using JSON
                toClient.println(clientJson);
            }
        } catch (IOException e) {
            System.out.println("[E] " + uuid + " | " + e.getMessage());
        }
    }
}
