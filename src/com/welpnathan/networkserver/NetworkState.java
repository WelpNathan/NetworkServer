package com.welpnathan.networkserver;

import com.welpnathan.networkserver.models.Channel;
import com.welpnathan.networkserver.models.Message;

import java.util.HashMap;
import java.util.UUID;

public class NetworkState {
    // reference's the clients connected to the server
    private final HashMap<UUID, NetworkClient> clients = new HashMap<>();

    // reference's the channels available with string
    // being the channel's name
    private final HashMap<String, Channel> channels = new HashMap<>();

    // reference's the current message timestamp
    private int currentTimestamp = 0;

    // reference's how many characters can be in the body and extra fields combined
    private static int MESSAGE_LIMIT = 1000;

    /**
     * Adds a new client to the network and
     * starts the associated thread.
     * @param networkClient Client object
     */
    public synchronized void registerNewClient(NetworkClient networkClient) {
        clients.put(networkClient.getUuid(), networkClient);
        networkClient.start();
    }

    /**
     * Adds a new channel to the state.
     * @param channelName Channel's name
     */
    public synchronized void addChannelIfNotExist(String channelName) {
        if (!doesChannelExist(channelName)) {
            channels.put(channelName, new Channel(channelName));
        }
    }

    /**
     * Adds a new message to the network state.
     * Done by using the publish request.
     * @param channelName Channel's name
     * @param message Message object
     */
    public synchronized boolean addMessageToChannel(String channelName, Message message) {
        if (!doesChannelExist(channelName)) return false;

        // TODO: calculate if extra fields are over the variable
        if (message.getBody().length() > MESSAGE_LIMIT) return false;

        message.setTimestamp(currentTimestamp);
        currentTimestamp++;
        channels.get(channelName).addMessage(message);
        return true;
    }

    /**
     * Returns if the channel exists.
     * @return Channel exist status
     */
    public synchronized boolean doesChannelExist(String channelName) {
        return channels.containsKey(channelName);
    }

    /**
     * Gets the channel from channel name.
     * @param channelName Channel name.
     * @return Channel object.
     */
    public synchronized Channel getChannel(String channelName) {
        return channels.get(channelName);
    }
}
