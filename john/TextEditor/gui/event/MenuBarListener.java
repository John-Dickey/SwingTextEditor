package john.TextEditor.gui.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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
				File2 f = MaineWindow.getInstance().getFileManager().getSelectedFile();
				if(f == null)
					System.out.println("wat");
				if(f.save())//if successfully saves
					f.setEdited(false);
				break;
			case "New":
				MaineWindow.getInstance().getFileManager().openFile();
				break;
			case "Create Server":
				int port = Integer.parseInt(JOptionPane.showInputDialog(MaineWindow.getInstance().getJFrame(), "Enter the port", "7000"));
				try {
					MaineWindow.getInstance().getNetworkManager().startServer(port);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
			case "Create Client":
				Scanner scan = new Scanner(JOptionPane.showInputDialog(MaineWindow.getInstance().getJFrame(), "Enter the ip and port in the format: [hostname]:[port]", "127.0.0.1:7000"));
				scan.useDelimiter(":");
				String host = scan.next();
				int port1 = scan.nextInt();
				try {
					MaineWindow.getInstance().getNetworkManager().startClient(host, port1);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
			case "Send File to Server":
				String[] opts = MaineWindow.getInstance().getFileManager().getOpenFilenames();
				String s = (String) JOptionPane.showInputDialog(MaineWindow.getInstance().getJFrame(), "Choose the File to send:", "Choose the File", JOptionPane.PLAIN_MESSAGE, null, opts, opts[0]); 
				//turn choice into array index then FileManager.getByArrayIndex(), then Client.send(file.toPacket())
				break;
			default:
				break;
			}
		}
	}

}
