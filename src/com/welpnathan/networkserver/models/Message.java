package com.welpnathan.networkserver.models;

import com.google.gson.Gson;

public class Message {
    private static final String _class = "Message";
    private final String from;
    private final int when;
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
     * Converts the message to a JSON string format.
     * @return JSON String
     */
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
