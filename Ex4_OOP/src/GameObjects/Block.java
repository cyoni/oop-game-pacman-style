package GameObjects;

import GIS.Map;
import Geom.Point3D;
import game.GameBoard;


public class Block{

	private Point3D location_p2;
	private Point3D location_p1;

	
	public Block(GameBoard gameBoard, Map map, int id, double lat, double lon, double lat2, double lon2) {
		this.location_p1 = map.coordsToPixel(lat, lon);
		this.location_p2 =  map.coordsToPixel(lat2, lon2);

	}

	public Point3D getLocation_p1() {
		return location_p1;
	}
	
	public Point3D getLocation_p2() {
		return location_p2;
	}
	



}
