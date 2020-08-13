package algorithms;

import GIS.Map;
import GameObjects.GameObject;
import game.GameBoard;

public class Line {
	  private static final double r2d = 180.0D / 3.141592653589793D;
	  private static final double d2r = 3.141592653589793D / 180.0D;
	  private static final double d2km = 111189.57696D * r2d;
	    
	
	private Point2D p1, p2;
	
	public Line(Point2D p1, Point2D p2) {
		this.p1 = p1;
		this.p2 = p2;
		p1.setX(p1.x()+10);
		p1.setY(p1.y()+20);
		
		p2.setX(p2.x()+10);
		p2.setY(p2.y()+20);
		
	}
	
	public static double distBetweenPointAndLine(double x, double y, double x1, double y1, double x2, double y2) {
	    // A - the standalone point (x, y)
	    // B - start point of the line segment (x1, y1)
	    // C - end point of the line segment (x2, y2)
	    // D - the crossing point between line from A to BC

		double AB = distBetween(x, y, x1, y1);
		double BC = distBetween(x1, y1, x2, y2);
		double AC = distBetween(x, y, x2, y2);

	    // Heron's formula
		double s = (AB + BC + AC) / 2;
		double area =  Math.sqrt(s * (s - AB) * (s - BC) * (s - AC));

	    // but also area == (BC * AD) / 2
	    // BC * AD == 2 * area
	    // AD == (2 * area) / BC
	    // TODO: check if BC == 0
		double AD = (2 * area) / BC;
	    return AD;
	}

	private static double distBetween(double x, double y, double x1, double y1) {
		double xx = x1 - x;
		double yy = y1 - y;

	    return  Math.sqrt(xx * xx + yy * yy);
	}

	public Point2D getP1() {
		return p1;
	}

	public Point2D getP2() {
		return p2;
	}
	
	public String toString() {
		return p1.toString() + ", " + p2.toString();
	}

	// temporary location!
	public static double getMouseDegree(GameBoard gameboard, Point2D global_pos_eater, Point2D global_pos_target) {
		Point2D local_pos_eater = Map.convertGlobalPointToPixelPoint(global_pos_eater);
		Point2D local_pos_target_object = Map.convertGlobalPointToPixelPoint(global_pos_target);
		double radian = Math.atan2(local_pos_eater.y()-local_pos_target_object.y(), local_pos_eater.x()-local_pos_target_object.x());
		double degree = Math.toDegrees(radian);
		degree = degree + 360;
		degree = 540 - degree;
		return degree;
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
