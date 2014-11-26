package Test;

import ClientSide.ClientClasses.BaseClient;
import ClientSide.ClientImplementations.ChatClient;
import Helpers.ConsoleManager;

import java.io.IOException;

/**
 * < - Client Test Class - >
 */
public class ClientTest {

    public static void main(String[] args) throws IOException {
        BaseClient myClient = new ChatClient("Garu");
        myClient.connect("127.0.0.1", 9999);

        String input = "";
        while (input != "#DISCONNECT" && myClient.isConnected()) {
            input = ConsoleManager.getInput();
            myClient.sendMessage(input);
        }

    }
}
