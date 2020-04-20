package GameObjects;

import algorithms.Point2D;
/**
 *  
 * @author Yoni
 */
public class Pacman extends MoveableObject {

	public static String picture = "pacman.png";
	private double weight;

	public Pacman(int id, Point2D location, double velocity, double eatingRadius) {
		super(id , location, velocity);
		setEatingRadius(eatingRadius);
		setObjectSize(23);
	}

	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public String toString() {
		Point2D global_location = getLocation();
		return "P,"	+ getId() + 
				","	+ global_location.y() + 
				"," + global_location.x() +
				"," + getVelocity() +
				"," + getEatingRadius()/10;
	}
	
	public static String getTag() {
		return "pacman";
	}

}
