package algorithms;

import GameObjects.Game_object;
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

	public Point2D getP1() {
		return p1;
	}

	public Point2D getP2() {
		return p2;
	}

	@Deprecated
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
	
	// temporary location!
	public synchronized static double getMouseDegree(GameBoard gameboard, Point2D global_pos_eater, Point2D global_pos_target) {
		Point2D local_pos_eater = gameboard.getMap().global2pixel(global_pos_eater);
		Point2D local_pos_target_object = gameboard.getMap().global2pixel(global_pos_target);
		double radian = Math.atan2(local_pos_eater.y()-local_pos_target_object.y(), local_pos_eater.x()-local_pos_target_object.x());
		double degree = Math.toDegrees(radian);
		degree = degree + 360;
		degree = 540 - degree;
		System.out.println(degree  + "*");
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
