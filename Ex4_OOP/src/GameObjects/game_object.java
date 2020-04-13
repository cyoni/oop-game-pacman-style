package GameObjects;



import GIS.Map;
import Geom.Point2D;
import game.GameBoard;

public class game_object {
	public String picture;

	int id = -1;
	private Point2D location;

	private game_object target;

	public game_object(int id, Point2D location) {
		this.id = id;
		this.location = location;
	}
	

	public Point2D getLocation() {
		return location;
	}
	
	public void setLocation(Point2D newLocation) {
		location = newLocation;
	}

	public int getId() {
		return id;
	}


	public game_object getTarget() {
		return target;
	}
	
	public void setTarget(game_object target) {
		this.target = target;
	}

}
