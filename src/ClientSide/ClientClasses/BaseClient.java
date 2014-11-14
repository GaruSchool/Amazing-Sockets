package ClientSide.ClientClasses;

import ClientSide.Interfaces.ClientInterface;
import ClientSide.Interfaces.ClientMessageListener;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by cccp on 14/11/2014.
 */
public class BaseClient implements ClientInterface {

    private String name;
    private Socket socket = null;
    private ClientReceiver clientReceiver;
    private boolean isConnected;

    public BaseClient(String name) {
        this.name = name;
    }

    @Override
    public void connect(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            clientReceiver = new ClientReceiver(this, socket);
            clientReceiver.start();
            isConnected = true;
            notifyConnected();
        } catch (IOException e) {
            clientReceiver.interrupt();
        }
    }


    @Override
    public void sendMessage(String message) {
        if (socket != null)
            try {
                new PrintWriter(socket.getOutputStream(), true).println(message);
                onMessageSent(message);
            } catch (IOException e) {
                if (clientReceiver != null)
                    clientReceiver.interrupt();
                clientReceiver = null;
            }
    }


    @Override
    public void onHandlerMessageRecived(String message) {
        if (message.equals(ClientReceiver.MESSAGE_DISCONNECTED)) {
            if (clientReceiver != null)
                this.clientReceiver.dispose();
            isConnected = false;
            notifyDisconnected();
        }
    }

    @Override
    public void onMessageRecived(String message, int messageType) {
        if (messageType == ClientMessageListener.MESSAGE_TYPE_SERVER)
            onClientMessageRecived(message);
        else if (messageType == ClientMessageListener.MESSAGE_TYPE_HANDLER)
            onHandlerMessageRecived(message);
    }


    @Override
    public void notifyDisconnected() {
    }

    @Override
    public void notifyConnected() {
        sendMessage("#NICKNAME " + name);
    }

    @Override
    public void onClientMessageRecived(String message) {
    }

    @Override
    public void onMessageSent(String message) {
    }

    public boolean isConnected() {
        return this.isConnected;
    }
}
