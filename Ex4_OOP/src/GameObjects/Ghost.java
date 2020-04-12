package GameObjects;

import GIS.Map;
import Geom.Point2D;
import game.GameBoard;


public class Ghost extends game_object{

	
	private double speed;
	public static String picture = "ghost.png";
	
	public Ghost(GameBoard gameBoard, Map map, int id, Point2D location, double speed) {
		super(id , map.global2pixel(location));
		this.speed = speed;
	}


	public double getSpeed() {
		return speed;
	}



}
