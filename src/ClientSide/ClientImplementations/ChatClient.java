package ClientSide.ClientImplementations;

import ClientSide.ClientClasses.BaseClient;

/**
 * Created by cccp on 14/11/2014.
 */
public class ChatClient extends BaseClient {
    public ChatClient(String name) {
        super(name);
    }

    @Override
    public void notifyConnected() {
        super.notifyConnected();
        System.out.println("#Connesso al server");
    }

    @Override
    public void notifyDisconnected() {
        System.out.println("#Disconnesso dal server");
    }

    @Override
    public void onClientMessageRecived(String message) {
        System.out.println("Server: " + message);
    }

    @Override
    public void onMessageSent(String message) {
        System.out.println("Tu: " + message);
    }
}
