package john.TextEditor.util;

import javax.swing.JTabbedPane;
import john.TextEditor.gui.MaineWindow;

public class DocumentUtils 
{
	public static void setCurrentTabName(String n)
	{
		JTabbedPane tPain = MaineWindow.getInstance().getTabbedPane();
		tPain.setTitleAt(tPain.getSelectedIndex(), n);
	}
	public static String getCurrentTabName()
	{
		JTabbedPane tPain = MaineWindow.getInstance().getTabbedPane();
		return tPain.getTitleAt(tPain.getSelectedIndex());
	}
}
