package GUI;

import javax.swing.JOptionPane;

public class Gui_dialog {
	
	/**
	 * This method pops up a dialog where it asks the user to fill up something according to 'content'
	 * @param content
	 * @return
	 */
	public static String getInputDialog(String content) {
		return JOptionPane.showInputDialog(content);
	}
	
	public static String getInputDialog(String content, String initialText) {
		return JOptionPane.showInputDialog(content, initialText);
	}
	
	/*
	 * This method alerts a message to the user.
	 */
	public static void alert(String content) {
		JOptionPane.showMessageDialog(null, content);
	}

}
