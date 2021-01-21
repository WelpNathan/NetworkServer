package com.welpnathan.networkserver.models.responses;

/**
 * An Error response indicates that the request failed and provides a
 * reason why.
 * {"_class":"ErrorResponse", "error":"NO SUCH CHANNEL: Bob"}
 * {"_class":"ErrorResponse", "error":"MESSAGE TOO BIG: 1234 characters"}
 * {"_class":"ErrorResponse", "error":"INVALID REQUEST: [{]"}
 */
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
