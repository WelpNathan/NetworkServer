package com.welpnathan.networkserver.models.requests;

import com.welpnathan.networkserver.models.responses.Response;

public class SubscribeRequest extends Request {
    private static final String _class = "SubscribeRequest";
    private final String channel;

    /**
     * Creates a new instance of SubscribeRequest.
     * Used for subscribing to the named channel.
     * @param identity Client's identity
     * @param channel Channel name
     */
    public SubscribeRequest(String identity, String channel) {
        super(_class, identity);
        this.channel = channel;
    }

    @Override
    public Response performRequest() {
        return null;
    }
}
