package GameObjects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import GIS.Map;
import algorithms.Point2D;
import game.GameBoard;


public class Rectangle{

	private Point2D p_up_left, p_up_right, p_down_left, p_down_right;
	
	public Rectangle(Point2D p1, Point2D p2) {
		p_up_right = p1;
		p_down_left = p2;
		p_up_left = new Point2D(p2.x(), p1.y());
		p_down_right = new Point2D(p1.x(), p2.y());
		
		/*
		 * p_up_left = p1; p_down_right = p2; p_down_left = new Point2D(p1.x(),
		 * p_down_right.y()); p_up_right = new Point2D(p_down_right.x(), p_up_left.y());
		 */
	}
	
	public Rectangle(Rectangle rectangles) {
		this.p_up_left = new Point2D(rectangles.getP_up_left().x(), rectangles.getP_up_left().y());
		this.p_up_right = new Point2D(rectangles.getP_up_right().x(), rectangles.getP_up_right().y());
		this.p_down_left = new Point2D(rectangles.getP_down_left().x(), rectangles.getP_down_left().y());
		this.p_up_left = new Point2D(rectangles.getP_up_left().x(), rectangles.getP_up_left().y());
	}
	
	public List<Point2D> getSides() {
		List<Point2D> points = new ArrayList<>();
		points.add(p_up_left);
		points.add(p_up_right);
		points.add(p_down_left);
		points.add(p_down_right);
		return points;
	}
	
	public Point2D getP_up_left() {return p_up_left;}
	public Point2D getP_up_right() {return p_up_right;}
	public Point2D getP_down_left() {return p_down_left;}
	public Point2D getP_down_right() {return p_down_right;}
	

}
