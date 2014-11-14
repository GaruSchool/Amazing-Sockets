package ClientSide.Interfaces;

/**
 * Created by t.garuglieri on 14/11/14.
 */
public interface ClientMessageListener {
    public static int MESSAGE_TYPE_SERVER = 1;
    public static int MESSAGE_TYPE_HANDLER = 2;

    public abstract void onMessageRecived(String message, int messageType);
}
