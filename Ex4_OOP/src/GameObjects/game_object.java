package GameObjects;



import GIS.Map;
import Geom.Point3D;
import game.GameBoard;

public class game_object {
	public String picture;

	int id = -1;
	private Point3D location;

	public game_object(int id, Point3D location) {
		this.id = id;
		this.location = location;
	}
	

	public Point3D getLocation() {
		return location;
	}

	public int getId() {
		return id;
	}


}
