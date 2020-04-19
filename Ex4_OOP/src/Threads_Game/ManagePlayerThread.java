package Threads_Game;

import GUI.Gui_algo;

public class ManagePlayerThread extends Thread{

	private Gui_algo gui_algo;

	public ManagePlayerThread(Gui_algo gui_algo) {
		this.gui_algo = gui_algo;
	}
	
	public void run() {
		while (gui_algo.getGameboard().isRunning()) {
			try {sleep(100);} catch (InterruptedException e) {}
			

			
		}
	}
	
	
}
