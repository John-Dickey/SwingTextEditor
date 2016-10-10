package john.TextEditor.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import john.TextEditor.gui.MaineWindow;
import john.TextEditor.objects.File;
import john.TextEditor.util.DocumentUtils;

public class FileWriter //TODO implement saving
{
	//returns if successful
	public static boolean saveFile(File f) //TODO implement is file modified symbol, probably a * in the title
	{//TODO might have to delete the file first
		PrintWriter writer;
		java.io.File file = f.getFile();
		if(f.getNeedsToSaveAs());
		{
			JFileChooser chooser = new JFileChooser();
			int option = chooser.showSaveDialog(MaineWindow.getInstance().getJFrame());
			if(option == JFileChooser.APPROVE_OPTION)
			{
				file = chooser.getSelectedFile();
				f.setFile(file);
				DocumentUtils.setCurrentTabName(file.getName());
			} else {
				return false;
			}
		}
		if(f.getFile().exists())
		{//TODO confirm overwrite confirmation works
			if(JOptionPane.showConfirmDialog(MaineWindow.getInstance().getJFrame(), "The file: " + f.getFile().getName() + " already exists, are you sure you want to overwrite it?") == JOptionPane.OK_OPTION)
			{
				//nothing!
			} else {
				return false;
			}
		}
		try{	
		writer = new PrintWriter(new BufferedWriter(new java.io.FileWriter(file)));
		} catch (IOException e) { e.printStackTrace(); return false;}
		Thread t = new Thread(new MakeAmericaThreadSafeAgain(writer, f));
		t.start();
		return true;
	}
	private static class MakeAmericaThreadSafeAgain implements Runnable
	{
		PrintWriter pw;
		File f;
		public MakeAmericaThreadSafeAgain(PrintWriter w, File f)
		{
			pw = w;
			this.f = f;
		}
		public void run()
		{
			pw.print(f.getTextArea().getText());
			pw.flush();
		}
	}
}
