package ServerSide.ServerClasses;

import ServerSide.Interfaces.ClientListener;
import ServerSide.Interfaces.ServerInterface;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Garu on 06/11/2014.
 */
public class ServerBase implements ServerInterface {

    protected String name;
    protected int port;
    protected ArrayList<ClientHandler> clientHandlers;
    protected ServerSocket listener = null;
    protected boolean isRunning;

    public ServerBase(String name, int port) {
        this.name = name;
        this.port = port;
        this.isRunning = false;
        this.clientHandlers = new ArrayList<ClientHandler>();
    }

    @Override
    public void startListening() {
        isRunning = true;
        try {
            listener = new ServerSocket(port);
            this.onListeningStarted(port);
            while (isRunning)
                createHandler(listener.accept());

        } catch (IOException e) {
            isRunning = false;
            System.out.println("Errore durante l'avvio del server\nStackTrace:\n" + e.toString());
        }
    }

    @Override
    public void stopListening() throws IOException {
        this.isRunning = false;
        broadcastMessage(null, SERVER_DISCONNECTED);
        for (ClientHandler handler : clientHandlers)
            handler.dispose();
        if (listener != null)
            listener.close();
        this.onListeningStopped();
    }

    @Override
    public void removeHandler(ClientHandler client) {
        client.dispose();
        this.clientHandlers.remove(client);
        this.onClientDisconnected(client);
    }

    @Override
    public void createHandler(Socket socket) {
        ClientHandler handler = new ClientHandler(this, socket);
        this.clientHandlers.add(handler);
        handler.start();
        this.onClientConnected(handler);
    }

    @Override
    public void broadcastMessage(ClientHandler sender, String message) {
        if (message != null)
            for (ClientHandler handler : clientHandlers)
                if (handler != sender)
                    handler.sendMessage(message);
    }

    @Override
    public void onHandlerMessageRecived(ClientHandler client, String message) {
        if (message.equals(ClientListener.CLIENT_NOTIFY_DISCONNECT) || message.equals(ClientListener.CLIENT_NOTIFY_ERROR))
            this.removeHandler(client);
    }

    @Override
    public void onMessageRecived(ClientHandler client, String message, int messageType) {
        synchronized (this) {
            if (messageType == ServerInterface.MESSAGE_TYPE_CLIENT)
                this.onClientMessageRecived(client, message);
            else if (messageType == ServerInterface.MESSAGE_TYPE_NOTIFY)
                this.onHandlerMessageRecived(client, message);
        }
    }

    protected String getFormattedMessage(ClientHandler handler, String message) {
        String formattedMessage = handler.getClientName() + ": " + message;
        return formattedMessage;
    }


    @Override
    public void onClientConnected(ClientHandler client) {
    }

    @Override
    public void onClientDisconnected(ClientHandler client) {
    }

    @Override
    public void onListeningStarted(int port) {
    }

    @Override
    public void onListeningStopped() {
    }

    @Override
    public void onClientMessageRecived(ClientHandler client, String message) {
    }

}
