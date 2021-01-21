package com.welpnathan.networkserver.models.requests;

/**
 * A Subscribe request subscribes the client to the named channel. The
 * request fails if the channel does not exist.
 * {"_class":"SubscribeRequest", "identity":"Alice", "channel":"Bob"}
 */
public class SubscribeRequest extends Request {
    private static final String _class = "SubscribeRequest";

    public SubscribeRequest(String identity) {
        super(_class, identity);
    }
}
