package ServerSide.ServerImplementations;

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
    protected ArrayList<ClientHandler> clientHandlers = new ArrayList<ClientHandler>();
    protected ServerSocket listener = null;
    protected boolean isRunning;

    public ServerBase(String name, int port) {
        this.name = name;
        this.port = port;
        isRunning = false;
    }

    @Override
    public void startListening() {
        isRunning = true;
        try {
            listener = new ServerSocket(port);
            while (isRunning)
                createHandler(listener.accept());

        } catch (IOException e) {
            System.out.println("Errore durante l'avvio del server\nStackTrace:\n" + e.toString());
        }
    }

    @Override
    public void stopListening() throws IOException {

        broadcastMessage(null, SERVER_DISCONNECTED);

        for (ClientHandler handler : clientHandlers)
            handler.dispose();

        if (listener != null)
            listener.close();
    }

    @Override
    public void removeHandler(ClientHandler handler) {
        handler.dispose();
        this.clientHandlers.remove(handler);
    }

    @Override
    public void createHandler(Socket socket) {
        ClientHandler handler = new ClientHandler(this, socket);
        this.clientHandlers.add(handler);
        handler.start();
    }

    @Override
    public void broadcastMessage(ClientHandler sender, String message) {
        if (message != null)
            for (ClientHandler handler : clientHandlers)
                if (handler != sender)
                    handler.sendMessage(message);
    }

    @Override
    public void onMessageRecived(ClientHandler handler, String message, int messageType) {
        synchronized (this) {
            if (messageType == ClientListener.MESSAGE_TYPE_NOTIFY)
                if (message.equals(ClientListener.CLIENT_NOTIFY_DISCONNECT) || message.equals(ClientListener.CLIENT_NOTIFY_ERROR))
                    removeHandler(handler);

            this.broadcastMessage(handler, getFormattedMessage(handler, message, messageType));
        }
    }

    protected String getFormattedMessage(ClientHandler handler, String message, int messageType) {
        if (messageType == ClientListener.MESSAGE_TYPE_CLIENT) {
            String formattedMessage = handler.getClientName() + ": " + message;
            return formattedMessage;
        } else
            return null;
    }

}
