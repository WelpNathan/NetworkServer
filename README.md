# NetworkServer
Simple publish-subscribe server for SHU Networked Software Development module. Second-year project. Powered by Java and GSON.

!!! MPORTANT NOTICE !!!
The application was created via OpenJDK 15.0.2. Other JDKs, especially lower versions may not work with the program.

Contents of Archive
- lib: contains all required libraries for application
- src: contains source code for the application
- testclient: SHU supplied test client

Persistence Extension
- Persistence is implemented via the use of SQLite. It creates 'data.db' to store channels and messages.

Compiling and Running the NetworkServer.
- IMPORTANT NOTICE: Again, this was created via OpenJDK 15.0.2 which uses new features such as switch yeilding.
- COMPILE: javac -cp ".;lib/gson-2.8.6.jar;lib/sqlite-jdbc-3.34.0.jar" src/com/welpnathan/networkserver/*.java src/com/welpnathan/networkserver/data/*.java src/com/welpnathan/networkserver/models/*.java src/com/welpnathan/networkserver/models/requests/*.java src/com/welpnathan/networkserver/models/responses/*.java
- RUNNING: java -cp "src;lib/gson-2.8.6.jar;lib/sqlite-jdbc-3.34.0.jar" com/welpnathan/networkserver/NetworkServer

Compiling and Running the TestClient
- COMPILE: javac -classpath ".;.\json-simple-1.1.1.jar" *.java
- RUNNING: java -classpath ".;.\json-simple-1.1.1.jar" DemoClient localhost 12345 1 L
