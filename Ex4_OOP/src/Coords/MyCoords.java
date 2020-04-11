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
	

	
	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		double x = local_vector_in_meter.x();   
		double y = local_vector_in_meter.y();
		double z = local_vector_in_meter.z();

		double _x = (x/(2*Math.PI*EARTH_RADIUS*1000))*360; 
		double _y = y/(2*Math.PI*EARTH_RADIUS*1000)*360*Math.cos(Math.atan(y/x))*Math.PI/180;

		return new Point3D(_x+gps.x(), _y+gps.y(), z+gps.z());
	}

	
	public double distance2D(Point3D gps0, Point3D gps1) {
		Point3D vector = vector3D(gps0, gps1);
		return vector.distance2D(new Point3D(0,0,0));	
	}
	public Point3D midPoint(Point3D gps0, Point3D gps1, double ratio) {
		Point3D vector = vector3D(gps0, gps1);
		Point3D ratioVector = ratioVector(vector, ratio); 
		return add(gps0, ratioVector);
	}
	public Point3D ratioVector(Point3D vector, double ratio) {
		return new Point3D(ratio*vector.x(), ratio*vector.y(), ratio*vector.z());
	}
	
	
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


	
	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		double x = EARTH_RADIUS * Math.sin(Math.toRadians(gps1.x() - gps0.x()));
		double y = EARTH_RADIUS * Math.sin(Math.toRadians(gps1.y() - gps0.y())) * Math.cos(Math.toRadians(gps0.x()));
		double z = gps1.z() - gps0.z();

		return new Point3D(x, y, z);
	}
	

	public double azimuth(Point3D gps0, Point3D gps1) {
		double lat0Radian = Math.toRadians(gps0.x()); //teta1
		double lat1Radian = Math.toRadians(gps1.x()); //teta2
		double diffLon = gps1.y()-gps0.y();
		double diffLonRadian = Math.toRadians(diffLon);  //delta2
		
		double numerator = Math.sin(diffLonRadian) * Math.cos(lat1Radian);
		double denominator = Math.cos(lat0Radian)*Math.sin(lat1Radian) - Math.sin(lat0Radian)*Math.cos(lat1Radian)*Math.cos(diffLonRadian);
		return (Math.toDegrees(Math.atan2(numerator,denominator))+360) % 360;
	}
	
 /*
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


@Override
public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
	
/*	double[] polarVec = new double[3];
	if (isValid_GPS_Point(gps0)&&isValid_GPS_Point(gps1)) {
		Point3D vec = vector3D(gps0,gps1);
		polarVec[2] = distance3d(gps0,gps1);
		polarVec[0] = Math.toDegrees(Math.atan(Math.abs((vec.y())/(vec.x()))));
		
		double y = (Math.sin(gps1.x()-gps0.x())*Math.cos(gps1.y()));
		double x = (Math.cos(gps0.y())*Math.sin(gps1.y()))-(Math.sin(gps0.y())*Math.cos(gps1.y())*Math.cos(gps1.x()-gps0.x()));
		polarVec[0] = Math.atan2(y, x);
		polarVec[0] = Math.toDegrees(polarVec[0]);

		if (polarVec[0]<0) polarVec[0]+=360;
		if (polarVec[0]>360) polarVec[0]-=360;
		
		polarVec[1] = Math.toDegrees(Math.asin(vec.z()/polarVec[2]));
		
		return polarVec;
	}
	return null;*/
}


}