package GameObjects;


import GIS.Map;
import GUI.Gui_dialog;
import Geom.Point2D;
import game.GameBoard;

/**
 */


public class Fruit extends game_object {

	public static String picture = "fruit.png";
	
	private double weight;
	private double eatingRadius; 
	
		
	public Fruit(int id, Point2D location, double weight) {
		super(id, location);
		this.weight = weight;
		this.eatingRadius = -3;
	}

	
	public String toString() {
		Point2D global_location = getGlobalPoint();
		return "F,"	+ getId() + 
				","	+ global_location.x() + 
				"," + global_location.y() +
				"," + getWeight() + 
				"," + getEatingRadius();
	}
	
	public double getWeight() {
		return weight;
	}
	
	public double getEatingRadius() {
		return eatingRadius;
	}



}
