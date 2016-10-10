package john.TextEditor.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.*;

import john.TextEditor.gui.event.MenuBarListener;
import john.TextEditor.gui.event.TextDocumentListener;
import john.TextEditor.objects.File;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

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
	
	MenuBarListener menuBarListner;
	TextDocumentListener docListener;
	
	
	ArrayList<File> openDocs;

	public MaineWindow() throws Exception
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
	      
	      openDocs = new ArrayList<>();
	      
	      docListener = new TextDocumentListener();
	      
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
	      
	      
	      if(openDocs.isEmpty())
	      {
	    	  openDocs.add(new File(new java.io.File("Untitled.java")));//TODO extension from preferences/project
	      } 
	      for(File f : openDocs)
		  {
	    	  openFile(f);
		  }
	      
	      
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
	public void openFile(File f)
	{
		tabbedPane.addTab(f.getFile().getName(), f.getScrollPane());
		f.getTextArea().getDocument().addDocumentListener(docListener);
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
	public ArrayList<File> getOpenDocuments()
	{
		return openDocs;
	}
	public JTabbedPane getTabbedPane()
	{
		return tabbedPane;
	}
}
