package john.TextEditor.net.server;

import com.jmr.wrapper.common.Connection;
import com.jmr.wrapper.common.listener.SocketListener;

import john.TextEditor.net.packet.Addition;
import john.TextEditor.net.packet.Deletion;
import john.TextEditor.net.packet.File;

public class ServerListener implements SocketListener
{

	@Override
	public void received(Connection con, Object object) 
	{
		if(object instanceof File)
		{
			File f = (File)object;
			if(FileManager.getInstance().exists(f))
				return;
			FileManager.getInstance().addFile(f);//TODO acknowledge packet, have sub types for add file, addition, etc
			sendToAllExcept(f, con);
		} else if(object instanceof Addition) {
			Addition a = (Addition)object;
			File file = FileManager.getInstance().getFile(a.getUid());
			if(file == null)
				return;
			a.processAddition();
			sendToAllExcept(a, con);
		} else if(object instanceof Deletion) {
			Deletion d = (Deletion)object;
			File file = FileManager.getInstance().getFile(d.getUid());
			if(file == null)
				return;
			d.processDeletion();
			sendToAllExcept(d, con);
		}
	}

	@Override
	public void connected(Connection con) 
	{
		ConnectionManager.getInstance().addConnection(con);
	}

	@Override
	public void disconnected(Connection con) 
	{
		ConnectionManager.getInstance().removeConnection(con);
	}
	private void sendToAllExcept(Object o, Connection con)
	{
		for(Connection c : ConnectionManager.getInstance().getConnections())
		{
			if(c != con)
				c.sendTcp(o);
		}
	}
}
