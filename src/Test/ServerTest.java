package Test;

import ServerSide.ServerClasses.ServerBase;
import ServerSide.ServerImplementations.ChatServer.SimpleChatServer;

/**
 * < - Server Test Class - >
 */
public class ServerTest {
    public static void main(String[] args) {
        ServerBase serverBase = new SimpleChatServer("ServerName", 9999);
        serverBase.startListening();
    }
}
