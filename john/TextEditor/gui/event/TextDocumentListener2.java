package john.TextEditor.gui.event;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import john.TextEditor.gui.MaineWindow;
import john.TextEditor.net.NetworkManager;
import john.TextEditor.net.packet.Addition;
import john.TextEditor.objects.File2;

public class TextDocumentListener2 implements DocumentListener
{
	File2 file;
	NetworkManager netMan;
	boolean notNetwork;
	Addition chillHolder;
	public TextDocumentListener2(File2 fil) 
	{
		file = fil;
		netMan = MaineWindow.getInstance().getNetworkManager();
		notNetwork = false;
		chillHolder = null;
	}
	@Override
	public void changedUpdate(DocumentEvent event) 
	{//TODO if "lag" becomes an issue, do this every like 10 times, or something
		p("10");
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
			Addition a = new Addition(file.getUid(), event.getDocument().getText(event.getOffset(), event.getLength()), event.getOffset());
			if(a.equals(chillHolder)){
				a = null;
				return;
			}
			if(netMan.shouldDo())
			{
				netMan.getClient().send(a);
			}
		} catch(BadLocationException e){
			e.printStackTrace();//TODO implement logging
		}
	}

	@Override
	public void removeUpdate(DocumentEvent event) 
	{
		
	}
	public void p(String s)
	{
		System.out.println(s);
	}
	public void chillOneSec(Addition ad)
	{
		chillHolder = ad;
	}
}
