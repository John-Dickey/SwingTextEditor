package john.TextEditor.net;

import john.TextEditor.net.client.Client;
import john.TextEditor.net.server.Server;

public class NetworkManager 
{
	Client client;
	Server server;
	boolean networkStuff;
	public NetworkManager()
	{
		client = null;
		server = null;
		networkStuff = false;
	}
	public void startServer()
	{
		
	}
	public void startClient()
	{
		
	}
	public Client getClient()
	{
		return client;
	}
	public Server getServer()
	{
		return server;
	}
	public boolean shouldDo()
	{
		return networkStuff;
	}
}
