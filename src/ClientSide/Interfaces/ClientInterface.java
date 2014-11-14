package ClientSide.Interfaces;

import java.io.IOException;

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

public interface ClientInterface extends ClientMessageListener {

    public abstract void connect(String ip, int port) throws IOException;

    public abstract void sendMessage(String message) throws IOException;

    public abstract void notifyDisconnected();

    public abstract void notifyConnected();

    public abstract void onHandlerMessageRecived(String message); // !

    public abstract void onClientMessageRecived(String message);

    public abstract void onMessageSent(String message);

}
