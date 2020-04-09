package GameObjects;


import GIS.Map;
import Geom.Point3D;
import game.GameBoard;

/**
 */


public class Fruit extends game_object {


	private int weight; 
	
		
	public Fruit(GameBoard gameBoard, Map map, int id, double lat, double lon, int weight) {
		super(gameBoard, map, "fruit.png", id, new Point3D(lat, lon));
		this.weight = weight;
	}

	


	public int getWeight() {
		return weight;
	}



}
