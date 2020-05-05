package GUI;

import java.awt.Graphics;
import java.util.Queue;

import GameObjects.Game_object;

public class RefreshScreen extends Thread {
	
	Gui_algo gui_algo;
	
	public RefreshScreen(Gui_algo gui_algo) {
		this.gui_algo = gui_algo;
	}
	
	
	public void run() {
		while(true) {
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			gui_algo.repaint();
		}		
	}


	public synchronized void refresh(Images images, Graphics graphics) {
	
		//gui_algo.drawItems.drawFruits(gui_algo.gameboard.getFruits(), graphics, images.getFruit_image(), gui_algo); // throws error
	//	gui_algo.drawItems.drawObjects(gui_algo.gameboard.getGhosts(), graphics, images.getGhost_image(), gui_algo);
	 //   gui_algo.drawItems.drawObjects(gui_algo.gameboard.getPacmans(), graphics, images.getPacman_image(), gui_algo);
	 //   gui_algo.drawItems.drawPlayer(graphics, images.getPlayer_image(), gui_algo);
	}

}
