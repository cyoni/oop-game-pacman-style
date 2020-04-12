package GameObjects;

import Geom.Point2D;

public class Line {

	
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


	
}
