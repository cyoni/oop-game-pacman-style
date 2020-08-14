package GIS;


import javax.swing.JFrame;
import Coords.MyCoords;
import GUI.Screen;
import algorithms.Point2D;


public class Map {

	
	private static Point2D DownLeftP = new Point2D(35.202403, 32.101923);
	private static Point2D UpRightP = new Point2D(35.212438, 32.105959);
	
	public static Point2D convertGlobalPointToPixelPoint(Point2D Global) {
		double RatioGlobalX = (Global.x() - DownLeftP.x()) / getScreenGlobalDiffX();
		double RatioGlobalY = (UpRightP.y() - Global.y()) / getScreenGlobalDiffY();
		double pixelX = Screen.WIDTH * RatioGlobalX;
		double pixelY = Screen.HEIGHT * RatioGlobalY;
		return new Point2D(pixelX,pixelY);
	}
	
	public static Point2D convertPixelToglobal(Point2D pixel) {
		double RatioPixelX = pixel.x() / Screen.WIDTH;
		double RatioPixelY = (pixel.y()) / Screen.HEIGHT;
		double GlobalX = DownLeftP.x() + (getScreenGlobalDiffX()*RatioPixelX);
		double GlobalY = UpRightP.y() - (getScreenGlobalDiffY()*RatioPixelY);
		return new Point2D(GlobalX, GlobalY);
	}
	

	private static double getScreenGlobalDiffX() {
		return UpRightP.x()-DownLeftP.x();
	}

	private static double getScreenGlobalDiffY() {
		return UpRightP.y()-DownLeftP.y();
	}
	
	
}
