package ServerSide.ServerImplementations.ChatServer;

import ServerSide.ServerClasses.ClientHandler;
import ServerSide.ServerClasses.ServerBase;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;


/**
 * Created by Garu on 06/11/2014.
 */
public class ChatServer extends ServerBase {

    public ChatServer(String name, int port) {
        super(name, port);
    }

    private boolean canListen(int port) { // TODO -> Merge in with ServerBase ?
        try {
            ServerSocket testListener = new ServerSocket(port);
            testListener.close();
        } catch (BindException bindException) {
            return false;
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private int getNextAvailablePort(int startingPort) {
        int port = startingPort;
        while (!canListen(port))
            port++;
        return port;
    }

    /*
            I've overwritted this method in order to handle the in-use-port exception
            Now Server automatically get the next free port.
     */

    @Override
    public void startListening() {
        if (canListen(port)) {
            isRunning = true;
            try {
                listener = new ServerSocket(port);
                System.out.println("Server Listening On: " + port);
                while (isRunning)
                    createHandler(listener.accept());

            } catch (IOException e) {
                System.out.println("Errore durante l'avvio del server\nStackTrace:\n" + e.toString());
            }
        } else {
            System.out.println("Errore durante l'avvio del server: la porta " + port + " è utilizzata da un altro applicativo\nRicerca porte libere...");
            port = getNextAvailablePort(port);
            System.out.println("Mi sposto sulla prossima porta libera: " + port);
            startListening();
        }
    }

    @Override
    public void onListeningStopped() {
        System.out.println("Server Stopped");
    }

    @Override
    public void onClientDisconnected(ClientHandler client) {
        System.out.println("#SERVER  \"" + client.getClientName() + "\" Disconnected");
    }

    @Override
    public void onClientConnected(ClientHandler client) {
        System.out.println("#SERVER  \"" + client.getClientName() + "\" Connected");
    }

    @Override
    public void onClientMessageRecived(ClientHandler client, String message) {
        broadcastMessage(client, getFormattedMessage(client, message));
        System.out.println(getFormattedMessage(client, message));
    }
}
