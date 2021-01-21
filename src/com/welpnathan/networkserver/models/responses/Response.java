package com.welpnathan.networkserver.models.responses;

import com.google.gson.Gson;

public abstract class Response {
    protected String _class;

    /**
     * Creates a new extended object of Response.
     * @param responseType The type of response
     */
    public Response(String responseType) { _class = responseType; }

    /**
     * Converts the extended object to a JSON string format.
     * @return JSON String
     */
    public String toJson() {
        return new Gson().toJson(this);
    }
}
