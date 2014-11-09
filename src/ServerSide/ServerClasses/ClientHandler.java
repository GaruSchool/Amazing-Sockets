package ServerSide.ServerClasses;

import ServerSide.Interfaces.ClientListener;
import ServerSide.Interfaces.ServerInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Garu on 06/11/2014.
 */
public class ClientHandler extends Thread {

    private static String default_nickname = "UnamedClient";

    private Socket socket;
    private ClientListener listener;
    private String name;
    private boolean isRunning;

    public ClientHandler(ClientListener listener, Socket socket) {
        this.listener = listener;
        this.isRunning = true;
        this.socket = socket;
        name =  default_nickname;
        sendMessage(ServerInterface.SERVER_CONNECTED);
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (isRunning) {
                String input = in.readLine();
                if (input != null)
                    handleMessage(input);
                else
                    notifyClientDisconnect(ClientListener.CLIENT_NOTIFY_DISCONNECT);

            }

        } catch (IOException e) {
            listener.onMessageRecived(this, ClientListener.CLIENT_NOTIFY_ERROR, ClientListener.MESSAGE_TYPE_NOTIFY);
        }

    }

    public void sendMessage(String message) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(message);
        } catch (IOException e) {
            notifyClientDisconnect(ClientListener.CLIENT_NOTIFY_ERROR);
        }
    }

    private void notifyClientDisconnect(String disconnectType) {
        listener.onMessageRecived(this, disconnectType, ClientListener.MESSAGE_TYPE_NOTIFY);
    }

    public void dispose() {
        try {
            if (socket != null)
                socket.close();
            isRunning = false;
        } catch (IOException e) {

        }
    }

    private void handleMessage(String message) {

        listener.onMessageRecived(this, message, ClientListener.MESSAGE_TYPE_CLIENT);

        if (message.equals(ClientListener.CLIENT_NOTIFY_DISCONNECT))
            notifyClientDisconnect(ClientListener.CLIENT_NOTIFY_DISCONNECT);

        if (message.contains(ClientListener.CLIENT_MESSAGE_NAME))
            this.name = message.replace(ClientListener.CLIENT_MESSAGE_NAME, "");
    }


    public String getClientName() {
        return (name.equals(default_nickname) ? getIpAddress() : name);
    }

    public void setClientName(String name) {
        this.name = name;
    }

    public String getIpAddress() {
        return this.socket.getRemoteSocketAddress().toString();
    }

}
