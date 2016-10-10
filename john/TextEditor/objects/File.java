package john.TextEditor.objects;

import javax.swing.SwingUtilities;

import john.TextEditor.gui.event.TextDocumentListener;
import john.TextEditor.io.FileReader;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

public class File 
{
	java.io.File file;
	RSyntaxTextArea textArea;
	RTextScrollPane scrollPane;
	TextDocumentListener docListener;
	boolean needsSaveAs;
	public File(java.io.File f)
	{
		file = f;
		textArea = new RSyntaxTextArea();
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);//TODO implement text style
	    textArea.setCodeFoldingEnabled(true);//TODO preferences code folding
	    scrollPane = new RTextScrollPane(textArea);
	    if(f.getName() == "Untitled.java")//TODO implement need to save, when geetting new files
	    {
	    	needsSaveAs = true;
	    	return;
	    }
	    needsSaveAs = false;
	    Thread r = new Thread(new PrivateParts(this));//make it thread safe
	    r.start();
	    docListener = new TextDocumentListener();
	    textArea.getDocument().addDocumentListener(docListener);
	}
	public File(String name, String data)
	{
		this(new java.io.File(name));
		needsSaveAs = true;
		this.setTextAreaText(data);
	}
	public RTextScrollPane getScrollPane()
	{
		return scrollPane;
	}
	public java.io.File getFile()
	{
		return file;
	}
	public void setTextAreaText(String s)
	{
		textArea.setText(s);
	}
	private class PrivateParts implements Runnable//Don't question it
	{
		File f;
		public PrivateParts(File f)
		{
			this.f = f;
		}
		public void run() 
		{
			f.setTextAreaText(FileReader.constructString(f.getFile()));
		}
	}
	public RSyntaxTextArea getTextArea()
	{
		return textArea;
	}
	public void setFile(java.io.File f)
	{
		file = f;
	}
	public boolean getNeedsToSaveAs()
	{
		return needsSaveAs;
	}
	public void setNeedsToSaveAs(boolean b)
	{
		needsSaveAs = b;
	}
}
