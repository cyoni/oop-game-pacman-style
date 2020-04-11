package GameObjects;

import Geom.Point3D;

public class Line {

	
	private Point3D p1, p2;
	
	public Line(Point3D p1, Point3D p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public Point3D getP1() {
		return p1;
	}

	public Point3D getP2() {
		return p2;
	}


	
}
