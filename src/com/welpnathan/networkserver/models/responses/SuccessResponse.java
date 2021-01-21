package com.welpnathan.networkserver.models.responses;

/**
 * A Success response simply indicates that the request succeeded.
 * {"_class":"SuccessResponse"}
 */
public class SuccessResponse extends Response {
    private static final String _class = "SuccessResponse";

    /**
     * Creates a new instance of SuccessResponse.
     */
    public SuccessResponse() { super(_class); }
}
