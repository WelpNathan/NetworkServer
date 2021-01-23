package com.welpnathan.networkserver.models.requests;

import com.welpnathan.networkserver.NetworkClient;
import com.welpnathan.networkserver.NetworkServer;
import com.welpnathan.networkserver.models.responses.Response;
import com.welpnathan.networkserver.models.responses.SuccessResponse;

public class OpenRequest extends Request {
    private static final String _class = "OpenRequest";

    /**
     * Creates a new instance of OpenRequest.
     * Used for establishing a client's identity and
     * creates a NetworkState channel by that name.
     * @param identity Client's identity
     */
    public OpenRequest(String identity) {
        super(_class, identity);
    }

    @Override
    public Response performRequest(NetworkClient networkClient) {
        networkClient.setClientIdentity(identity);
        NetworkServer.getNetworkState().addChannelIfNotExist(identity);
        networkClient.addChannelSubscription(identity);
        return new SuccessResponse();
    }
}
