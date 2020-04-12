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
	
		
	public Fruit(GameBoard gameBoard, Map map, int id, Point2D location, double weight) {
		super(id, map.global2pixel(location));
		this.weight = weight;
	}

	


	public double getWeight() {
		return weight;
	}



}
