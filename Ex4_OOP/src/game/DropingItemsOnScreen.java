package game;

import GUI.Gui_dialog;
import GUI.MouseClick;

public class DropingItemsOnScreen extends Thread {

	public static boolean dropping_apples = false;
	public static boolean dropping_pacmans = false;

	
	public void startDroppingItems() {
		
		Gui_dialog.alert("Hi, drop apples. Once done, press the right button of the mouse");
		dropping_apples = true;
		
		
		while (dropping_apples) {
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Gui_dialog.alert("Great. Now drop pacmans.");
		dropping_pacmans = true;
		
		while (dropping_pacmans) {
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Gui_dialog.alert("Good. Now choose your stating point.");
		
	}

}
