package ServerSide.ServerImplementations.BasicServerImpl;

import ServerSide.ServerClasses.ClientHandler;
import ServerSide.ServerClasses.ServerBase;

/**
 * Created by Garu on 09/11/2014.
 */

public class BasicEchoServer extends ServerBase {

    public BasicEchoServer(String name, int port) {
        super(name, port);
    }

    @Override
    public void onClientMessageRecived(ClientHandler client, String message) {
        client.sendMessage(message);
    }

    @Override
    public void onListeningStarted(int port) {
        System.out.println("Server Started, Listening on port: " + port);
    }
}
