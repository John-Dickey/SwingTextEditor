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
	
	JMenuBar menuBar;
	JMenu fileMenu;
	
	JMenuItem fileOpenMenuItem;
	JMenuItem fileSaveMenuItem;
	JMenuItem fileNewMenuItem;
	
	MenuBarListener menuBarListner;
	TextDocumentListener2 docListener;
	
	NetworkManager networkManager;
	
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
	      
	      //openDocs = new ArrayList<>();
	      
	      //docListener = new TextDocumentListener2();
	      
	      menuBarListner = new MenuBarListener();
	      menuBar = new JMenuBar();
	      fileMenu = new JMenu("File");
	      menuBar.add(fileMenu);
	      
	      fileOpenMenuItem = new JMenuItem("Open");
	      fileOpenMenuItem.addActionListener(menuBarListner);
	      fileMenu.add(fileOpenMenuItem);
	      
	      fileSaveMenuItem = new JMenuItem("Save");
	      fileSaveMenuItem.addActionListener(menuBarListner);
	      fileMenu.add(fileSaveMenuItem);
	      
	      fileNewMenuItem = new JMenuItem("New");
	      fileNewMenuItem.addActionListener(menuBarListner);
	      fileMenu.add(fileNewMenuItem);
	      
	      networkManager = new NetworkManager();
	      
	      fileManager = new FileManager();
	      
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
	}/*
	public void openFile(File f)
	{
		tabbedPane.addTab(f.getFile().getName(), f.getScrollPane());
		f.getTextArea().getDocument().addDocumentListener(docListener);
	}*/
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
}
