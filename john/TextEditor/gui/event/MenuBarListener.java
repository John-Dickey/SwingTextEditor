package john.TextEditor.gui.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import org.fife.ui.rtextarea.RTextScrollPane;

import john.TextEditor.gui.MaineWindow;
import john.TextEditor.io.FileWriter;
import john.TextEditor.objects.File;
import john.TextEditor.util.DocumentUtils;

public class MenuBarListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e)//i think we're on the edt 
	{
		if(e.getSource() instanceof JMenuItem)
		{
			String name = ((JMenuItem)e.getSource()).getText();
			switch(name)
			{
			case "Open":
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(MaineWindow.getInstance().getJFrame());
			    if(returnVal == JFileChooser.APPROVE_OPTION)
			    {
			    	MaineWindow.getInstance().openFile(new File(chooser.getSelectedFile()));
			    }
				break;
			case "Save":
				File f = DocumentUtils.whoseScrollPaneIsThis(((RTextScrollPane)MaineWindow.getInstance().getTabbedPane().getSelectedComponent()));
				boolean res = FileWriter.saveFile(f);
				if(res)
				{
					//TODO remove the file edited indicator
				}
				break;
			}
		}
	}

}
