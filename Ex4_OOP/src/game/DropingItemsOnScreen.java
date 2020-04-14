package game;

import GUI.Gui_dialog;
import GUI.MouseClickOnScreen;

public class DropingItemsOnScreen extends Thread {

	public static boolean dropping_apples = false;
	public static boolean dropping_pacmans = false;
	public static boolean dropping_player = false;

	
	public void startDroppingItems() {
		start();
	}
	
	public synchronized void run() {
		
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
		dropping_player = true;
		while(dropping_player) {
			try {
				sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Gui_dialog.alert("Hit OK to start the game");
	}

}
