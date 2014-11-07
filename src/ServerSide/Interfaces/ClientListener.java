package ServerSide.Interfaces;

import ServerSide.ServerClasses.ClientHandler;

/**
 * Created by Garu on 06/11/2014.
 */
public interface ClientListener {
    public static int MESSAGE_TYPE_CLIENT = 1;
    public static int MESSAGE_TYPE_NOTIFY = 2;
    public static int MESSAGE_TYPE_OTHER = 3;

    public static String CLIENT_NOTIFY_ERROR = "#CLIENT_IO_ERROR";
    public static String CLIENT_NOTIFY_DISCONNECT = "#CLIENT_DISCONNECTED";
    public static String CLIENT_MESSAGE_NAME = "#NICKNAME";

    public abstract void onMessageRecived(ClientHandler handler, String message, int messageType);
}
