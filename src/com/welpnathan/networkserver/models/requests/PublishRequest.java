package com.welpnathan.networkserver.models.requests;

/**
 * A Publish request publishes a message on the client's channel. The
 * timestamp of the message is ignored; the server will issue its own
 * timestamp for the message when storing it. The request fails if the
 * channel does not exist or if the message is too big.
 * {"_class":"PublishRequest", "identity":"Alice", "message":{"_class":"Message",
 * "from":"Bob", "when":0, "body":"Hello again!"}}
 */
public class PublishRequest extends Request {
    private static final String _class = "PublishRequest";

    public PublishRequest(String identity) {
        super(_class, identity);
    }
}
