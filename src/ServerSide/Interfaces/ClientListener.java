package ServerSide.Interfaces;

import ServerSide.ServerClasses.ClientHandler;

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

public interface ClientListener {
    public static int MESSAGE_TYPE_CLIENT = 1;
    public static int MESSAGE_TYPE_NOTIFY = 2;
    public static int MESSAGE_TYPE_OTHER = 3;

    public static String CLIENT_NOTIFY_ERROR = "#CLIENT_IO_ERROR";
    public static String CLIENT_NOTIFY_DISCONNECT = "#CLIENT_DISCONNECTED";
    public static String CLIENT_MESSAGE_NAME = "#NICKNAME";

    public abstract void onMessageRecived(ClientHandler client, String message, int messageType);
}
