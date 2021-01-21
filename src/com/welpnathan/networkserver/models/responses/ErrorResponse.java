package com.welpnathan.networkserver.models.responses;

public class ErrorResponse extends Response {
    private static final String _class = "ErrorResponse";
    private final String error;

    /**
     * Creates a new instance of MessageListResponse.
     * @param error Error message
     */
    public ErrorResponse(String error) {
        super(_class);
        this.error = error;
    }
}
