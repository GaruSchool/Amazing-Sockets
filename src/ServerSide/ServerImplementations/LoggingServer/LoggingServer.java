package ServerSide.ServerImplementations.LoggingServer;

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
    public void onClientMessageRecived(ClientHandler client, String message) {
        messages.add(new LoggedMessage(message, client.getClientName(), client.getIpAddress()));
        if (message.equals("#LOG"))
            printMessages();
    }

    private void printMessages() {
        for (LoggedMessage messagel : messages)
            System.out.println(messagel.getSenderIP() + " - " + messagel.getSenderNickname() + ": " + messagel.getMessage());
    }
}
