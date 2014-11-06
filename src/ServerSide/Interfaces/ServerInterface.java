package ServerSide.Interfaces;

import ServerSide.ServerImplementation.ClientHandler;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Garu on 06/11/2014.
 */
public interface ServerInterface extends ClientListener {

    public static String SERVER_DISCONNECTED = "#SERVER_DISCONNECTED";
    public static String SERVER_CONNECTED = "#SERVER_CONNECTED";


    public void startListening();

    public abstract void stopListening() throws IOException;

    public abstract void removeHandler(ClientHandler handler);

    public abstract void createHandler(Socket socket);

    public abstract void broadcastMessage(ClientHandler sender, String message);
}
