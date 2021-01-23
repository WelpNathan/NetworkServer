package com.welpnathan.networkserver.models;

import java.util.ArrayList;

public class Channel {
    private final String name;
    private final ArrayList<Message> messages = new ArrayList<>();

    /**
     * Creates a new Channel object.
     * @param name Name of channel
     */
    public Channel(String name) {
        this.name = name;
    }

    /**
     * Adds a new message to the channel.
     * @param message Message object
     */
    public void addMessage(Message message) {
        messages.add(message);
    }

    /**
     * Gets the messages after a specific timestamp.
     * @param timestamp Timestamp of message
     * @return Array of messages
     */
    public ArrayList<Message> getMessages(int timestamp) {
        if (timestamp == 0) return messages;

        // create array of messages that apply to the timestamp
        ArrayList<Message> messagesToSend = new ArrayList<>();

        // check each message and see if the timestamp applies
        for(Message message : messages) {
            if (message.getTimestamp() > timestamp) {
                messagesToSend.add(message);
            }
        }

        // return messages that apply
        return messages;
    }
}
