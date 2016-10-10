package john.TextEditor.main;

import john.TextEditor.gui.*;
import javax.swing.*;

//TODO implement logging
public class Main 
{
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable(){
			public void run()
			{
				try {
					MaineWindow.getInstance();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});//invoke on the event dispatching thread
	}
}
