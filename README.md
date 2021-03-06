###Amazing-Sockets


**Java Modular Implementation of a client-server socket based architecture**

The only class that you need to create an Amazing Sockets based server is the BaseServer class.

> First of all you have to create a class that extends BaseServer Class and override the methods that you need.

**Interface methods that you can override safetly:**


	void onClientMessageRecived(ClientHandler client, String message)
It gets called when the server recives a new message from a client 
(Scroll down for more informations about ClientHandler structure).

	void onClientConnected(ClientHandler client)
Gets called when a new client is connected to the server and his Handler get instantiated.

	void onClientDisconnected(ClientHandler client)
Gets called when a client gets disconnected form the server.

	void onListeningStarted(int port)
Gets called when the startListening() method of the server is called without errors

	void onListeningStopped()
Gets called when the server stops listening on the selected port.

## **Other Methods (THAT YOU SHOULDN'T TOUCH!)**

When you override startListening() Method remember to call onListeningStarted(int)
as your socket starts the listening on the selected port

	void startListening()
   
When you override stopListening() Method remember to to call onListeningStopped()
At the end of your implementation

	void stopListening() throws IOException
    
When you override removeHandler(ClientHandler) Method remember to to call onClientDisconnected(ClientHandler) as your handler get disconnected.

	void removeHandler(ClientHandler client)
    
When you override createHandler(ClientHandler) Method remember to to call onClientConnected(ClientHandler) as your handler get linked to your server.   

	void createHandler(Socket socket)
    
When you override the broadcastMessage(ClientHandler,String) method you should use the
sendMessage(String) method in the ClientHandler class in order to have a faster and error-free communication.
-> In case of error the sendMessage(String) methods notify the Server that the client got an error, so the server will disconnect the client automatically

	void broadcastMessage(ClientHandler sender, String message)
    
    
When you override the method onHandlerMessageRecived(ClientHandler,String) you should **always** call the super.onHandlerMessageRecived(ClientHandler,String) in order to allow the server to handle client notifications.

	onHandlerMessageRecived(ClientHandler client, String message)
    
You should **never** override this method as it provides the concurrent access form every ClientHandler to the server and it handles automatically every message.

	void onMessageRecived(ClientHandler client, String message, int messageType)	
    
Override this method if you need a different message formatting.

    String getFormattedMessage(ClientHandler handler, String message)
    
**Echo Server Implementation Example**

	public class BasicEchoServer extends ServerBase {
	   public BasicEchoServer(String name, int port) {
	    super(name, port);
	    }

	    @Override
	    public void onClientMessageRecived(ClientHandler client, String message) {
	       client.sendMessage(message);
	   }
	}

**Chat Server Implementation Example**

	public class SimpleChatServer extends ServerBase {

    	public SimpleChatServer(String name, int port) {
        	super(name, port);
    	}

    	@Override
    	public void onClientMessageRecived(ClientHandler client, String message) {
        	String formattedMessage = getFormattedMessage(client, message);
        	broadcastMessage(client, formattedMessage);
        	System.out.println(formattedMessage);
    	}

    	@Override
    	public void onHandlerMessageRecived(ClientHandler client, String message) {
        	super.onHandlerMessageRecived(client, message);
        	System.out.println("#" + message);
    	}
	}
