package ServerSide.Interfaces;

import ServerSide.ServerClasses.ClientHandler;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Garu on 06/11/2014.
 */
public interface ServerInterface extends ClientListener {

    /*
                Methods marked with a "!" shouldn't be overridden because they contains calls to
                other non-visible methods, if you needs to override them keep in mind to explore their code
                in order to keep everything going fine.

     */
    public static String SERVER_DISCONNECTED = "#SERVER_DISCONNECTED";
    public static String SERVER_CONNECTED = "#SERVER_CONNECTED";

    public void startListening();

    public abstract void stopListening() throws IOException; // !

    public abstract void removeHandler(ClientHandler handler); // !

    public abstract void createHandler(Socket socket); // !

    public abstract void broadcastMessage(ClientHandler sender, String message); // ! - Pass null as ClientHandler to broadcast the message to everyone

    public abstract void onClientMessageRecived(ClientHandler client, String message);

    public abstract void onHandlerMessageRecived(ClientHandler clientHandler, String message);

    public abstract void onClientConnected(ClientHandler handler);

    public abstract void onClientDisconnected(ClientHandler handler);

    public abstract void onListeningStarted(int port);

    public abstract void onListeningStopped();



}
