package john.TextEditor.gui;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class LogPanel extends JPanel
{
	JTextArea textArea;
	public LogPanel()
	{
		textArea = new JTextArea(50, 50);
		
		add(textArea);
		MaineWindow.getInstance().getTabbedPane().addTab("LOG WINDOW", this);
	}
	public void log(String s)
	{
		textArea.append("\n" + s);
	}
}
