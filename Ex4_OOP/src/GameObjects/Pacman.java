package GameObjects;


import Coords.MyCoords;
import GIS.GIS_element;
import GIS.Map;
import GIS.Meta_data;
import Geom.Geom_element;
import Geom.Point3D;
import Geom.geom;
import game.GameBoard;
/**
 *  
 * @author Yoni
 */
public class Pacman extends game_object {


	public static String picture = "pacman.png";
	private double speed;

	public Pacman( int id, Point3D location, double speed) {
		super(id, location);
		this.speed = speed;
	}




	public double getSpeed() {
		return speed;
	}


}
