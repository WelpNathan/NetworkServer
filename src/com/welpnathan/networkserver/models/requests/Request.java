package com.welpnathan.networkserver.models.requests;

import com.google.gson.Gson;
import com.welpnathan.networkserver.NetworkServer;
import com.welpnathan.networkserver.NetworkState;
import com.welpnathan.networkserver.models.responses.Response;

public abstract class Request {
    protected String _class;
    protected String identity;
    protected final NetworkState networkState = NetworkServer.getNetworkState();

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
     * Converts the extended object to a JSON string format.
     * @return JSON String
     */
    public String toJson() {
        return new Gson().toJson(this);
    }

    /**
     * Returns a response from the request.
     * @return Response to return to client
     */
    public abstract Response performRequest();
}
