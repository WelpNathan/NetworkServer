package com.welpnathan.networkserver.models.responses;

import com.welpnathan.networkserver.models.Message;

import java.util.ArrayList;

/**
 * A MessageList response is sent in reply to a Get request. The response
 * provides a list of messages ordered by timestamp. The list of messages
 * may be empty.
 * {"_class":"MessageListResponse", "messages":[]}
 * {"_class":"MessageListResponse", "messages":[{"_class":"Message", "from":"Bob",
 * "when":47, "body":"Hello again!"}, {"_class":"Message", "from":"Alice", "when":53,
 * "body":"pic attached, base64 encoded, LOL",
 * "pic":"iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAMAAAAoLQ9TAAAAxlBMVEUAAADWAEX/"}]}
 */
public class MessageListResponse extends Response {
    private static final String _class = "MessageListResponse";
    private final ArrayList<Message> messages;

    /**
     * Creates a new instance of MessageListResponse.
     */
    public MessageListResponse(ArrayList<Message> messages) {
        super(_class);
        this.messages = messages;
    }
}
