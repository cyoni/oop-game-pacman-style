package game;

import java.util.Iterator;

import Coords.MyCoords;
import GUI.Gui_dialog;
import GameObjects.GameObject;

public class FruitManager extends Thread{

	/**
	 * This class is responsible to remove fruits when the player is close enough to them.
	 * @author Yoni
	 */
	private GameBoard gameboard;

	public FruitManager(GameBoard gameboard) {
		this.gameboard = gameboard;
	}
	
	public void run() {
		MyCoords mc = new MyCoords();
		while (gameboard.isRunning()) {
			try {
				sleep(300);
			} catch (InterruptedException e) {}
			 Iterator<GameObject> fruit_iter = gameboard.getFruits().iterator();
			while(fruit_iter.hasNext()) {
				GameObject fruit = fruit_iter.next();
				double distance = mc.distance2D(gameboard.getPlayer().getLocation(), fruit.getLocation() );
				//System.out.println("player->apple " + gameboard.getPlayer().getLocation() +"," +  fruit.getLocation() + ". " + distance);
				if (distance < 21 + 3) {
				
					fruit_iter.remove();
					
				}
			}
			 
		}
		
	}
	
	
	
}
