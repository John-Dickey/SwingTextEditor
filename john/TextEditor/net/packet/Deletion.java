package john.TextEditor.net.packet;

import java.io.Serializable;
import java.rmi.server.UID;

import john.TextEditor.net.server.FileManager;

public class Deletion implements Serializable, Packet
{
	UID uid;
	int offset;
	int length;
	public Deletion(UID u, int off, int len)
	{
		uid = u;
		offset = off;
		length = len;
	}
	public UID getUid() {
		return uid;
	}
	public int getOffset() {
		return offset;
	}
	public int getLength() {
		return length;
	}
	public void processDeletion()
	{
		File f = FileManager.getInstance().getFile(uid);
		StringBuilder sb = new StringBuilder(f.getData());
		sb.delete(offset, offset+length-1);
		f.setData(sb.toString());
	}
}
