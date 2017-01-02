package john.TextEditor.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import john.TextEditor.gui.event.MenuBarListener;
import john.TextEditor.gui.event.TextDocumentListener2;
import john.TextEditor.handlers.FileManager;
import john.TextEditor.net.NetworkManager;

public class MaineWindow 
{
	
	private static MaineWindow maineWindow;
	
	JPanel cp;
	JFrame frame;
	RSyntaxTextArea textArea;
	JTabbedPane tabbedPane;
	
	MenuBarDeal menuBar;
	
	MenuBarListener menuBarListner;
	TextDocumentListener2 docListener;
	
	NetworkManager networkManager;
	
	LogPanel logger;
	
	//ArrayList<File> openDocs;
	FileManager fileManager;
	private MaineWindow() throws Exception
	{
	      maineWindow = this;
		  
		  cp = new JPanel(new BorderLayout());
	      frame = new JFrame("You better believe this is a frame");
	      frame.pack();
	      frame.setSize(720,480);
	      frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	      
	      tabbedPane = new JTabbedPane();
	      cp.add(tabbedPane, BorderLayout.CENTER);
	      
	      JButton b = new JButton("test");
	      JPanel left = new JPanel();
	      left.setBackground(Color.blue);
	      left.add(b);
	      cp.add(left, BorderLayout.LINE_START);
	      
	      JButton b1 = new JButton("test2");
	      JPanel up = new JPanel(new FlowLayout(FlowLayout.LEADING));
	      up.add(b1);
	      up.setBackground(Color.red);
	      cp.add(up, BorderLayout.PAGE_START);
	      
	      menuBar = new MenuBarDeal();
	      
	      networkManager = new NetworkManager();
	      
	      fileManager = new FileManager();
	      
	      //logger = new LogPanel();
	      
	      if(fileManager.hasNoFiles())
	    	  fileManager.openFile();
	      
	      frame.setJMenuBar(menuBar);
	      frame.setContentPane(cp);
	      frame.setTitle("Text Editor Demo");
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.setLocationRelativeTo(null);
	      frame.setVisible(true);
	      
	}
	private void p(String s)//because typing out System.out.println() takes too long
	{
		System.out.println(s);
	}
	public static MaineWindow getInstance()
	{
		if(maineWindow == null)
		{
			try {
				maineWindow = new MaineWindow();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return maineWindow;
	}
	public JFrame getJFrame()
	{
		return frame;
	}
	public JTabbedPane getTabbedPane()
	{
		return tabbedPane;
	}
	public TextDocumentListener2 getDocumentListener()
	{
		return docListener;//TODO implement getDocumentListener()
	}
	public FileManager getFileManager()
	{
		return fileManager;
	}
	public NetworkManager getNetworkManager()
	{
		return networkManager;
	}
	public LogPanel getLogger()
	{
		return logger;
	}
}
