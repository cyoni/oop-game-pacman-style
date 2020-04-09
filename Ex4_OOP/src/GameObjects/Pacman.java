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


	
	private int speed;

	public Pacman(GameBoard gameBoard, Map map, int id, double lat, double lon, int speed) {
		super(gameBoard, map, "pacman.png", id, new Point3D(lat, lon));
		this.speed = speed;
	}




	public int getSpeed() {
		return speed;
	}


}
