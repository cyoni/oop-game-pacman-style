package Coords;

import Geom.Point3D;

/**
 * This class represents a basic coordinate system with some functions that calculate distances, sums GPS coordinate with a vector
 * calculate an azimuth and elevation and some more
 * 
 * @author Yoni
 **/

public class MyCoords implements coords_converter {

	public static final double EARTH_RADIUS = 6371000;
	
	/**
	 * This function gets two parameters: 1. GPS 2. Vector and it converts the vector to degrees and sums it and the GPS and the new GPS coordinate 
	 * @param gps point
	 * @param local_vector_in_meter vector
	 * @return new gps point
	 **/
	
	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		double x = local_vector_in_meter.x();   
		double y = local_vector_in_meter.y();
		double z = local_vector_in_meter.z();

		double _x = (x/(2*Math.PI*EARTH_RADIUS*1000))*360; 
		double _y = y/(2*Math.PI*EARTH_RADIUS*1000)*360*Math.cos(Math.atan(y/x))*Math.PI/180;

		return new Point3D(_x+gps.x(), _y+gps.y(), z+gps.z());
	}

	/**
	 * This function calculates a distance between two GPS coordinates and returns it
	 * @param gps0 start point
	 * @param gps1 end point
	 * @return distance the distance from gps0 to gps1
	 **/
	
	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		double diffx, diffy, toMeter1, toMeter2, lonNorm;
		lonNorm = Math.cos(Math.toRadians(gps0.x()));
		diffx = Math.toRadians(gps0.x() - gps1.x());
		diffy = Math.toRadians(gps0.y() - gps1.y());
		toMeter1 = Math.sin(diffx) * EARTH_RADIUS;
		toMeter2 = Math.sin(diffy) * EARTH_RADIUS * lonNorm;
		return Math.sqrt(Math.pow(toMeter1, 2) + Math.pow(toMeter2, 2));
	}

	/**
	 * This function returns a vector of two GPS coordinates
	 * @param gps0 start point
	 * @param gps1 end point
	 * @return vector in meter
	 **/
	
	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		double x = EARTH_RADIUS * Math.sin(Math.toRadians(gps1.x() - gps0.x()));
		double y = EARTH_RADIUS * Math.sin(Math.toRadians(gps1.y() - gps0.y())) * Math.cos(Math.toRadians(gps0.x()));
		double z = gps1.z() - gps0.z();

		return new Point3D(x, y, z);
	}
	/**
	 * This function calculate an azimuth, elevation and a distance between two GPS coordinates 
	 * @param gps0 start point
	 * @param gps1 end point
	 * @return array of azimuth, elevation, distance
	 **/
	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		double aed[] = new double[3];
		double distance = distance3d(gps0, gps1);
		double azimuth;
		double elevation;
		double g, p1, p2, x, y;
		p1 = Math.toRadians(gps0.x());
		p2 = Math.toRadians(gps1.x());
		g = Math.toRadians(gps0.y()-gps1.y());

		y = Math.sin(g)*Math.cos(p1);
		x = (Math.cos(p2)* Math.sin(p1)) - Math.sin(p2)*Math.cos(p1)*Math.cos(g);
		azimuth = Math.atan2(y, x) + Math.PI;

		elevation = Math.asin ((gps1.z() - gps0.z()) / distance);		

		aed[0] = Math.toDegrees(azimuth);
		aed[1] = elevation;
		aed[2] = distance;

		return aed;
	}
	
	/**
	 * This function checks whether a coordinate is still within its range
	 * @param gps point
	 * @return  boolean that represents whether the GPS point is valid
	 **/
	
	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		boolean isValid;
		if (p.y() > 180 || p.y() < -180) isValid = false;
		else if (p.x() > 90 || p.x() < -90) isValid = false;
		else if (p.z() <-450) isValid = false;
		else isValid = true;
		
		return isValid;
	}


}