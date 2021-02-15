package com.welpnathan.networkserver.data;

import com.welpnathan.networkserver.models.Channel;
import com.welpnathan.networkserver.models.Message;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DbConnection implements IDbConnection {

    private Connection connection;
    private final static String DATABASE_URI = "jdbc:sqlite:data.db";

    /**
     * Creates a new instance of the database connection.
     */
    public DbConnection() {
        System.out.println("[DATABASE] Attempting to get connection...");
        connection = getConnection();
        System.out.println("[DATABASE] Connection successful! Initialising database...");

        try {
            initialiseDatabase();
            System.out.println("[DATABASE] Database initialisation successful.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("[DATABASE] Unable to initialise database as there is an SQL error.");
        }
    }

    /**
     * Initialises the database with the tables needed.
     * @throws SQLException If the SQL commands cannot be ran.
     */
    private void initialiseDatabase() throws SQLException {
        PreparedStatement channelSql  = connection.prepareStatement(
            "CREATE TABLE IF NOT EXISTS channels ("
                + "name TEXT PRIMARY KEY,\n"
                + "UNIQUE(name)" // ensures that there can be only 1 channel of the same name
                + ");"
        );
        channelSql.execute();

        // json "when" attribute (message id)
        // payload
        // ensures that there can only be 1 message with id 0, 1, 2, 3, etc
        PreparedStatement messageSql = connection.prepareStatement(
                """
                        CREATE TABLE IF NOT EXISTS messages (id INTEGER PRIMARY KEY,
                        channel TEXT,
                        body TEXT,
                         FOREIGN KEY(channel) REFERENCES channels(name),UNIQUE(id));"""
        );
        messageSql.execute();
    }

    /**
     * Attempts to get the database connection using the SQLite driver.
     * @return SQL Connection
     */
    @Override
    public Connection getConnection() {
        if (connection != null) return connection;

        try {
            Connection connection = DriverManager.getConnection(DATABASE_URI);
            this.connection = connection;
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("[DATABASE] Unable to connect to the database.");
        }
    }

    /**
     * Adds a channel to the database.
     * @param channelName Channel name
     */
    @Override
    public void addChannel(String channelName) {
        try {
            // INSERT OR IGNORE INTO // ignores primary key constraints
            PreparedStatement sql = connection.prepareStatement(
                    "INSERT INTO "
                    + "channels(name) "
                    + "VALUES(?)");
            sql.setString(1, channelName);

            sql.execute();
            // System.out.println("[DATABASE] Added " + channelName + " to the channel database.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("[DATABASE] Unable to add channel " + channelName + " to the database.");
        }
    }

    /**
     * Adds a message to the channel.
     * @param channelName Channel name
     * @param message Message object
     */
    @Override
    public void addMessageToChannel(String channelName, Message message) {
        try {
            PreparedStatement sql = connection.prepareStatement(
                    "INSERT INTO "
                    + "messages(id, channel, body) "
                    + "VALUES(?, ?, ?)"
            );
            sql.setInt(1, message.getTimestamp());
            sql.setString(2, channelName);
            sql.setString(3, message.getBody());

            sql.execute();
            // System.out.println("[DATABASE] Added message ID " + message.getTimestamp() + " to " + channelName + ".");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("[DATABASE] Unable to add message ID " + message.getTimestamp() + " to " + channelName + " to the database.");
        }
    }

    @Override
    public HashMap<String, Channel> getChannelData() {
        HashMap<String, Channel> map = new HashMap<>();

        // get all channels and put them into their respected hashmap
        try {
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM channels;");
            ResultSet rs = sql.executeQuery();

            while(rs.next()) {
                String channelName = rs.getString("name");
                map.put(channelName, new Channel(channelName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("[DATABASE] Unable to select all channels from the database.");
        }

        // fill channel data
        for(Map.Entry<String, Channel> me : map.entrySet()) {
            Channel channel = me.getValue();
            try {
                PreparedStatement sql = connection.prepareStatement("SELECT * FROM messages WHERE channel = ?");
                sql.setString(1, channel.getName());

                ResultSet rs = sql.executeQuery();
                while(rs.next()) {
                    int id = rs.getInt("id");
                    String body = rs.getString("body");
                    channel.addMessage(new Message(channel.getName(), id, body));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("[DATABASE] Unable to get messages from channel " + channel.getName() + ".");
            }
        }

        return map;
    }

    /**
     * Gets the last message timestamp.
     * @return Last message timestamp
     */
    @Override
    public int getLastMessageTimestamp() {
        try {
            PreparedStatement sql = connection.prepareStatement("SELECT COUNT(*) FROM messages;");
            ResultSet rs = sql.executeQuery();

            int timestamp = 0;
            while(rs.next()) {
                timestamp = rs.getInt(1);
            }

            //System.out.println("[DATABASE] The last message timestamp was " + timestamp + ".");
            return timestamp;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("[DATABASE] Unable to get the last message's timestamp.");
        }
    }
}
