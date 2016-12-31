package john.TextEditor.net.packet;

import java.io.Serializable;

public class LogEnrty implements Serializable, Packet
{
	String msg;
	public LogEnrty(String s)
	{
		msg = s;
	}
	public String getMsg()
	{
		return msg;
	}
}
