package ServerSide.ServerImplementations.LoggingServer;

import ServerSide.Interfaces.ClientListener;
import ServerSide.ServerClasses.ClientHandler;
import ServerSide.ServerClasses.ServerBase;

import java.util.ArrayList;

/**
 * Created by cccp on 07/11/2014.
 */
public class LoggingServer extends ServerBase {

    private ArrayList<LoggedMessage> messages = new ArrayList<LoggedMessage>();

    public LoggingServer(String name, int port) {
        super(name, port);
    }

    @Override
    public void onMessageRecived(ClientHandler handler, String message, int messageType) {
        super.onMessageRecived(handler, message, messageType);

        if (messageType == ClientListener.MESSAGE_TYPE_CLIENT)
            messages.add(new LoggedMessage(message, handler.getClientName(), handler.getIpAddress()));

        if (messageType == ClientListener.MESSAGE_TYPE_CLIENT && message.equals("#LOG"))
            printMessages();
    }

    private void printMessages() {
        for (LoggedMessage messagel : messages)
            System.out.println(messagel.getSenderIP() + " - " + messagel.getSenderNickname() + ": " + messagel.getMessage());
    }
}
