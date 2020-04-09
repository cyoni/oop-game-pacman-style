package GIS;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Coords.MyCoords;
import Geom.*;
/**
* this class represents a map that contains functions such as converting pixels to GPS, GPS to pixels 
* @author Yoni
**/
public class Map {
	
	double  xTop = 32.1046, yTop = 35.212438,  xBottom =  32.101923, yBottom =  35.202403;		
	JFrame f;
		
	
	public Map(JFrame jframe) {
		this.f = jframe; // this point represents the max width and the max height of the screen.
	}

	/**
	* this function converts coordinate to pixel
	* @param 
	* @return coordinate
	**/
	public Point3D coordsToPixel(double x, double y) {
		int height = f.getHeight();
		int width = f.getWidth();
		System.out.println("height=" + height + ", wid:" + width);
		int y1 =  (int)((height*x - height*xTop) / (xBottom-xTop))  ; //(int) ((-f.getHeight()*x+f.getHeight()*xBottom+f.getHeight()*xTop-f.getHeight()*xBottom)/(xTop-xBottom));
		int x1 =  (int) ((width*y - width*yBottom)/(yTop-yBottom));
		return new Point3D(x1, y1, 0);
	}

	
	/**
	* this function converts pixel to coordinates
	* @param 
	* @return coordinate
	**/
	public Point3D pixelToCoords(double x, double y) {
		double x1 = (((f.getHeight()*xBottom) + (f.getHeight()-y)*(xTop-xBottom)) / f.getHeight());
		double y1 = (((f.getWidth()*yBottom) + x*(yTop-yBottom)) / f.getHeight());
		return new Point3D(x1, y1, 0);
	}
	
	
	/**
	* this function returns degree between two pixels
	* @param distance
	* @return
	**/
	public static double degreeOfPixel(int x, int y, int x2, int y2) {
		return Math.tan(Math.abs(y-y2)/Math.abs(x-x2));
	}
	
	
}
