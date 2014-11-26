package ClientSide.ClientClasses;

import ClientSide.Interfaces.ClientMessageListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Amazing Sockets Created by Tommaso Garuglieri
 * GitHub Repository: https://github.com/GaruSchool/Amazing-Sockets/
 *
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

public class ClientReceiver extends Thread {

    public static String MESSAGE_DISCONNECTED = "#DISCONNECTED";

    private ClientMessageListener listener;
    private Socket socket;

    public ClientReceiver(ClientMessageListener listener, Socket socket) {
        this.listener = listener;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!this.isInterrupted()) {
                listener.onMessageRecived(input.readLine(), ClientMessageListener.MESSAGE_TYPE_SERVER);
            }

        } catch (IOException e) {
            notifyDisconnected();
        }
    }

    private void notifyDisconnected() {
        listener.onMessageRecived(MESSAGE_DISCONNECTED, ClientMessageListener.MESSAGE_TYPE_HANDLER);
    }

    public void dispose() {
        this.interrupt();
        this.socket = null;
    }
}
