package ClientSide.ClientClasses;

import ClientSide.Interfaces.ClientInterface;
import ClientSide.Interfaces.ClientMessageListener;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Amazing Sockets Created by Tommaso Garuglieri
 * <p/>
 * GitHub Repository: https://github.com/GaruSchool/Amazing-Sockets/
 * Contact: garuglieritommaso@gmail.com
 * <p/>
 * <Amazing Sockets - Java Socket Interface to simplify the communication.>
 * Copyright (C) <2014>  <Tommaso Garuglieri>
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

public class BaseClient implements ClientInterface {

    private String name;
    private Socket socket = null;
    private ClientReceiver clientReceiver;
    private boolean isConnected;

    public BaseClient(String name) {
        this.name = name;
    }

    @Override
    public void connect(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            clientReceiver = new ClientReceiver(this, socket);
            clientReceiver.start();
            isConnected = true;
            onConnected();
        } catch (IOException e) {
            clientReceiver.dispose();
        }
    }


    @Override
    public void sendMessage(String message) {
        if (socket != null)
            try {
                new PrintWriter(socket.getOutputStream(), true).println(message);
                onMessageSent(message);
            } catch (IOException e) {
                if (clientReceiver != null)
                    clientReceiver.dispose();
                clientReceiver = null;
            }
    }


    @Override
    public void onHandlerMessageRecived(String message) {
        if (message.equals(ClientReceiver.MESSAGE_DISCONNECTED)) {
            if (clientReceiver != null)
                this.clientReceiver.dispose();
            isConnected = false;
            onDisconnected();
        }
    }

    @Override
    public void onMessageRecived(String message, int messageType) {
        if (messageType == ClientMessageListener.MESSAGE_TYPE_SERVER)
            onClientMessageRecived(message);
        else if (messageType == ClientMessageListener.MESSAGE_TYPE_HANDLER)
            onHandlerMessageRecived(message);
    }


    @Override
    public void onDisconnected() {
    }

    @Override
    public void onConnected() {
        sendMessage("#NICKNAME " + name);
    }

    @Override
    public void onClientMessageRecived(String message) {
    }

    @Override
    public void onMessageSent(String message) {
    }

    public boolean isConnected() {
        return this.isConnected;
    }
}
