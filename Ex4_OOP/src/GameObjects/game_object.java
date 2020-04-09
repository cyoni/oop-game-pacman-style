package GameObjects;

import Geom.Point3D;
import game.GameBoard;
import game.Game_Metadata;

public abstract class game_object {
	private String picture;
	protected GameBoard game_metadata;
	int id;
	Point3D location;
	int speed;
	int radius;
	
	// radius- eating radius
	public game_object(GameBoard gameBoard, String picture, int id, double lat, double lon, int speed, int radius) {
		this.picture = picture;
		this.game_metadata = gameBoard;
		this.id = id;
		this.location = new Point3D(lat, lon);
		this.speed = speed;
		this.radius = radius;
	}
	
	
	public game_object(String element) {
		String element_data[] = element.split(",");
		this.id = Integer.parseInt(element_data[1]);
		this.location = new Point3D(Integer.parseInt(element_data[2]), Integer.parseInt(element_data[3]));
		this.speed = Integer.parseInt(element_data[5]);
		this.radius = Integer.parseInt(element_data[6]);
	}


	public int getId() {
		return id;
	}

	public Point3D getLocation() {
		return location;
	}

	public int getSpeed() {
		return speed;
	}

	public int getRadius() {
		return radius;
	}

	public abstract void addItemToList();

}
