package Test;

import ClientSide.BaseClient;
import ServerSide.ServerImplementations.BasicServerImpl.BasicEchoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Garu on 06/11/2014.
 */

public class MainClass {
    public static void main(String[] args) throws IOException {
        new Thread() {
            @Override
            public void run() {
                new BasicEchoServer("Garu", 9999).startListening();
            }
        }.start();

        BaseClient myClient = new BaseClient("Garu");
        myClient.connect("127.0.0.1", 9999);

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferRead.readLine();
        myClient.sendMessage(s);
    }
}
