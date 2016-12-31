package john.TextEditor.net.client;

import john.TextEditor.net.packet.Packet;

public class Client 
{
	com.jmr.wrapper.client.Client client;
	public Client(String ip, int port, boolean connect)
	{
		client = new com.jmr.wrapper.client.Client(ip, port, port);
		client.setListener(new ClientListener());
		if(connect)
			client.connect();
	}
	public void send(Packet p)
	{
		client.getServerConnection().sendTcp(p);
	}
	public void connect()
	{
		client.connect();
	}
	public void stop()
	{
		client.close();
	}
	public boolean isConnected()
	{
		return client.isConnected();
	}
}
