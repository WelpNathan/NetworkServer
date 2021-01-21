package com.welpnathan.networkserver.models.requests;

import com.welpnathan.networkserver.models.Message;
import com.welpnathan.networkserver.models.responses.Response;

public class PublishRequest extends Request {
    private static final String _class = "PublishRequest";
    private final Message message;

    /**
     * Creates a new instance of PublishRequest.
     * Used for publishing a message to the client's
     * channel.
     * @param identity Client's identity
     * @param message Client message
     */
    public PublishRequest(String identity, Message message) {
        super(_class, identity);
        this.message = message;
    }

    @Override
    public Response performRequest() {
        return null;
    }
}
