package com.welpnathan.networkserver.models.requests;

/**
 * An Open request establishes the client's identity and creates a channel
 * by that name if it does not already exist. The request also subscribes the
 * client to its own channel. The request always succeeds.
 * {"_class":"OpenRequest", "identity":"Alice"}
 */
public class OpenRequest extends Request {
    private static final String _class = "OpenRequest";

    public OpenRequest(String identity) {
        super(_class, identity);
    }
}
