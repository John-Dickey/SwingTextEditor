package john.TextEditor.handlers;

import java.rmi.server.UID;
import java.util.ArrayList;

import javax.swing.JTabbedPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import org.fife.ui.rtextarea.RTextScrollPane;

import john.TextEditor.gui.MaineWindow;
import john.TextEditor.gui.event.TextDocumentListener2;
import john.TextEditor.net.packet.Addition;
import john.TextEditor.net.packet.Deletion;
import john.TextEditor.net.packet.LogEnrty;
import john.TextEditor.objects.File2;
import john.TextEditor.objects.FileAsGui;

public class FileManager 
{
	ArrayList<File2> files;
	JTabbedPane tabs;
	public FileManager()
	{
		files = new ArrayList<File2>();
		tabs = MaineWindow.getInstance().getTabbedPane();
	}
	public void openFile(File2 file)
	{
		file.setGuiRep(new FileAsGui(file));
		
		TextDocumentListener2 docListener = new TextDocumentListener2(file);
		file.getGuiRep().getTextArea().getDocument().addDocumentListener(docListener);
		file.setDocListener(docListener);
		
		tabs.addTab(file.getName(), file.getGuiRep().getScrollPane());
		files.add(file);
	}
	public void openFile(java.io.File fil)
	{
		openFile(new File2(fil));
	}
	public void openFile()
	{
		openFile(new File2());
	}
	public void openFile(john.TextEditor.net.packet.File f)
	{
		openFile(new File2(f));
	}
	public boolean hasNoFiles()
	{
		return files.isEmpty();
	}
	public File2 getFile(RTextScrollPane pane) throws IllegalArgumentException
	{
		p(files.size() + "");
		for(File2 f : files)
		{
			if(f.getGuiRep().getScrollPane().equals(pane))
				return f;
		}
		throw new IllegalArgumentException("Could not find");//TODO good exception desc
	}
	public File2 getFile(Document d) throws IllegalArgumentException
	{
		for(File2 f : files)
		{
			if(f.getGuiRep().getTextArea().getDocument() == d)
				return f;
		}
		throw new IllegalArgumentException("Could not find");
	}
	public File2 getFile(UID u) throws IllegalArgumentException
	{
		for(File2 f: files)
		{
			MaineWindow.getInstance().getNetworkManager().getClient().send(new LogEnrty(u.toString() + ":" + f.getUid().toString()));
			if(f.getUid().equals(u))
				return f;
		}
		throw new IllegalArgumentException("Could not find");
	}
	public File2 getSelectedFile()
	{
		return getFile((RTextScrollPane)MaineWindow.getInstance().getTabbedPane().getSelectedComponent());
	}
	public void p(String s)
	{
		System.out.println(s);
	}
	public void handleAddition(Addition a) throws BadLocationException
	{
		File2 file = getFile(a.getUid());
		file.getDocListener().chillOneSecA(a);
		file.getGuiRep().getTextArea().getDocument().insertString(a.getOffset(), a.getChange(), null);
	}
	public void handleDeletion(Deletion d) throws BadLocationException
	{
		File2 file = getFile(d.getUid());
		file.getGuiRep().getTextArea().getDocument().remove(d.getOffset(), d.getLength());
	}
	public String[] getOpenFilenames()
	{
		String[] namesArray = new String[files.size()];
		File2[] array = files.toArray(new File2[files.size()]);
		for(int x = 0; x < array.length; x++)
		{
			namesArray[x] = array[x].getName();
		}
		return namesArray;
	}
	public File2 getFileByArrayIndex(int index)
	{
		return files.get(index);
	}
}
