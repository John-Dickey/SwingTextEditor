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
	MaineWindow window;
	public MenuBarListener() 
	{
		window = MaineWindow.getInstance();
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() instanceof JMenuItem)
		{
			String name = ((JMenuItem)e.getSource()).getText();
			switch(name)
			{
			case "Open":
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(window.getJFrame());
			    if(returnVal == JFileChooser.APPROVE_OPTION)
			    {
			    	window.getFileManager().openFile(chooser.getSelectedFile());
			    }
				break;
			case "Save":
				File2 f = window.getFileManager().getSelectedFile();
				if(f == null)
					System.out.println("wat");
				if(f.save())//if successfully saves
					f.setEdited(false);
				break;
			case "New":
				window.getFileManager().openFile();
				break;
			case "Create Server":
				int port = Integer.parseInt(JOptionPane.showInputDialog(window.getJFrame(), "Enter the port", "7000"));
				try {
					window.getNetworkManager().startServer(port);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
			case "Create Client":
				Scanner scan = new Scanner(JOptionPane.showInputDialog(window.getJFrame(), "Enter the ip and port in the format: [hostname]:[port]", "127.0.0.1:7000"));
				scan.useDelimiter(":");
				String host = scan.next();
				int port1 = scan.nextInt();
				try {
					window.getNetworkManager().startClient(host, port1);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
			case "Send File to Server":
				String[] opts = window.getFileManager().getOpenFilenames();
				String s = (String) JOptionPane.showInputDialog(window.getJFrame(), "Choose the File to send:", "Choose the File", JOptionPane.PLAIN_MESSAGE, null, opts, opts[0]); 
				//turn choice into array index then FileManager.getByArrayIndex(), then Client.send(file.toPacket())
				int index = -1;
				for(int x = 0; x < opts.length; x++)
				{
					if(s == opts[x])
					{
						index = x;
					}
				}
				if(index == -1)
					break;
				File2 file = window.getFileManager().getFileByArrayIndex(index);
				window.getNetworkManager().getClient().send(file.toPacket());
				break;
			default:
				break;
			}
		}
	}

}
