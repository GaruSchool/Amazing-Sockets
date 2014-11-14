package Console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

public class ConsoleManager {
    public static String getInput() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferRead.readLine();
        } catch (IOException e) {
            return null;
        }
    }
}
