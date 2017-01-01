package john.TextEditor.objects;

import javax.swing.JTabbedPane;
import javax.swing.text.BadLocationException;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import john.TextEditor.gui.MaineWindow;

public class FileAsGui 
{
	File2 file;
	RSyntaxTextArea textArea;
	RTextScrollPane scrollPane;
	public FileAsGui(File2 f)
	{
		file = f;
		textArea = new RSyntaxTextArea(f.text);
		textArea.setClearWhitespaceLinesEnabled(false);
		textArea.setCloseCurlyBraces(false);
		textArea.setCloseMarkupTags(false);
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		scrollPane = new RTextScrollPane(textArea);
	}
	public void insert(int offset, String str) throws BadLocationException
	{
		textArea.getDocument().insertString(offset, str, null);
	}
	public RTextScrollPane getScrollPane()
	{
		return scrollPane;
	}
	public RSyntaxTextArea getTextArea()
	{
		return textArea;
	}
	public void updateName()
	{
		JTabbedPane tPain = MaineWindow.getInstance().getTabbedPane();
		tPain.setTitleAt(tPain.indexOfComponent(scrollPane), (file.edited() ? "*" : "") + file.name);
	}
}
