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
	public void startServer(int port) throws Exception
	{
		server = new Server(port);
	}
	public void startServer() throws Exception
	{
		startServer(7000);
	}
	public void startClient(String ip, int port)
	{
		client = new Client(ip, port, true);
		networkStuff = true;
	}
	public void startClient(String ip)
	{
		startClient(ip, 7000);
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
