package ClientSide.ClientImplementations;

import ClientSide.ClientClasses.BaseClient;

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

public class ChatClient extends BaseClient {
    public ChatClient(String name) {
        super(name);
    }

    @Override
    public void onConnected() {
        super.onConnected();
        System.out.println("#Connesso al server");
    }

    @Override
    public void onDisconnected() {
        System.out.println("#Disconnesso dal server");
    }

    @Override
    public void onClientMessageRecived(String message) {
        System.out.println("Server: " + message);
    }

    @Override
    public void onMessageSent(String message) {
        System.out.println("Tu: " + message);
    }
}
