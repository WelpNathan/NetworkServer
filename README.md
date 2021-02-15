# NetworkServer
Simple publish-subscribe server for SHU Networked Software Development module. Second-year project. Powered by Java and GSON.

## Important Notice
The application was created via `OpenJDK 15.0.2`. Other JDKs, **especially** lower versions may not work with the program.

## Contents of Archive
The application is split up into three main sections.
- **lib**: contains all required libraries for application. i.e `GSON` and `SQLite` driver
- **src**: contains source code for the application.
    - **com/welpnathan/networkserver**: main package
        - **data**: contains database connection code
            - **DbConnection**: SQLite database connection code
            - **IDbConnection**: interface to allow other databases such as `MySQL`
        - **models**: contains models of data
            - **requests**: contains request code
                - **GetRequest**: request messages from a specific timestamp
                - **OpenRequest**: request a new identity (opens channels)
                - **PublishRequest**: request that a new message be published to a channel
                - **Request**: abstract class for requests to implement
                - **SubscribeRequest**: request that the client would like to subscribe to a channel
                - **UnsubscribeRequest**: request that the client would like to unsubscribe from a channel
            - **responses**: contains response code
                - **ErrorResponse**: response when a request errors
                - **MessageListResponse**: response when using a `GetRequest`
                - **Response**: abstract class for responses to implement
                - **SuccessResponse**: response when using requests such as `PublishRequest`
            - **Channel**: contains channel login
            - **Message**: contains messsage login
- **testclient**: University supplied test client.

## Persistence Extension
- Persistence is implemented via the use of **SQLite**. It creates `data.db` to store messages and channels.
- The application uses an interface `IDbConnection` to allow more databases such as MySQL to be developed.

## Compiling and Running the NetworkServer.
Again, this was created via `OpenJDK 15.0.2` which uses new features such as `switch yeilding`.
**Compiling NetworkServer**: 
Ensure your `java` command links to `OpenJDK 15.0.2`.
```
javac -cp ".;lib/gson-2.8.6.jar;lib/sqlite-jdbc-3.34.0.jar" src/com/welpnathan/networkserver/*.java src/com/welpnathan/networkserver/data/*.java src/com/welpnathan/networkserver/models/*.java src/com/welpnathan/networkserver/models/requests/*.java src/com/welpnathan/networkserver/models/responses/*.java
```

**Running NetworkServer**: 
Ensure your `java` command links to `OpenJDK 15.0.2`.
```
java -cp "src;lib/gson-2.8.6.jar;lib/sqlite-jdbc-3.34.0.jar" com/welpnathan/networkserver/NetworkServer
```

## Compiling and Running the TestClient
TestClient is a university supplied program and so I'm not entirely sure what version it was designed for. I was able to run it using `OpenJDK 15.0.2` without any issues.
**Compiling TestClient**
Ensure your `java` command links to `OpenJDK 15.0.2`.
```
javac -classpath ".;.\json-simple-1.1.1.jar" *.java
```

**Running TestClient**
Ensure your `java` command links to `OpenJDK 15.0.2`.
```
java -classpath ".;.\json-simple-1.1.1.jar" DemoClient localhost 12345 1 R
java -classpath ".;.\json-simple-1.1.1.jar" DemoClient localhost 12345 1 L
java -classpath ".;.\json-simple-1.1.1.jar" DemoClient localhost 12345 1 H
```
