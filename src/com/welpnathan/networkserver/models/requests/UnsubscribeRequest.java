package com.welpnathan.networkserver.models.requests;

/**
 * An Unsubscribe request unsubscribes the client from the named
 * channel. The request fails if the channel does not exist.
 * {"_class":"UnsubscribeRequest", "identity":"Alice", "channel":"Bob"}
 */
public class UnsubscribeRequest extends Request {
    private static final String _class = "UnsubscribeRequest";

    public UnsubscribeRequest(String identity) {
        super(_class, identity);
    }
}
