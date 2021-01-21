package com.welpnathan.networkserver.models.requests;

import com.welpnathan.networkserver.models.responses.Response;

public class UnsubscribeRequest extends Request {
    private static final String _class = "UnsubscribeRequest";
    private final String channel;

    /**
     * Creates a new instance of UnsubscribeRequest.
     * Used for unsubscribing to the named channel.
     * @param identity Client's identity
     * @param channel Channel name
     */
    public UnsubscribeRequest(String identity, String channel) {
        super(_class, identity);
        this.channel = channel;
    }

    @Override
    public Response performRequest() {
        return null;
    }
}
