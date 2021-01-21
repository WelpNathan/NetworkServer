package com.welpnathan.networkserver.models;

import com.google.gson.Gson;

/**
 * A message is encoded as a JSON object with fields from (the sender of the
 * message), when (a non-negative integer serving as the timestamp when the
 * message was received by the server) and body (the text of the message).
 * {"_class":"Message", "from":"Bob", "when":47, "body":"Hello again!"}
 *
 * Beyond these fields, a message may have additional fields, such as the field
 * pic below (for a picture attachment, base64-encoded).
 * {"_class":"Message", "from":"Alice", "when":53, "body":"pic attached, base64 encoded,
 * LOL", "pic":"iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAMAAAAoLQ9TAAAAxlBMVEUAAADWAEX/"}
 */
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
