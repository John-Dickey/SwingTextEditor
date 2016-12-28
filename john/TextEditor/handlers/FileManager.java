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
		file.getGuiRep().getTextArea().getDocument().addDocumentListener(new TextDocumentListener2(file));
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
		openFile(new File2(f.getData(), f.getName()));
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
		throw new IllegalArgumentException("It didn't work");//TODO good exception desc
	}
	public File2 getFile(Document d)
	{
		for(File2 f : files)
		{
			if(f.getGuiRep().getTextArea().getDocument() == d)
				return f;
		}
		return null;
	}
	public File2 getFile(UID u)
	{
		for(File2 f: files)
		{
			if(f.getUid().equals(u))
				return f;
		}
		return null;
	}
	public void p(String s)
	{
		System.out.println(s);
	}
	public void handleAddition(Addition a) throws BadLocationException
	{
		File2 file = getFile(a.getUid());
		file.getGuiRep().getTextArea().getDocument().insertString(a.getOffset(), a.getChange(), null);
	}
	public void handleDeletion(Deletion d)
	{
		
	}
}
