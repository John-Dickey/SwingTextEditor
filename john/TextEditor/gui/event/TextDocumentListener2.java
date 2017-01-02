package john.TextEditor.gui.event;

import java.awt.Robot;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import john.TextEditor.gui.MaineWindow;
import john.TextEditor.net.NetworkManager;
import john.TextEditor.net.packet.Addition;
import john.TextEditor.net.packet.Deletion;
import john.TextEditor.net.packet.LogEnrty;
import john.TextEditor.objects.File2;

import org.apache.commons.lang3.StringUtils;

import com.sun.glass.events.KeyEvent;

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
	public void insertUpdate(DocumentEvent event)//TODO make a sync button, which basically says im right, replace everyone's versions with my version (here because why not)
	{
		try {
			String doc = event.getDocument().getText(0, event.getDocument().getLength());
			String change = doc.substring(event.getOffset(), event.getOffset() + event.getLength());
			if(change.equals("\n"))
			{
				if(netMan.shouldDo())
				{
					netMan.getClient().send(new LogEnrty("\\n"));
				}
				int beginIndexOfPrev = doc.lastIndexOf('\n', event.getOffset()-1);
				if(beginIndexOfPrev != -1)
				{
					String prevLine = doc.substring(beginIndexOfPrev, event.getOffset() -1);
					int countOfTabs = StringUtils.countMatches(prevLine, '\t');
					SwingUtilities.invokeLater(new Dealio(countOfTabs, event.getDocument(), event.getOffset() + 1));
				}
			}
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
	private class Dealio implements Runnable//TODO name "Dealio"
	{
		int number;
		Document document;
		int off;
		public Dealio(int num, Document doc, int offset)
		{
			number = num;
			document = doc;
			off = offset;
		}
		public void run()
		{
			String holder = "";
			for(int x = 0; x < Math.ceil((double)number); x++)
			{
				holder += "\t";
			}
			try {
				document.insertString(off, holder, null);
				//file.getGuiRep().getTextArea().setCaretPosition(file.getGuiRep().getTextArea().getCaretPosition() + 1);//off + holder.length() + 1 // \_('~')_/ it doesnt work
				new Robot().keyPress(KeyEvent.VK_RIGHT);
				Thread.sleep(100);
				new Robot().keyRelease(KeyEvent.VK_RIGHT);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}
