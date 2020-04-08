package GIS;

import java.awt.Image;
import java.awt.Toolkit;

import Coords.MyCoords;
import Geom.*;
/**
* this class represents a map that contains functions such as converting pixels to GPS, GPS to pixels 
* @author Yoni & Nizan
**/
public class Map {
	
	double xTop = 32.107640;
	double yTop = 35.212816;
	
	double xBottom =  32.101067;
	double yBottom =  35.184181;		

	private Point3D f;
		
	
	public Map(Point3D f2) {
		this.f = f2; // this point represents the max width and the max height of the screen.
	}

	/**
	* this function converts coordinate to pixel
	* @param 
	* @return coordinate
	**/
	public Point3D coordsToPixel(double x, double y) {
		int x1 = (int) ((-f.y()*x+f.y()*xBottom+f.y()*xTop-f.y()*xBottom)/(xTop-xBottom));
		int y1 = (int) ((f.x()*y - f.x()*yBottom)/(yTop-yBottom));
		return new Point3D(y1, f.y()-x1, 0);
	}

	
	/**
	* this function converts pixel to coordinates
	* @param 
	* @return coordinate
	**/
	public Point3D pixelToCoords(double x, double y) {
		double x1 = (((f.y()*xBottom) + (f.y()-y)*(xTop-xBottom)) / f.y());
		double y1 = (((f.x()*yBottom) + x*(yTop-yBottom)) / f.x());
		return new Point3D(x1, y1, 0);
	}
	
	
	/**
	* this function returns degree between two pixels
	* @param distance
	* @return
	**/
	public double degreeOfPixel(int x, int y, int x2, int y2) {
		return Math.tan(Math.abs(y-y2)/Math.abs(x-x2));
	}
	
	
}
