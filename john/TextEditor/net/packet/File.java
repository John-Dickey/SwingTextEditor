package john.TextEditor.net.packet;

import java.io.Serializable;
import java.rmi.server.UID;

public class File implements Serializable, Packet
{
	String name, data;//TODO implement uid
	UID uid;
	public File(String name, String data, UID uid)
	{
		this.name = name;
		this.data = data;
		this.uid = uid;
	}
	public boolean equals(File f)//if two files are the same name, but are different files, will still return true
	{
		return uid.equals(f.getUid());
	}
	public String getName()
	{
		return name;
	}
	public String getData()
	{
		return data;
	}
	protected void setData(String s)
	{
		data = s;
	}
	public UID getUid()
	{
		return uid;
	}
}
