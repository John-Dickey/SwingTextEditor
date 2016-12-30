package john.TextEditor.net.client;

import javax.swing.text.BadLocationException;

import com.jmr.wrapper.common.Connection;
import com.jmr.wrapper.common.listener.SocketListener;

import john.TextEditor.gui.MaineWindow;
import john.TextEditor.net.packet.Addition;
import john.TextEditor.net.packet.Deletion;
import john.TextEditor.net.packet.File;
import john.TextEditor.net.server.FileManager;
import john.TextEditor.objects.File2;

public class ClientListener implements SocketListener
{

	@Override
	public void received(Connection con, Object object) 
	{
		if(object instanceof File)
		{
			File f = (File)object;
			if(FileManager.getInstance().exists(f))
				return;
			MaineWindow.getInstance().getFileManager().openFile(f);
		} else if(object instanceof Addition) {
			Addition a = (Addition)object;
			try {
				MaineWindow.getInstance().getFileManager().handleAddition(a);
			} catch(BadLocationException e) {
				e.printStackTrace();
			}
		} else if(object instanceof Deletion) {
			Deletion d = (Deletion)object;
			try {
				MaineWindow.getInstance().getFileManager().handleDeletion(d);
			} catch(BadLocationException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void connected(Connection con) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnected(Connection con) {
		// TODO Auto-generated method stub
		
	}

}
