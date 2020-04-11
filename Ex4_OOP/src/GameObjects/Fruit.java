package GameObjects;


import GIS.Map;
import Geom.Point3D;
import game.GameBoard;

/**
 */


public class Fruit extends game_object {

	public static String picture = "fruit.png";

	private double weight; 
	
		
	public Fruit(GameBoard gameBoard, Map map, int id, Point3D location, double weight) {
		super(id, location);
		this.weight = weight;
	}

	


	public double getWeight() {
		return weight;
	}



}
