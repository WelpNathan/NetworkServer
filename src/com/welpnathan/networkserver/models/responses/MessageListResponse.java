package com.welpnathan.networkserver.models.responses;

import com.welpnathan.networkserver.models.Message;

import java.util.ArrayList;

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
