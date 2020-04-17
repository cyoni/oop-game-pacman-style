package GameObjects;

import algorithms.Point2D;
import algorithms.node_data;

public class Line {
	  private static final double r2d = 180.0D / 3.141592653589793D;
	  private static final double d2r = 3.141592653589793D / 180.0D;
	  private static final double d2km = 111189.57696D * r2d;
	    
	
	private Point2D p1, p2;
	
	public Line(Point2D p1, Point2D p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public Point2D getP1() {
		return p1;
	}

	public Point2D getP2() {
		return p2;
	}

	public static double distance2D(Point2D p1, Point2D p2) {
		double x1, x2, y1, y2;
		x1 = p1.x();
		y1 = p1.y();
		x2 = p2.x();
		y2 = p2.y();
		//System.out.println(p1 + "," + p2);
		double dis = Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2-y1), 2));;

		return dis;	
	}
	

	public static double distance(Point2D p1, Point2D p2) {
		 double lt1 = p1.x();
		 double ln1 = p1.y();
		 double lt2 = p2.x();
		 double ln2 = p2.y();
		 
	     final double x = lt1 * d2r;
	     final double y = lt2 * d2r;
	     return Math.acos( Math.sin(x) * Math.sin(y) + Math.cos(x) * Math.cos(y) * Math.cos(d2r * (ln1 - ln2))) * d2km;
	}


	
}
