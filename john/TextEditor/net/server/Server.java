package john.TextEditor.net.server;

public class Server 
{
	com.jmr.wrapper.server.Server server;
	
	public Server(int port) throws Exception
	{
		server = new com.jmr.wrapper.server.Server(port, port);
		server.setListener(new ServerListener());
		if (server.isConnected()){
			System.out.println("Server has started.");
		}
	}
	public Server() throws Exception
	{
		this(7000);
	}
}
