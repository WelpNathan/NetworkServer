package com.welpnathan.networkserver;

import java.util.HashMap;
import java.util.UUID;

public class NetworkState {
    private final HashMap<UUID, Client> clients = new HashMap<>();

    /**
     * Adds a new client to the network and
     * starts the associated thread.
     * @param client Client object
     */
    public void registerNewClient(Client client) {
        clients.put(client.getClientUuid(), client);
        client.start();
    }

    /**
     * Adds a new message to the network state.
     * Done by using the publish request.
     */
    public void addNewMessage() {
        // TODO: Implement
    }
}
