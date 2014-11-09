import ServerSide.ServerImplementations.BasicServerImpl.BasicEchoServer;

/**
 * Created by Garu on 06/11/2014.
 */

public class MainClass {
    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                new BasicEchoServer("Garu", 9999).startListening();
            }
        }.start();
    }
}
