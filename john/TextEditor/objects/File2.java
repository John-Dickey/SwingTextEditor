package john.TextEditor.objects;

import java.rmi.server.UID;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import john.TextEditor.gui.MaineWindow;
import john.TextEditor.gui.event.TextDocumentListener2;
import john.TextEditor.io.FileReader;
import john.TextEditor.io.FileWriter;
import john.TextEditor.net.packet.File;

public class File2 
{
	String text;
	String name;
	UID uid;
	FileAsGui guiRep;
	java.io.File actualFile;
	boolean askForOverwrite;
	boolean edited;
	TextDocumentListener2 docListener;
	/*
	 * Constructors
	 */
	public File2(String text, String name, java.io.File fileIfApplicable, UID u)
	{
		this.text = text;
		this.name = name;
		uid = (u != null ? u : new UID());
		//uid = new UID();//TODO change this to have a uid arguement, check if is null, then if so it makes new one
		//MaineWindow.getInstance().getLogger().log("creating a new uuid: {" + uid.toString() + "} for file with name: " + name);
		guiRep = null;
		actualFile = fileIfApplicable;
		askForOverwrite = true;
		edited = false;
	}
	public File2(java.io.File f)
	{
		this(FileReader.constructString(f), f.getName(), f, null);
	}
	public File2(String text, String name)
	{
		this(text, name, null, null);
	}
	public File2(String text, String name, UID u)
	{
		this(text, name, null, u);
	}
	public File2()
	{
		this("", "Untitled.txt");
	}
	public File2(john.TextEditor.net.packet.File f)
	{
		this(f.getData(), f.getName(), f.getUid());
		//setUid(f.getUid());
	}
	/*
	 * Methods
	 */
	public void setGuiRep(FileAsGui g)
	{
		guiRep = g;
	}
	public FileAsGui getGuiRep()
	{
		return guiRep;
	}
	public String getName()
	{
		return name;
	}
	public john.TextEditor.net.packet.File toPacket()
	{
		return new File(name, text, uid);
	}
	public boolean save()
	{
		if(actualFile != null)//if the file was defined already
		{
			if(actualFile.exists() && askForOverwrite)//if the file exists, and it shuold ask if overwriting is ok
			{
				if(JOptionPane.showConfirmDialog(MaineWindow.getInstance().getJFrame(), 
						"The file: " + name + 
						" already exists, are you sure you want to overwrite it?") 
						== JOptionPane.OK_OPTION)
				{//yes they want to over write
					askForOverwrite = false;
					return FileWriter.write(text, actualFile);
				} else {//they dont, fails
					return false;
				}
			}
			return FileWriter.write(text, actualFile);
		} else {//needs to select a new file
			JFileChooser chooser = new JFileChooser();
			int option = chooser.showSaveDialog(MaineWindow.getInstance().getJFrame());
			if(option == JFileChooser.APPROVE_OPTION)//if selected a new file
			{
				actualFile = chooser.getSelectedFile();
				if(actualFile.exists()) {//file exists, needs to confim 
					if(JOptionPane.showConfirmDialog(MaineWindow.getInstance().getJFrame(), 
							"The file: " + name + 
							" already exists, are you sure you want to overwrite it?") 
							== JOptionPane.OK_OPTION)
					{
						askForOverwrite = false;
						setName(actualFile.getName());
						return FileWriter.write(text, actualFile);
					} else {
						return false;
					}
				}
				setName(actualFile.getName());
				return FileWriter.write(text, actualFile);
			} else {
				return false;
			}
		}
	}
	public void setName(String s)
	{
		name = s;
		guiRep.updateName();
	}
	public boolean edited()
	{
		return edited;
	}
	public void setEdited(boolean b)
	{
		edited = b;
		guiRep.updateName();
	}
	public void p(String s)
	{
		System.out.println(s);
	}
	public void setText(String string) 
	{
		text = string;
	}
	public UID getUid()
	{
		return uid;
	}
	public String getText()
	{
		return text;
	}
	public void setUid(UID u)
	{
		uid = u;
	}
	public void setDocListener(TextDocumentListener2 t)
	{
		docListener = t;
	}
	public TextDocumentListener2 getDocListener() 
	{
		return docListener;
	}
}
