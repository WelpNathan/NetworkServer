package com.welpnathan.networkserver.models.requests;

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
}
