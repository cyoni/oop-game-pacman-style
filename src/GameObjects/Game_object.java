package GameObjects;

import algorithms.Point2D;


public class Game_object {
	public String picture;
	public static int GLOBAL_ID = 0;
	private int local_id;
	private Point2D location;
	private int objectSize;

	public Game_object(int id, Point2D location) {
		this.local_id = id;
		this.location = location;
	}
	
	public Point2D getLocation() {
		return location;
	}
	
	public void setLocation(Point2D newLocation) {
		location = newLocation;
	}

	public int getId() {
		return local_id;
	}
	
	public void setObjectSize(int size) {
		objectSize = size;
	}
	
	public int getObjectSize() {
		return objectSize;
	}
		
	public static void resetTotalObjects() {
		GLOBAL_ID = 0;
	}

}
