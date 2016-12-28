package john.TextEditor.gui.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import org.fife.ui.rtextarea.RTextScrollPane;

import john.TextEditor.gui.MaineWindow;
import john.TextEditor.objects.File2;

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
			    	MaineWindow.getInstance().getFileManager().openFile(chooser.getSelectedFile());
			    }
				break;
			case "Save":
				File2 f = MaineWindow.getInstance().getFileManager().getFile((RTextScrollPane)MaineWindow.getInstance().getTabbedPane().getSelectedComponent());
				if(f == null)
					System.out.println("wat");
				if(f.save())//if successfully saves
					f.setEdited(false);
				break;
			case "New":
				MaineWindow.getInstance().getFileManager().openFile();
				break;
			default:
				break;
			}
		}
	}

}
