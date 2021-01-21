package com.welpnathan.networkserver.models.requests;

/**
 * A Get request retrieves all messages that were published on any channel
 * to which the client is currently subscribed, in the order in which they
 * were published. The field after specifies a timestamp, and the server will
 * only retrieve messages that were published strictly after that timestamp.
 * If the timestamp is 0 the server will retrieve all messages from the
 * subscribed channels. The request never fails, but it may return an empty
 * list of messages.
 * {"_class":"GetRequest", "identity":"Alice", "after":42}
 */
public class GetRequest extends Request {
    private static final String _class = "GetRequest";

    public GetRequest(String identity) {
        super(_class, identity);
    }
}
