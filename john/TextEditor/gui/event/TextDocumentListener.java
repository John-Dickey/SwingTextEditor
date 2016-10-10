package john.TextEditor.gui.event;

import javax.swing.event.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Segment;

import john.TextEditor.gui.MaineWindow;
import john.TextEditor.objects.File;
import john.TextEditor.util.DocumentUtils;

public class TextDocumentListener implements DocumentListener
{
	@Override
	public void changedUpdate(DocumentEvent event) 
	{
		if(DocumentUtils.getCurrentTabName().contains(new String("*")))
		{
			return;
		}
		else
		{
			DocumentUtils.setCurrentTabName('*' + DocumentUtils.getCurrentTabName());
		}
	}

	@Override
	public void insertUpdate(DocumentEvent event) 
	{
		int offset = event.getOffset();
		int length = event.getLength();
		DocumentEvent.EventType type = event.getType();
		Segment seg = new Segment();
		try {
			event.getDocument().getText(offset, length, seg);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		String change = seg.toString();
		File f = DocumentUtils.whoseDocumentIsThis(event.getDocument());
		//MaineWindow.getInstance().getClient().sendAddition(f, offset, length, change);//TODO when client finished
	}

	@Override
	public void removeUpdate(DocumentEvent event) {
		// TODO implement doc listener remove update
		
	}
	
}
