package Test;


import ServerSide.ServerClasses.ServerBase;
import ServerSide.ServerImplementations.BasicServerImpl.BasicEchoServer;

public class ServerTest {
    public static void main(String[] args) {

        ServerBase serverBase = new BasicEchoServer("ServerName", 9999);
        serverBase.startListening();

    }
}
