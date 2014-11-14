package ClientSide.Interfaces;

import java.io.IOException;

/**
 * Created by t.garuglieri on 14/11/14.
 */
public interface ClientInterface extends ClientMessageListener {

    public abstract void connect(String ip, int port) throws IOException;

    public abstract void sendMessage(String message) throws IOException;

    public abstract void notifyDisconnected();

    public abstract void notifyConnected();

    public abstract void onHandlerMessageRecived(String message); // !

    public abstract void onClientMessageRecived(String message);

    public abstract void onMessageSent(String message);

}
