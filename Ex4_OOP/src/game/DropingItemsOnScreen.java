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
		if (dropping_apples) dropApples();
		if (dropping_pacmans) dropPacmans();
		if (dropping_player) dropPlayer();
	}
	
	private void dropPlayer() {
		Gui_dialog.alert("Choose your stating point.");
		dropping_player = true;
		while(dropping_player) {
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}

	private void dropPacmans() {
		Gui_dialog.alert("Drop pacmans.");
		while (dropping_pacmans) {
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void dropApples() {
		
		Gui_dialog.alert("Start dropping fruits. Once done, press the right button of the mouse");
		while (dropping_apples) {
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void selectToDropAll() {
		dropping_apples = true;
		dropping_pacmans = true;
		dropping_player = true;
	}

	public static void selectNone() {
		dropping_apples = false;
		dropping_pacmans = false;
		dropping_player = false;
	}

}
