package com.welpnathan.networkserver.models.requests;

import com.welpnathan.networkserver.NetworkClient;
import com.welpnathan.networkserver.models.responses.ErrorResponse;
import com.welpnathan.networkserver.models.responses.Response;
import com.welpnathan.networkserver.models.responses.SuccessResponse;

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
    public Response performRequest(NetworkClient networkClient) {
        boolean success = networkClient.removeChannelSubscription(channel);
        return success
                ? new SuccessResponse()
                : new ErrorResponse("Channel does not exist.");
    }
}
