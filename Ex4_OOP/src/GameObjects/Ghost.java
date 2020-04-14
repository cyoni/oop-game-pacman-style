package GameObjects;

import GIS.Map;
import Geom.Point2D;
import game.GameBoard;


public class Ghost extends game_object{

	
	private double speed;
	public static String picture = "ghost.png";
	
	public Ghost(int id, Point2D location, double speed) {
		super(id , location);
		this.speed = speed;
	}

	public String toString() {
		Point2D global_location = getGlobalPoint();
		return "G,"	+ getId() + 
				","	+ global_location.x() + 
				"," + global_location.y() +
				"," + getSpeed() + 
				",";
	}

	public double getSpeed() {
		return speed;
	}



}
