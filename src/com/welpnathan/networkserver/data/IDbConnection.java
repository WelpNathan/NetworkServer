package com.welpnathan.networkserver.data;

import com.welpnathan.networkserver.models.Channel;
import com.welpnathan.networkserver.models.Message;

import java.sql.Connection;
import java.util.HashMap;

public interface IDbConnection {
    Connection getConnection();

    void addChannel(String channelName);
    void addMessageToChannel(String channelName, Message message);

    HashMap<String, Channel> getChannelData();
    int getLastMessageTimestamp();
}
