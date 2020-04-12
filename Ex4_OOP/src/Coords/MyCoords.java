package Coords;
import Geom.Point2D;

/**
 * This class represents a basic coordinate system with some functions that calculate distances, sums GPS coordinate with a vector
 * calculate an azimuth and elevation and some more
 * 
 * @author Yoni
 **/

public class MyCoords implements coords_converter { 
	

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




	@Override
	public Point2D add(Point2D gps, Point2D local_vector_in_meter) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public double distance3d(Point2D gps0, Point2D gps1) {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public Point2D vector3D(Point2D gps0, Point2D gps1) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public double[] azimuth_elevation_dist(Point2D gps0, Point2D gps1) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public boolean isValid_GPS_Point(Point2D p) {
		// TODO Auto-generated method stub
		return false;
	}
	



}