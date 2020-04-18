package GameObjects;

import algorithms.Point2D;

public class MoveableObject extends Game_object implements IMoveable{

	protected double velocity = 1; // default
	private double degree = 0; // default
	private double score = 0;
	protected double eatingRadius = 10;
	
	public MoveableObject(int id, Point2D location, double velocity) {
		super(id, location);
		this.velocity = velocity;
	}
	
	public void setScore(double score) {
		this.score = score;
	}	
	
	public double getScore() {
		return score;
	}	
	
	public double getDegree() {
		return degree;
	}	
	
	public double getVelocity() {
		return velocity;
	}
	
	public void setEatingRadius(double rad) {
		eatingRadius = rad;
	}
	
	public double getEatingRadius() {
		return eatingRadius;
	}

	@Override
	public void setDegree(double deg) {
		this.degree = deg;
	}

	@Override
	public void setVelocity(double vel) {
		this.velocity = vel;
	}

}
