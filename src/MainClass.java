import ServerSide.ServerImplementations.LoggingServer.LoggingServer;

/**
 * Created by Garu on 06/11/2014.
 */
public class MainClass {
    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                new LoggingServer("Garu", 9999).startListening();
            }
        }.start();
    }
}
