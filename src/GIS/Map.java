package GIS;


import javax.swing.JFrame;
import Coords.MyCoords;
import GUI.Screen;
import algorithms.Point2D;


public class Map {

	

	public Point2D nw;
	public Point2D ne;
	public Point2D sw;
	public Point2D se;
	MyCoords mc;

	
	protected Point2D DownLeftP = new Point2D( 35.202403,32.101923,0);
	protected Point2D UpRightP = new Point2D( 35.212438,32.105959,0);
	
	public Map(JFrame jframe) {
		nw = new Point2D( 32.105394,  35.202532, 0);
		ne = new Point2D( 32.105444,  35.212496, 0);
		sw = new Point2D( 32.101899,  35.202447, 0);
		se = new Point2D( 32.101899,  35.212496, 0);
		mc=new MyCoords();
	}
	public Point2D global2pixel(Point2D Global) {
		
		double RatioGlobalX = (Global.x()-DownLeftP.x())/getGlobalDiffX();
		double RatioGlobalY = (UpRightP.y()-Global.y())/getGlobalDiffY();
		double pixelX = Screen.WIDTH*RatioGlobalX;
		double pixelY = Screen.HEIGHT*RatioGlobalY;
		
		return new Point2D(pixelX,pixelY,0);
	}
	public Point2D pixel2global(Point2D pixel) {

		double RatioPixelX = pixel.x()/Screen.WIDTH;
		double RatioPixelY = (pixel.y())/Screen.HEIGHT;
		double GlobalX = DownLeftP.x()+(getGlobalDiffX()*RatioPixelX);
		double GlobalY = UpRightP.y()-(getGlobalDiffY()*RatioPixelY);
		return new Point2D(GlobalX,GlobalY,0);
	}
	/** 
	 * @return the width of the pic in degree 
	 */
	public double getGlobalDiffX() {
		return UpRightP.x()-DownLeftP.x();
	}
	/** 
	 * @return the hight of the pic in degree 
	 */
	public double getGlobalDiffY() {
		return UpRightP.y()-DownLeftP.y();
	}
	
	
	/**
	* this function converts coordinate to pixel
	* @param 
	* @return coordinate
	**/
	/*public Point2D coordsToPixel(double x, double y) {
		Point2D Global = new Point2D(x,y);
		double RatioGlobalX = (Global.x()-DownLeftP.x())/getGlobalDiffX();
		double RatioGlobalY = (UpRightP.y()-Global.y())/getGlobalDiffY();
		double pixelX = Screen.WIDTH*RatioGlobalX;
		double pixelY = Screen.HEIGHT*RatioGlobalY;
		return new Point2D(pixelX,pixelY,0);

	}

	
	*//**
	* this function converts pixel to coordinates
	* @param 
	* @return coordinate
	**//*
	public Point2D pixelToCoords(double x, double y) {
		Point2D pixel = new Point2D(x,y);
		double RatioPixelX = pixel.x()/Screen.WIDTH;
		double RatioPixelY = (pixel.y())/Screen.HEIGHT;
		double GlobalX = DownLeftP.x()+(getGlobalDiffX()*RatioPixelX);
		double GlobalY = UpRightP.y()-(getGlobalDiffY()*RatioPixelY);
		return new Point2D(GlobalX,GlobalY,0);

	}	*/
	
}
