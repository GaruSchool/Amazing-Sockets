package ClientSide.ClientClasses;

import ClientSide.Interfaces.ClientMessageListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by t.garuglieri on 14/11/14.
 */
public class ClientReceiver extends Thread {

    public static String MESSAGE_DISCONNECTED = "#DISCONNECTED";


    private ClientMessageListener listener;
    private Socket socket;


    public ClientReceiver(ClientMessageListener listener, Socket socket) {
        this.listener = listener;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!this.isInterrupted()) {
                listener.onMessageRecived(input.readLine(), ClientMessageListener.MESSAGE_TYPE_SERVER);
            }

        } catch (IOException e) {
            notifyDisconnected();
        }

    }


    private void notifyDisconnected() {
        listener.onMessageRecived(MESSAGE_DISCONNECTED, ClientMessageListener.MESSAGE_TYPE_HANDLER);
    }

    public void dispose() {
        this.interrupt();
        this.socket = null;
    }
}
