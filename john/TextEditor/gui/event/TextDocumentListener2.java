package john.TextEditor.gui.event;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import org.apache.commons.lang3.StringUtils;

import john.TextEditor.gui.MaineWindow;
import john.TextEditor.net.NetworkManager;
import john.TextEditor.net.packet.Addition;
import john.TextEditor.net.packet.Deletion;
import john.TextEditor.net.packet.LogEnrty;
import john.TextEditor.objects.File2;

public class TextDocumentListener2 implements DocumentListener
{
	File2 file;
	NetworkManager netMan;
	boolean notNetwork;
	Addition chillHolderA;
	Deletion chillHolderD;
	public TextDocumentListener2(File2 fil) 
	{
		file = fil;
		netMan = MaineWindow.getInstance().getNetworkManager();
		notNetwork = false;
		chillHolderA = null;
		chillHolderD = null;
	}
	@Override
	public void changedUpdate(DocumentEvent event) 
	{//TODO if "lag" becomes an issue, do this every like 10 times, or something
		if(!file.edited())
			file.setEdited(true);
		try {
			file.setText(event.getDocument().getText(0, event.getDocument().getLength()));
		} catch(BadLocationException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void insertUpdate(DocumentEvent event) 
	{
		try {
			//String change = event.getDocument().getText(event.getOffset(), event.getLength());
			String doc = event.getDocument().getText(0, event.getDocument().getLength());
			String change = doc.substring(event.getOffset(), event.getOffset() + event.getLength());
			Addition a = new Addition(file.getUid(), change, event.getOffset());
			if(chillHolderA != null)
			{
				if(a.getChange().equals(chillHolderA.getChange()) && a.getOffset() == chillHolderA.getOffset()){
					chillHolderA = null;
					return;
				}
			}
			if(netMan.shouldDo())
			{
				//netMan.getClient().send(new LogEnrty("[" + doc.charAt(event.getOffset() - 2) + "]" + "[" + doc.charAt(event.getOffset() - 1) + "]" + "[" + change + "]" + "[" + doc.charAt(event.getOffset() + 1) + "]" + "[" + doc.charAt(event.getOffset() + 2) + "]"));
				netMan.getClient().send(a);
			}
		} catch(BadLocationException e){
			e.printStackTrace();//TODO implement logging
		}
	}

	@Override
	public void removeUpdate(DocumentEvent event) 
	{
		Deletion d = new Deletion(file.getUid(), event.getOffset(), event.getLength());
		if(chillHolderD != null)
		{
			if(d.getLength() == chillHolderD.getLength() && d.getOffset() == chillHolderD.getOffset())
			{
				chillHolderD = null;
				return;
			}
		}
		if(netMan.shouldDo())
		{
			netMan.getClient().send(d);
		}
	}
	public void p(String s)
	{
		System.out.println(s);
	}
	public void chillOneSecA(Addition ad)
	{
		chillHolderA = ad;
	}
	public void chillOneSecD(Deletion del)
	{
		chillHolderD = del;
	}
}
