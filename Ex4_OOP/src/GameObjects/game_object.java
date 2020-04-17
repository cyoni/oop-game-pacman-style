package GameObjects;



import GIS.Map;
import Geom.Point2D;
import game.GameBoard;

public class game_object {
	public String picture;

	public static int totalObjects = 0;
	private int id;
	private Point2D location;
	public static Map map;

	public game_object(int id, Point2D location) {
		this.id = id;
		this.location = location;
	}
	
	public Point2D getLocation() {
		return location;
	}
	
	public static void resetTotalObjects() {
		totalObjects = 0;
	}
	
	public void setLocation(Point2D newLocation) {
		location = newLocation;
	}

	public int getId() {
		return id;
	}
	
	public Point2D getGlobalPoint() {
		return game_object.map.pixel2global(getLocation());
	}
	


}
