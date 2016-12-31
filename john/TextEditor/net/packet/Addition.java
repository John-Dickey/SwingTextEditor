package john.TextEditor.net.packet;

import java.io.Serializable;
import java.rmi.server.UID;

import john.TextEditor.net.server.FileManager;

public class Addition implements Serializable, Packet
{
	UID uid;
	String change;
	int offset;
	public Addition(UID u, String chng, int off)
	{
		uid = u;
		change = chng;
		offset = off;
	}
	public UID getUid() {
		return uid;
	}
	public String getChange() {
		return change;
	}
	public int getOffset() {
		return offset;
	}
	public void processAddition()
	{
		File f = FileManager.getInstance().getFile(uid);
		StringBuilder sb = new StringBuilder(f.getData());
		sb.insert(offset, change);
		f.setData(sb.toString());
	}
}
