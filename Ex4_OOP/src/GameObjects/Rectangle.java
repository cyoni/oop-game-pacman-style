package GameObjects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import GIS.Map;

import Geom.Point3D;
import game.GameBoard;


public class Rectangle{

	private Point3D p_up_left;
	private Point3D p_up_right;
	private Point3D p_down_left;
	private Point3D p_down_right;
	
	public Rectangle(Point3D p1, Point3D p2) {
		p_up_left = p1;
		p_down_right = p2;
		p_down_left = new Point3D(p1.x(), p_down_right.y());
		p_up_right = new Point3D(p_down_right.x(), p_up_left.y());
	}
	
	public Rectangle(Rectangle rectangles) {
		this.p_up_left = new Point3D(rectangles.getP_up_left().x(), rectangles.getP_up_left().y());
		this.p_up_right = new Point3D(rectangles.getP_up_right().x(), rectangles.getP_up_right().y());
		this.p_down_left = new Point3D(rectangles.getP_down_left().x(), rectangles.getP_down_left().y());
		this.p_up_left = new Point3D(rectangles.getP_up_left().x(), rectangles.getP_up_left().y());
	}
	
	public List<Point3D> getSides() {
		List<Point3D> points = new ArrayList<>();
		points.add(p_up_left);
		points.add(p_up_right);
		points.add(p_down_left);
		points.add(p_down_right);
		return points;
	}
	
	public Point3D getP_up_left() {return p_up_left;}
	public Point3D getP_up_right() {return p_up_right;}
	public Point3D getP_down_left() {return p_down_left;}
	public Point3D getP_down_right() {return p_down_right;}
	

}
