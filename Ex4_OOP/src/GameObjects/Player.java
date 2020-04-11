package GameObjects;

import GIS.Map;
import Geom.Point3D;
import game.GameBoard;

/**

 */
public class Player extends game_object {

	public static String picture = "player.jpg";
	private double degree = 30;
	

	public Player(int id, Point3D location, double d) {
		super(id, location);
	}

	public double getDegree() {
		return degree;
	}
	
	public void setDegree(double degree) {
		this.degree = degree;
	}


}
