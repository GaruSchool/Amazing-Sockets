Amazing-Sockets
===============

Java Modular Implementation of a client-server socket based architecture

The only class that you need to create an Amazing Sockets based server is the BaseServer class.

 First Step) Create a class that extends BaseServer and override the methods that you need.

 The interface methods that you can override safetly:

    void onClientMessageRecived(ClientHandler client, String message)
    Called when the server recive a message by a client (Scroll below for other informations about the ClientHandler structure).


    void onHandlerMessageRecived(ClientHandler client, String message);
      Called when the server get notified by an error/disconnection by a ClientHandler, usually you shouldn't override this method.
      If for some reasons you need to override this you should ALWAYS  call the super.onHandlerMessageRecived(ClientHandler,String) in order to allow the server to handle client notifications.


    void onClientConnected(ClientHandler client);
    Called when the client open a connection with your server and a new ClientHandler get instantiated.

    void onClientDisconnected(ClientHandler client);
      Called when the client crash or close the connection with your server.

    void onListeningStarted(int port);
       Called when your server start waiting for connection on the selected port.


    public abstract void onListeningStopped();
      Called when your server stop waiting for connection and it free the relative port.
      
OTHER METHODS



