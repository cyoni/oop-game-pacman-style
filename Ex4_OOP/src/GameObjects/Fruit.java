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
 */


public class Fruit extends game_object {


	public Fruit(GameBoard gameBoard, String picture, int id, double lat, double lon, int speed, int radius) {
		super(gameBoard, "fruit.png", id, lat, lon, speed, radius);
	}


	public Fruit(String element) {
		super(element);
	}


	@Override
	public void addItemToList() {
		game_metadata.getFruits().add(this);
	}


	

}
