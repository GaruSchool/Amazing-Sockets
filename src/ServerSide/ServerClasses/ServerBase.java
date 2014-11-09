package ServerSide.ServerClasses;

import ServerSide.Interfaces.ClientListener;
import ServerSide.Interfaces.ServerInterface;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Amazing Sockets Created by Tommaso Garuglieri
 *
 * GitHub Repository: https://github.com/GaruSchool/Amazing-Sockets/
 * Contact: garuglieritommaso@gmail.com
 *
 * <Amazing Sockets - Java Socket Interface to simplify the communication.>
 * Copyright (C) <2014>  <Tommaso Garuglieri>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

public class ServerBase implements ServerInterface {

    protected String name;
    protected int port;
    protected ArrayList<ClientHandler> clientHandlers;
    protected ServerSocket listener = null;
    protected boolean isRunning;

    public ServerBase(String name, int port) {
        this.name = name;
        this.port = port;
        this.isRunning = false;
        this.clientHandlers = new ArrayList<ClientHandler>();
    }

    /*
       When you override startListening() Method keep in mind to call onListeningStarted(int)
       as your socket start the listening on the selected port
    */
    @Override
    public void startListening() {
        isRunning = true;
        try {
            listener = new ServerSocket(port);
            this.onListeningStarted(port);
            while (isRunning)
                createHandler(listener.accept());

        } catch (IOException e) {
            isRunning = false;
            System.out.println("Errore durante l'avvio del server\nStackTrace:\n" + e.toString());
        }
    }

    /*
        When you override stopListening() Method keep in mind to call onListeningStopped()
        At the end of your implementation
     */
    @Override
    public void stopListening() throws IOException {
        this.isRunning = false;
        broadcastMessage(null, SERVER_DISCONNECTED);
        for (ClientHandler handler : clientHandlers)
            handler.dispose();
        if (listener != null)
            listener.close();
        this.onListeningStopped();
    }

    /*
       When you override removeHandler(ClientHandler) Method keep in mind to call onClientDisconnected(ClientHandler)
       as your handler get disconnected.
    */
    @Override
    public void removeHandler(ClientHandler client) {
        client.dispose();
        this.clientHandlers.remove(client);
        this.onClientDisconnected(client);
    }

    /*
     When you override createHandler(ClientHandler) Method keep in mind to call onClientConnected(ClientHandler)
     as your handler get linked to your server.
  */
    @Override
    public void createHandler(Socket socket) {
        ClientHandler handler = new ClientHandler(this, socket);
        this.clientHandlers.add(handler);
        handler.start();
        this.onClientConnected(handler);
    }

    /*
     When you override the broadcastMessage(ClientHandler,String) method you should use the
     sendMessage(String) method in the ClientHandler class in order to have a fast and error safe communication.
     -> In case of error the sendMessage(String) methods notify the Server that client got an error, so the server
        can disconnect the client automatically
  */
    @Override
    public void broadcastMessage(ClientHandler sender, String message) {
        if (message != null)
            for (ClientHandler handler : clientHandlers)
                if (handler != sender)
                    handler.sendMessage(message);
    }

    /*
        When you override the method onHandlerMessageRecived(ClientHandler,String) you should ALWAYS
        call the super.onHandlerMessageRecived(ClientHandler,String) in order to allow the server to
        use handle client notifications.
     */
    @Override
    public void onHandlerMessageRecived(ClientHandler client, String message) {
        if (message.equals(ClientListener.CLIENT_NOTIFY_DISCONNECT) || message.equals(ClientListener.CLIENT_NOTIFY_ERROR))
            this.removeHandler(client);
    }

    /*
        You should NEVER override this method as it provides the concurrent access form every ClientHandler
        to the server and it handle automatically every message.
     */

    @Override
    public void onMessageRecived(ClientHandler client, String message, int messageType) {
        synchronized (this) {
            if (messageType == ServerInterface.MESSAGE_TYPE_CLIENT)
                this.onClientMessageRecived(client, message);
            else if (messageType == ServerInterface.MESSAGE_TYPE_NOTIFY)
                this.onHandlerMessageRecived(client, message);
        }
    }

    /*
          Override this method if you need a different message formatting.
     */
    protected String getFormattedMessage(ClientHandler handler, String message) {
        String formattedMessage = handler.getClientName() + ": " + message;
        return formattedMessage;
    }


    /*
          You NEED to override this methods:
     */
    @Override
    public void onClientConnected(ClientHandler client) {
    }

    @Override
    public void onClientDisconnected(ClientHandler client) {
    }

    @Override
    public void onListeningStarted(int port) {
    }

    @Override
    public void onListeningStopped() {
    }

    @Override
    public void onClientMessageRecived(ClientHandler client, String message) {
    }

}
