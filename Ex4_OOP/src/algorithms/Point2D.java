package algorithms;

import java.io.Serializable;
public class Point2D  
{
	/**
	 * This class represents a 3D point in space.
	 */
	private double x,y,z;

	public Point2D(double x,double y, double i) 
	{
		this.x=x;
		this.y=y;
	}
	public Point2D(double x,double y) 
	{
		this.x=x;
		this.y=y;
	}

	public Point2D(Point2D p) 
	{
		this.x=p.x();
		this.y=p.y();
		
	}
	
	
	public String toString() {
		return "[" + x + "," + y +"]";
	}
	public double x() { return x;}
	public double y() { return y;}


}
