package ServerSide;

import ServerSide.ServerImplementation.ClientHandler;
import ServerSide.ServerImplementation.ServerBase;

import java.io.IOException;
import java.net.Socket;


/**
 * Created by Garu on 06/11/2014.
 */
public class GaruServer extends ServerBase {

    public GaruServer(String name, int port) {
        super(name, port);
    }

    @Override
    public void startListening() {
        System.out.println("Server Started On Port: " + port);
        super.startListening();
    }

    @Override
    public void stopListening() throws IOException {
        super.stopListening();
        System.out.println("Server Stopped");
    }

    @Override
    public void removeHandler(ClientHandler handler) {
        super.removeHandler(handler);
        System.out.println("#SERVER # " + handler.getClientName() + " # Disconnected");
    }

    @Override
    public void createHandler(Socket socket) {
        super.createHandler(socket);
        System.out.println("Client Connected - " + socket.getLocalAddress().toString());
    }

    @Override
    public void broadcastMessage(ClientHandler sender, String message) {
        super.broadcastMessage(sender, message);
    }

    @Override
    public void onMessageRecived(ClientHandler handler, String message, int messageType) {
        super.onMessageRecived(handler, message, messageType);
        String oMessage = (getFormattedMessage(handler,message,messageType)==null) ? message : getFormattedMessage(handler,message,messageType);
        System.out.println(oMessage);
    }

}
