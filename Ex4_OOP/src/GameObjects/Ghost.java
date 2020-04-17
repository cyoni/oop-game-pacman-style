package GameObjects;

import GIS.Map;
import algorithms.Point2D;
import game.GameBoard;


public class Ghost extends game_object implements IHungry{

	
	private double speed;
	private game_object target;
	public static String picture = "ghost.png";
	
	public Ghost(int id, Point2D location, double speed) {
		super(id , location);
		this.speed = speed;
	}

	public String toString() {
		Point2D global_location = getLocation();
		return "G,"	+ getId() + 
				","	+ global_location.x() + 
				"," + global_location.y() +
				"," + getSpeed() + 
				",";
	}

	public double getSpeed() {
		return speed;
	}


	@Override
	public game_object getTarget() {
		return target;
	}

	@Override
	public void setTarget(game_object target) {
		this.target = target;
	}



}
