package GameObjects;

import GIS.Map;
import GUI.Gui_dialog;
import algorithms.Point2D;
import game.GameBoard;

/**

 */
public class Player extends MoveableObject{

	public static String picture = "player.png";
	private int eatenPacmans = 0;

	public Player(int id, Point2D location, double velocity, double eatingRadius) {
		super(id , location, velocity);
		setEatingRadius(eatingRadius);
		setObjectSize(30);
	}

	public int getEatenPacmans() {
		return this.eatenPacmans;
	}
	
	public void eatPacman(Pacman pacman) {
		addToScore(pacman.getWeight());
		eatenPacmans++;
	}
	
	
	public String toString() {
		Point2D global_location = getLocation();
		return "M,"	+ getId() + 
				","	+ global_location.y() + 
				"," + global_location.x() + 
				"," + velocity + 
				"," + eatingRadius/10;
	}
		
	public static String getTag() {
		return "player";
	}


}
