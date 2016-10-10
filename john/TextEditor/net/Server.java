package john.TextEditor.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.text.Document;

public class Server implements Runnable
{
	ArrayList<Doc> documents;
	ArrayList<Connection> connections;
	ServerSocket socket;
	boolean flag;
	public Server() throws IOException
	{
		documents = new ArrayList<Doc>();
		connections = new ArrayList<Connection>();
		socket = new ServerSocket(6969);
	}
	public void run()
	{
		flag = true;
		while(flag)
		{
			try {
				Socket s = socket.accept();
				connections.add(new Connection(s));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private class Doc
	{
		String name;
		Document document;
		public Doc(String n, Document d)
		{
			name = n;
			document = d;
		}
	}
	private class Connection
	{
		Socket socket;
		public Connection(Socket s)
		{
			socket = s;
		}
		
	}
}
