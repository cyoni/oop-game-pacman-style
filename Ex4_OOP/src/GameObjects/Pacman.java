package GameObjects;

import algorithms.Point2D;
/**
 *  
 * @author Yoni
 */
public class Pacman extends game_object implements IHungry {


	public static String picture = "pacman.png";
	private double speed;
	private game_object target;

	public Pacman(int id, Point2D location, double speed) {
		super(id, location);
		this.speed = speed;
	}

	public String toString() {
		Point2D global_location = getLocation();
		return "P,"	+ getId() + 
				","	+ global_location.x() + 
				"," + global_location.y() +
				"," + getSpeed();
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
