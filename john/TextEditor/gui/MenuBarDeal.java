package john.TextEditor.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import john.TextEditor.gui.event.MenuBarListener;

public class MenuBarDeal extends JMenuBar
{
	JMenu fileMenu;
	JMenuItem fileOpenMenuItem;
	JMenuItem fileSaveMenuItem;
	JMenuItem fileNewMenuItem;
	
	JMenu sourceMenu;
	JMenu highlightingSubmenuMenuItem;
	
	JMenu networkMenu;
	JMenuItem netServerStartMenuItem;
	JMenuItem netClientStartMenuItem;
	JMenuItem netOpenFileMenuItem;
	
	
	MenuBarListener menuBarListner;
	public MenuBarDeal()
	{
		menuBarListner = new MenuBarListener();
		/*****************
		* File
		*****************/
		fileMenu = new JMenu("File");
		add(fileMenu);

		fileOpenMenuItem = new JMenuItem("Open");
		fileOpenMenuItem.addActionListener(menuBarListner);
		fileMenu.add(fileOpenMenuItem);

		fileSaveMenuItem = new JMenuItem("Save");
		fileSaveMenuItem.addActionListener(menuBarListner);
		fileMenu.add(fileSaveMenuItem);

		fileNewMenuItem = new JMenuItem("New");
		fileNewMenuItem.addActionListener(menuBarListner);
		fileMenu.add(fileNewMenuItem);
		/*****************
		* Source
		*****************/
		sourceMenu = new JMenu("Source");
		highlightingSubmenuMenuItem = new JMenu("Code Highlighting");
		
		JMenuItem holder;
		for(int x = 0; x < MenuBarConstants.stuff.length; x++)
		{
			holder = new JMenuItem(MenuBarConstants.stuff[x]);
			holder.addActionListener((e) -> {
				MaineWindow.getInstance().getFileManager().getSelectedFile().getGuiRep().getTextArea().setSyntaxEditingStyle(((JMenuItem) e.getSource()).getText());
			});
			highlightingSubmenuMenuItem.add(holder);
		}
		sourceMenu.add(highlightingSubmenuMenuItem);
		add(sourceMenu);
		
		/*****************
		* Networking
		*****************/
		networkMenu = new JMenu("Networking");
		add(networkMenu);
		
		netServerStartMenuItem = new JMenuItem("Create Server");
		netServerStartMenuItem.addActionListener(menuBarListner);
		networkMenu.add(netServerStartMenuItem);
		
		netClientStartMenuItem = new JMenuItem("Create Client");
		netClientStartMenuItem.addActionListener(menuBarListner);
		networkMenu.add(netClientStartMenuItem);
		
		netOpenFileMenuItem = new JMenuItem("Send File to Server");
		netOpenFileMenuItem.addActionListener(menuBarListner);
		networkMenu.add(netOpenFileMenuItem);
	}
	private static class MenuBarConstants
	{
		public static String[] stuff = {
				SyntaxConstants.SYNTAX_STYLE_ACTIONSCRIPT,
				SyntaxConstants.SYNTAX_STYLE_ASSEMBLER_X86,
				SyntaxConstants.SYNTAX_STYLE_BBCODE,
				SyntaxConstants.SYNTAX_STYLE_C,
				SyntaxConstants.SYNTAX_STYLE_CLOJURE,
				SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS,
				SyntaxConstants.SYNTAX_STYLE_CSHARP,
				SyntaxConstants.SYNTAX_STYLE_CSS,
				SyntaxConstants.SYNTAX_STYLE_D,
				SyntaxConstants.SYNTAX_STYLE_DART,
				SyntaxConstants.SYNTAX_STYLE_DELPHI,
				//SyntaxConstants.SYNTAX_STYLE_DOCKERFILE,
				SyntaxConstants.SYNTAX_STYLE_DTD,
				SyntaxConstants.SYNTAX_STYLE_FORTRAN,
				SyntaxConstants.SYNTAX_STYLE_GROOVY,
				//SyntaxConstants.SYNTAX_STYLE_HOSTS,
				SyntaxConstants.SYNTAX_STYLE_HTACCESS,
				SyntaxConstants.SYNTAX_STYLE_HTML,
				SyntaxConstants.SYNTAX_STYLE_JAVA,
				SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT,
				SyntaxConstants.SYNTAX_STYLE_JSON,
				//SyntaxConstants.SYNTAX_STYLE_JSON_WITH_COMMENTS,
				SyntaxConstants.SYNTAX_STYLE_JSP,
				SyntaxConstants.SYNTAX_STYLE_LATEX,
				SyntaxConstants.SYNTAX_STYLE_LESS,
				SyntaxConstants.SYNTAX_STYLE_LISP,
				SyntaxConstants.SYNTAX_STYLE_LUA,
				SyntaxConstants.SYNTAX_STYLE_MAKEFILE,
				SyntaxConstants.SYNTAX_STYLE_MXML,
				SyntaxConstants.SYNTAX_STYLE_NONE,
				SyntaxConstants.SYNTAX_STYLE_NSIS,
				SyntaxConstants.SYNTAX_STYLE_PERL,
				SyntaxConstants.SYNTAX_STYLE_PHP,
				SyntaxConstants.SYNTAX_STYLE_PROPERTIES_FILE,
				SyntaxConstants.SYNTAX_STYLE_PYTHON,
				SyntaxConstants.SYNTAX_STYLE_RUBY,
				SyntaxConstants.SYNTAX_STYLE_SAS,
				SyntaxConstants.SYNTAX_STYLE_SCALA,
				SyntaxConstants.SYNTAX_STYLE_SQL,
				SyntaxConstants.SYNTAX_STYLE_TCL,
				//SyntaxConstants.SYNTAX_STYLE_TYPESCRIPT,
				SyntaxConstants.SYNTAX_STYLE_UNIX_SHELL,
				SyntaxConstants.SYNTAX_STYLE_VISUAL_BASIC,
				SyntaxConstants.SYNTAX_STYLE_WINDOWS_BATCH,
				SyntaxConstants.SYNTAX_STYLE_XML
		};
	}
}
