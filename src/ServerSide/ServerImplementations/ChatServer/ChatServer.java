package ServerSide.ServerImplementations.ChatServer;

import ServerSide.ServerClasses.ClientHandler;
import ServerSide.ServerClasses.ServerBase;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Created by Garu on 06/11/2014.
 */
public class ChatServer extends ServerBase {

    public ChatServer(String name, int port) {
        super(name, port);
    }

    private static boolean canListen(int port) {
        try {
            ServerSocket testListener = new ServerSocket(port);
            testListener.close();
        } catch (BindException bindException) {
            return false;
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private static int getNextAvailablePort(int startingPort) {
        int port = startingPort;
        while (!canListen(port))
            port++;
        return port;
    }

    @Override
    public void startListening() {
        if (canListen(port)) {
            isRunning = true;
            try {
                listener = new ServerSocket(port);
                while (isRunning)
                    createHandler(listener.accept());

            } catch (IOException e) {
                System.out.println("Errore durante l'avvio del server\nStackTrace:\n" + e.toString());
            }
        } else {
            System.out.println("Errore durante l'avvio del server: la porta " + port + " è utilizzata da un altro applicativo\nRicerca porte libere...");
            port = getNextAvailablePort(port);
            System.out.println("Mi sposto sulla prossima porta libera: " + port);
            startListening();
        }
    }

    @Override
    public void stopListening() throws IOException {
        super.stopListening();
        System.out.println("Server Stopped");
    }

    @Override
    public void removeHandler(ClientHandler handler) {
        super.removeHandler(handler);
        System.out.println("#SERVER  \"" + handler.getClientName() + "\" Disconnected");
    }

    @Override
    public void createHandler(Socket socket) {
        super.createHandler(socket);
        System.out.println("Client Connected - " + socket.getLocalAddress().toString());
    }

    @Override
    public void onClientMessageRecived(ClientHandler client, String message) {
        broadcastMessage(client,getFormattedMessage(client,message));
    }
}
