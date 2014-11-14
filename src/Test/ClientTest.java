package Test;

import ClientSide.ClientClasses.BaseClient;
import ClientSide.ClientImplementations.ChatClient;
import Console.ConsoleManager;

import java.io.IOException;

/**
 * Created by cccp on 14/11/2014.
 */
public class ClientTest {

    public static void main(String[] args) throws IOException {
        BaseClient myClient = new ChatClient("Garu");
        myClient.connect("127.0.0.1", 9999);


        String input = "";
        while (input != null && myClient.isConnected()) {
            input = ConsoleManager.getInput();
            myClient.sendMessage(input);
        }

    }


}
