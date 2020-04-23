package GameObjects;

import algorithms.Point2D;

public class ObjectEatsFruit extends MoveableObject {

	public ObjectEatsFruit(int id, Point2D location, double velocity) {
		super(id, location, velocity);
		// TODO Auto-generated constructor stub
	}
	
	
	public boolean eat(Game_object something) {
		return (something instanceof Fruit);
	}

}
