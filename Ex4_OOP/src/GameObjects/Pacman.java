package GameObjects;


import Coords.MyCoords;
import GIS.GIS_element;
import GIS.Map;
import Geom.Geom_element;
import Geom.Point2D;
import Geom.geom;
import game.GameBoard;
/**
 *  
 * @author Yoni
 */
public class Pacman extends game_object {


	public static String picture = "pacman.png";
	public static int id = 0;
	private double speed;

	public Pacman(int id, Point2D location, double speed) {
		super(id, location);
		this.speed = speed;
	}

	public String toString() {
		Point2D global_location = getGlobalPoint();
		return "P,"	+ getId() + 
				","	+ global_location.x() + 
				"," + global_location.y() +
				"," + getSpeed();
	}
	
	public double getSpeed() {
		return speed;
	}


}
