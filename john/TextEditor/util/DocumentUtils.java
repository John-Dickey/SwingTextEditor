package john.TextEditor.util;

import javax.swing.JTabbedPane;
import john.TextEditor.gui.MaineWindow;

public class DocumentUtils 
{
	/*
	public static File whoseDocumentIsThis(Document d)
	{
		for(File f : MaineWindow.getInstance().getOpenDocuments())
		{
			Document doc = f.getTextArea().getDocument();
			Segment s1 = new Segment();
			try {
				d.getText(0, doc.getLength(), s1);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
			if(s1.toString() == d.toString())
			{
				return f;
			}
		}
		return null;
	}
	public static File whoseScrollPaneIsThis(RTextScrollPane p)
	{
		for(File f : MaineWindow.getInstance().getFileManager())
		{
			if(f.getScrollPane() == p)
				return f;
		}
		return null;
	}*/
	public static void setCurrentTabName(String n)
	{
		JTabbedPane tPain = MaineWindow.getInstance().getTabbedPane();
		tPain.setTitleAt(tPain.getSelectedIndex(), n);
	}
	public static String getCurrentTabName()
	{
		JTabbedPane tPain = MaineWindow.getInstance().getTabbedPane();
		return tPain.getTitleAt(tPain.getSelectedIndex());
	}/*
	public static File getFileForFilename(String name)
	{
		for(File f : MaineWindow.getInstance().getOpenDocuments())
		{
			if(f.getFile().getName() == name)
				return f;
		}
		return null;
	}*/
}
