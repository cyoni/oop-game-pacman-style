package game;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import Coords.MyCoords;
import FileFormat.CSVReader;
import GUI.Gui_dialog;
import GameObjects.Player;
import GameObjects.game_object;
import Geom.Point2D;

public class GameManager extends Thread {

	GameBoard gameboard;
	
	long gameTimer = 50000;
	
	public GameManager(GameBoard gameboard) {
		this.gameboard = gameboard;
				
	}

	public void startGame() throws Exception {
		gameboard.setGame_status(true);
		FruitManager fruitManager = new FruitManager(gameboard);
		fruitManager.start();
		
		runGame(); // start thread
	}
	
	
	public boolean getIsPlaying() {
		return gameboard.isRunning();
	}
	
	public void runGame() throws Exception {
		try {
			
			double m = 0;
		while(gameboard.isRunning() && gameTimer > 0) {
			gameTimer=100;
			//System.out.println(gameTimer);
			
			
		//	Point2D p = gameboard.getPlayer().getLocation();
			//double x = p.x();
		////	double y = p.y();
		//	Point2D pp = new Point2D(++x, ++y);			
		//	gameboard.getPlayer().setLocation(pp);
			
			
			PriorityQueue<game_object> queue = gameboard.getFruits();
			System.out.println(queue.size() + " size of fruits");
			game_object fruit = queue.peek();
			game_object player = gameboard.getPlayer();
			Point2D target = null;
			
			
			// checks if the object is close to the target 
			
			if (queue.size() == 0) {
					gameboard.setGame_status(false);
					throw new Exception("No more fruits");
				
			}
			
			if (player.getTarget() == null) {
				
				player.setTarget(fruit);
				target = player.getTarget().getLocation();
				m = (target.y() - player.getLocation().y()) / (target.x() - player.getLocation().x());

			}
			
			if (player.getTarget() != null) {
				
				if (!queue.contains(player.getTarget())) player.setTarget(null);
				else {
					
					if (fruit != player.getTarget())
					{
						player.setTarget(fruit);
						Gui_dialog.alert("@");
					}
				
				target = player.getTarget().getLocation();
	
			 // if the fruit is still available && no one is chasing the player.
				sleep(100);

				
				MyCoords mc = new MyCoords();
				double distance = mc.distance2D(player.getLocation(), player.getTarget().getLocation());
				System.out.println("DISTANCE "  + distance);

				
				
				System.out.println("tagert " + target);
				// משוואת ישר:
				
				//double degree = Math.atan2(player.getLocation().y()-target.y(), player.getLocation().x()-target.x());
			//	degree = Math.toDegrees(degree);
				
				
				
				double x1,x2,y1,y2;
	
				y1 = m*(player.getLocation().x() - target.x()) + target.y();
					System.out.println("M=" + m);
	

				if (player.getLocation().x() > player.getTarget().getLocation().x()) { // go down
					player.setLocation(new Point2D(player.getLocation().x()-3 , y1));	
					System.out.println("going down " + player.getLocation());
				}
				else if(player.getLocation().x() < player.getTarget().getLocation().x()) { // go up
					gameboard.getPlayer().setLocation(new Point2D(player.getLocation().x()+3 , y1));	
					System.out.println("going up " + player.getLocation());

				}
				else { // degree is 0
					System.out.println("going no where");
				}
				
	
				}
			}
			
			
	
			}
		

		}
		catch (InterruptedException e) {}

	}
	
	
}
