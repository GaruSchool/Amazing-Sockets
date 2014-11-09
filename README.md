**Amazing-Sockets
===============**

**Java Modular Implementation of a client-server socket based architecture**

The only class that you need to create an Amazing Sockets based server is the BaseServer class.

> First of all you have to create a class that extends BaseServer Class and override the methods that you need.

### Interface methods that you can override safetly:


	void onClientMessageRecived(ClientHandler client, String message)
It gets called when the server recive a new message from a client 
(Scroll down for more informations about ClientHandler structure).

	void onClientConnected(ClientHandler client)
Gets called when a new client connect to the server and his Handler get instantiated.

	void onClientDisconnected(ClientHandler client)
Gets called when a client get disconnected form the server.

	void onListeningStarted(int port)
Gets called when the startListening() method of the server is called without errors

	void onListeningStopped()
Gets called when the server stop listening on the selected port.





