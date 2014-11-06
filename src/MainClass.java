import ServerSide.GaruServer;

/**
 * Created by Garu on 06/11/2014.
 */
public class MainClass {

    public static void main(String[] args)
    {
       // new GaruServer("Garu",9999).startListening();


        new Thread(){
            @Override
            public void run() {
                new GaruServer("Garu",9999).startListening();
            }
        }.start();

    }
}
