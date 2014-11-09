package ServerSide.Interfaces;

import ServerSide.ServerClasses.ClientHandler;

import java.io.IOException;
import java.net.Socket;

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

public interface ServerInterface extends ClientListener {

    /*
                Methods marked with a "!" shouldn't be overridden because they contains calls to
                other non-visible methods, if you needs to override them keep in mind to explore their code
                in order to keep everything going fine.

     */

    public static String SERVER_DISCONNECTED = "#SERVER_DISCONNECTED";
    public static String SERVER_CONNECTED = "#SERVER_CONNECTED";

    public void startListening();

    public abstract void stopListening() throws IOException; // !

    public abstract void removeHandler(ClientHandler client); // !

    public abstract void createHandler(Socket socket); // !

    public abstract void broadcastMessage(ClientHandler sender, String message); // ! - Pass null as ClientHandler to broadcast the message to everyone

    public abstract void onClientMessageRecived(ClientHandler client, String message);

    public abstract void onHandlerMessageRecived(ClientHandler client, String message);

    public abstract void onClientConnected(ClientHandler client);

    public abstract void onClientDisconnected(ClientHandler client);

    public abstract void onListeningStarted(int port);

    public abstract void onListeningStopped();

}
