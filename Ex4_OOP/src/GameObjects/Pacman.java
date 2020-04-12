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
	private double speed;

	public Pacman( int id, Map map, Point2D location, double speed) {
		super(id, map.global2pixel(location));
		this.speed = speed;
	}

	public double getSpeed() {
		return speed;
	}


}
