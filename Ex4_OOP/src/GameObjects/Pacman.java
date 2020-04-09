package GameObjects;


import Coords.MyCoords;
import GIS.GIS_element;
import GIS.Meta_data;
import Geom.Geom_element;
import Geom.Point3D;
import Geom.geom;
import game.GameBoard;
import game.Game_Metadata;
/**
 *  
 * @author Yoni
 */
public class Pacman extends game_object {



	public Pacman(GameBoard gameBoard, String picture, int id, double lat, double lon, int speed, int radius) {
		super(gameBoard, picture, id, lat, lon, speed, radius);
		// TODO Auto-generated constructor stub
	}
	
	public Pacman(String element) {
		super(element);
	}


	@Override
	public void addItemToList() {
		game_metadata.getPacmans().add(this);		
	}


	
}
