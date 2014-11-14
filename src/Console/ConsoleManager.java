package Console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by cccp on 14/11/2014.
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
