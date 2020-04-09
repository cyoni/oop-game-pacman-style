package GameObjects;

import GIS.Map;
import Geom.Point3D;
import game.GameBoard;


public class Ghost extends game_object{


	private int speed;

	public Ghost(GameBoard gameBoard, Map map, int id, double lat, double lon, int speed) {
		super(gameBoard, map, "ghost.jpg", id , new Point3D(lat, lon));
		this.speed = speed;
	}


	public int getSpeed() {
		return speed;
	}



}
