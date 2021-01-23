package com.welpnathan.networkserver.models.requests;

import com.welpnathan.networkserver.NetworkClient;
import com.welpnathan.networkserver.models.responses.Response;

public abstract class Request {
    protected String _class;
    protected String identity;

    /**
     * Creates a new extended object of Request.
     * @param requestType The type of request
     * @param identity Client's identity
     */
    public Request(String requestType, String identity) {
        _class = requestType;
        this.identity = identity;
    }

    /**
     * Returns a response from the request.
     * @return Response to return to client
     * @param networkClient Client object
     */
    public abstract Response performRequest(NetworkClient networkClient);
}
