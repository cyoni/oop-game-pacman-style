package GIS;

import algorithms.Point2D;

public interface Meta_Data {
	/** returns the Universal Time Clock associated with this data; */
	public long getUTC();
	/** return a String representing this data */
	public String toString();
	/**
	 * @return the orientation: yaw, pitch and roll associated with this data;
	 */
	public Point2D get_Orientation();
}
