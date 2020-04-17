package Coords;
import Geom.Point2D;

/**
 * This class represents a basic coordinate system with some functions that calculate distances, sums GPS coordinate with a vector
 * calculate an azimuth and elevation and some more
 * 
 * @author Yoni
 **/

public class MyCoords{ 
	

	public static final double EARTH_RADIUS = 6371000;
	


	
	public double distance2D(Point2D p1, Point2D p2) {
		double x1, x2, y1, y2;
		x1 = p1.x();
		y1 = p1.y();
		x2 = p2.x();
		y2 = p2.y();
		//System.out.println(p1 + "," + p2);
		double dis = Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2-y1), 2));;
	
		return dis;	
	}

    private static final double r2d = 180.0D / 3.141592653589793D;
    private static final double d2r = 3.141592653589793D / 180.0D;
    private static final double d2km = 111189.57696D * r2d;
    
    public static double meters(double lt1, double ln1, double lt2, double ln2) {
        final double x = lt1 * d2r;
        final double y = lt2 * d2r;
        return Math.acos( Math.sin(x) * Math.sin(y) + Math.cos(x) * Math.cos(y) * Math.cos(d2r * (ln1 - ln2))) * d2km;
    }


}