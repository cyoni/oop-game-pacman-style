package GameObjects;

import algorithms.Point2D;

public class Ghost extends MoveableObject{

	public static String picture = "ghost.png";
	
	public Ghost(int id, Point2D location, double velocity, double eatingRadius) {
		super(id , location, velocity);
		this.eatingRadius = eatingRadius;
		setObjectSize(30);
	}

	public String toString() {
		Point2D global_location = getLocation();
		return "G,"	+ getId() + 
				","	+ global_location.x() + 
				"," + global_location.y() +
				"," + velocity + 
				"," + eatingRadius;
	}

}
