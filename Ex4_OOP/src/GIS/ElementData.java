package GIS;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;
/** 
 * This class represents an element with data
 * @author Nizan and Yoni
 **/
public class ElementData implements GIS_element {

	private MetaData md;
	private Point3D gps;
	
	/** 
	 * An Empty Constructor
	 **/
	public ElementData() {
		md = new MetaData();
		gps = new Point3D(0, 0, 0);
	}

	/** 
	 * Constructor that gets some parameters and stores them in memory
	 * @param name  
	 * @param date 
	 * @param mac  
	 * @param authMode  
	 * @param lon   
	 * @param lat    
	 * @param alt   
	 **/
	public ElementData(String name, String date, String mac, String authMode,
			double lon, double lat, double alt) {
		this.md = new MetaData(name, date, mac, authMode);
		this.gps = new Point3D(lat, lon, alt);
	}

	/** 
	 * Constructor that gets some parameters and an object of coordinates and stores them in memory
	 * @param name  
	 * @param date     
	 * @param mac   
	 * @param authMode   
	 * @param cd   
	 **/
	public ElementData(String name, String date, String mac, String authMode, Point3D cd) {
		this.md = new MetaData(name, date, mac, authMode);
		this.gps = new Point3D(cd); // gets an object of coordinates
	}

	/** 
	 * Constructor that gets an object of type metaData and some parameters and stores them in memory
	 * @param md   
	 * @param lon   
	 * @param lat   
	 * @param alt   
	 **/
	public ElementData(MetaData md, double lon, double lat, double alt) {
		this.md = new MetaData(md);
		this.gps = new Point3D(lat, lon, alt);
	}

	/** 
	 * Copy constructor
	 * @param ed  
	 **/
	public ElementData(ElementData ed) {
		this(ed.md, ed.gps);
	}

	/** 
	 * Constructor that gets two objects of metaData and coordinates and stores them in memory
	 * @param md  
	 * @param gps  
	 **/
	public ElementData(MetaData md, Point3D gps) {
		this.md = new MetaData(md);
		this.gps = new Point3D(gps);
	}

	/** 
	 * this function returns the coordinates of an element
	 * @return gps point
	 **/
	@Override
	public Geom_element getGeom() {
		return this.gps;
	}
	/** 
	 * this function returns the data of an element (with out its GPS data)
	 * return md data about the point
	 **/
	@Override
	public Meta_data getData() {
		return null;
	}

	/** 
	 * this function checks whether an element's MetaData equals its GPS data
	 * @param ed other ElementData
	 * @return true if ed eauals to this elementdata
	 **/
	public boolean equals(ElementData ed) {
		return equals(ed.md, ed.gps);
	}
	/** 
	 * this function checks whether element's data equals a given data and whether element's GPS equals a given GPS 
	 * @param md  
	 * @param gps  
	 * @return true if md and gps equal to gps and md of this elementdata
	 **/
	public boolean equals(MetaData md, Point3D gps) {
		return this.gps.equals(gps) && this.md.equals(md);
	}

	/** 
	 * this function converts to meters the GPS and sums this element's GPS and a given vector. 
	 * @param vec  
	 **/
	@Override
	public void translate(Point3D vec) {
		Point3D p = new Point3D(this.gps);
		gps = new MyCoords().add(p, vec);		
	}

	/** 
	 * this function returns a string of this element's GPS and data
	 * @return tostring
	 **/
	@Override
	public String toString() {
		return "cord: " +  gps.toString() + ", metadata: " + md.toString();
	}

}