package com.welpnathan.networkserver.models.requests;

import com.welpnathan.networkserver.models.responses.Response;

public class GetRequest extends Request {
    private static final String _class = "GetRequest";
    private final int after;

    /**
     * Creates a new instance of GetRequest.
     * @param identity Client's identity
     * @param after Get messages after that timestamp.
     */
    public GetRequest(String identity, int after) {
        super(_class, identity);
        this.after = after;
    }

    @Override
    public Response performRequest() {
        return null;
    }
}
