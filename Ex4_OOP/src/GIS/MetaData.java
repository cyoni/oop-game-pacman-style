/*package GIS;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import Geom.Point3D;
*//** 
 * this class represents a data structure that contains data of layers, projects and elements
 *  @author Nizan and Yoni
 **//*
public class MetaData implements Meta_data {

	private final String defaultColor = "yellow";
	private String name;
	private String mac;//bssid
	private String authMode;//capacbility
	private String firstSeen;
	private String timestamp;
	private String color;

	*//** 
	 * Empty constructor
	 **//*
	public MetaData() {
	}

	*//** 
	 * constructor that gets some parameters and save them
	 * @param name  
	 * @param firstSeen  
	 * @param mac  
	 * @param authMode  
	 **//*
	public MetaData(String name, String firstSeen, String mac, String authMode) {
		this.name = name;
		this.mac = mac;
		this.firstSeen = firstSeen;
		this.authMode = authMode;
		setRandomColor();
		this.timestamp = Double.toString(new Date().getTime());
	}

	*//** 
	 * This function chooses a random color to the dot that represents a place on google earth
	 **//*
	private void setRandomColor() {
		int ran = new Random().nextInt(3);
		if (ran == 0)
			setColor("yellow");
		else if(ran == 1)
			setColor("red");
		else
			setColor("green");
	}

	*//** 
	 * copy constructor 
	 * @param md  
	 **//*
	public MetaData(MetaData md) {
		this(md.name, md.firstSeen, md.mac, md.authMode);
	}

	*//** 
	 * This function converts a date and a time into UTC
	 * @return utc in millisecond
	 **//*
	@Override
	public long getUTC()  {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = null;
		try {
			d = sdf.parse(firstSeen);
		} catch (Exception e) {
			System.out.println(e);
		}
		return d.getTime();
	}

	@Override
	public Point3D get_Orientation() {
		return null;//not for this ex
	}

	*//** 
	 * This function returns a name value
	 * @return name  
	 **//*
	public String getName() {
		return name;
	}

	*//** 
	 * This function returns a mac value
	 * @reutn mac
	 **//*
	public String getMac() {
		return mac;
	}

	*//** 
	 * This function returns a authMode value
	 * @return authMode
	 **//* 
	public String getAuthMode() {
		return authMode;
	}

	*//** 
	 * This function returns a date and time of the object
	 * @return date  
	 **//*
	public String getDate() {
		return firstSeen;
	}

	*//** 
	 * This function returns the time stamp of the object
	 * @return timestamp
	 **//*
	public String getTimestamp() {
		return timestamp;
	}

	*//** 
	 * This function returns the color that represents the object
	 * @return color
	 **//*
	public String getColor() {
		return color;
	}

	*//** 
	 * This function set a certain color to an object
	 * @param color  
	 **//*
	public void setColor(String color) {
		this.color = color;
	}

	*//** 
	 * This function returns a first-seen value
	 * @return firstSeen
	 **//*
	public String getFirstSeen() {
		return firstSeen;
	}

	*//** 
	 * This function sets first-seen value in the MT
	 * @param firstSeen
	 **//*
	public void setFirstSeen(String firstSeen) {
		this.firstSeen = firstSeen;
	}

	*//** 
	 * This function sets a name value in the MT
	 * @param name  
	 **//*
	public void setName(String name) {
		this.name = name;
	}

	*//** 
	 * This function sets a mac value in the MT
	 * @param mac
	 **//*
	public void setMac(String mac) {
		this.mac = mac;
	}

	*//** 
	 * This function set an AuthMode value in the MT
	 * @param authMode
	 **//*
	public void setAuthMode(String authMode) {
		this.authMode = authMode;
	}

	*//** 
	 * This function set a Time-Stamp on the MT
	 * @param timestamp
	 **//*
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	*//** 
	 * This function checks whether the data of this MT equals another MT
	 * @param md  
	 * @return true if md equal to this metadata
	 **//*
	public boolean equals(MetaData md) {
		return (name.equals(md.name) && mac.equals(md.mac) && authMode.equals(md.authMode) && 
				firstSeen.equals(md.firstSeen) && color.equals(md.color));

	}

	*//** 
	 * This function returns a string of this MT
	 * @return tostring
	 **//*
	@Override
	public String toString() {
		return "name: " + this.name + ", MAC: " + this.mac + ", AuthMode: " + this.authMode + 
				", Date: " + this.firstSeen +  ", Timestamp: " + this.timestamp;
	}
}*/