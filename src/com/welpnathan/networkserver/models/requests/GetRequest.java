package com.welpnathan.networkserver.models.requests;

import com.welpnathan.networkserver.NetworkClient;
import com.welpnathan.networkserver.NetworkServer;
import com.welpnathan.networkserver.NetworkState;
import com.welpnathan.networkserver.models.Channel;
import com.welpnathan.networkserver.models.Message;
import com.welpnathan.networkserver.models.responses.MessageListResponse;
import com.welpnathan.networkserver.models.responses.Response;

import java.util.ArrayList;

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
    public Response performRequest(NetworkClient networkClient) {
        NetworkState state = NetworkServer.getNetworkState();
        ArrayList<String> channelSubscriptions = networkClient.getChannelSubscriptions();
        ArrayList<Message> allMessages = new ArrayList<>();

        // get channel objects and get messages
        for (String channelName : channelSubscriptions) {
            Channel channel = state.getChannel(channelName);
            ArrayList<Message> messages = channel.getMessages(after);
            allMessages.addAll(messages);
        }

        return new MessageListResponse(allMessages);
    }
}
