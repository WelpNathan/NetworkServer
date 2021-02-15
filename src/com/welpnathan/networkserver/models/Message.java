package com.welpnathan.networkserver.models;

import com.google.gson.Gson;

public class Message {
    private final String _class = "Message";
    private final String from;
    private int when;
    private final String body;

    /**
     * Creates a new instance of Message.
     * TODO: Needs work to support more variables.
     * @param from The identity of the client
     * @param when Int timestamp
     * @param body Payload of message
     */
    public Message(String from, int when, String body) {
        this.from = from;
        this.when = when;
        this.body = body;
    }

    /**
     * Sets the message's timestamp.
     * @param timestamp New timestamp
     */
    public void setTimestamp(int timestamp) {
        when = timestamp;
    }

    /**
     * Returns the message's timestamp.
     * @return Message timestamp
     */
    public int getTimestamp() {
        return when;
    }

    /**
     * Returns the message's body.
     * @return Message body
     */
    public String getBody() {
        return body;
    }
}
