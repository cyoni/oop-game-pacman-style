package FileFormat;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import GIS.*;
import Geom.Point3D;
/** 
 *  This class lets users to convert csv file into kml file
 * @author Nizan and Yoni
 */
public class Csv2kml {

	private final String name = "SSID", bbsid = "MAC", capabilties = "AuthMode", 
			date = "FirstSeen", lat = "CurrentLatitude", lon = "CurrentLongitude"; // Variables to the switch-case code 

	public static final String top = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
			"<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document>" +
			"<Style id=\"red\"><IconStyle>" +
			"<Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon>" +
			"</IconStyle></Style>"+
			"<Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon>" +
			"</IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href>" +
			"</Icon></IconStyle></Style><Folder><name>NETWORKS</name>";

	public static final String buttom = "</Folder>\r\n" + 
			"</Document></kml>";

	private String firstRaw;
	private String csvFile;
	private String kmlFile;
	private LayerData ld;
	private File file;
	private BufferedReader br;
	private PrintWriter writer;

	/** 
	 * This function creates a new Layer
	 **/
	public Csv2kml() {
		this.ld = new LayerData();
	}

	/**
	 * Constructor. Gets a location adress and create kml file with same name as the csv
	 * @param csvFile 
	 */
	public Csv2kml(String csvFile) {
		this.csvFile = csvFile;
		this.kmlFile = csvFile.substring(0, csvFile.length()-3) + "kml";
		this.ld = new LayerData();
	}


	/** 
	 * Constructor. Gets a location address and a destination
	 * @param csvFile
	 * @param kmlFile
	 **/
	public Csv2kml(String csvFile, String kmlFile) {
		this.csvFile = csvFile;
		this.kmlFile = kmlFile;
		this.ld = new LayerData();
	}

	/** 
	 * This function returns the path of the csv file
	 **/
	public String getCsvFile() {
		return csvFile;
	}

	/** 
	 * This function sets the path of a csv file
	 **/
	public void setCsvFile(String csvFile) {
		this.csvFile = csvFile;
	}

	/** 
	 * This function returns the path of the kml file
	 **/
	public String getKmlFile() {
		return kmlFile;
	}

	/** 
	 * This function sets an address of a kml file
	 **/
	public void setKmlFile(String kmlFile) {
		this.kmlFile = kmlFile;
	}

	/** 
	 * This function returns true or false whether the file is good to go
	 **/
	public boolean checkFile() {
		return file.exists() && file.isFile() && file.getName().endsWith(".csv");
	}
	/** 
	 * This function calls readCSV and writeKml functions 
	 * @return if convert finish successfully
	 **/
	public boolean covert() {
		boolean b = readCsv();
		if(b)
			System.out.println("read completed");
		else {
			System.out.println("read failed");
			return false;
		}
		b = writeKml();
		if(b) {
			System.out.println("write completed");
			return true;
		}
		else
			System.out.println("write failed");
		return false;
	}

	/** 
	 * This function reads a csv file. First, it reads a line out of the file and gets its parameters and then runs a for loop to read 
	 * and set its data in the proper places and finally adds the new element to the Layer.
	 * The following function SKIPS the first line in the CSV file
	 * @return true if read successfully
	 **/
	public boolean readCsv() {
		String st; 
		try { 
			file = new File(csvFile); 

			if(!checkFile())
				throw new RuntimeException("file error");

			br = new BufferedReader(new FileReader(file)); 			
			br.readLine();
			firstRaw = br.readLine(); 
			String[] a = firstRaw.split(",");

			while((st = br.readLine()) != null) {
				String[] str = st.split(",");

				Point3D p = new Point3D(0, 0, 0); // to store the coordinates for each element
				MetaData md = new MetaData(); // creates a new meta data object to store data for each element

				for (int i = 0; i < str.length; i++) {

					switch (a[i]) {
					//name
					case name:
						md.setName(str[i]);
						break;
						//mac
					case bbsid:
						md.setMac(str[i]);
						break;
						//capabilties(authmode)
					case capabilties:
						md.setAuthMode(str[i]);
						break;
						//date
					case date:
						md.setFirstSeen(str[i]);
						break;
						//cord x
					case lat:
						double x = Double.parseDouble(str[i]);
						p.add(x, 0, 0 );
						break;
						//cord y
					case lon:
						double y = Double.parseDouble(str[i]);
						p.add(0, y, 0);
						break;
					}
				}
				
				ElementData t = new ElementData(md, p); // create a new element
				ld.add(t); // add to the layer the new element

			}
			br.close();
			return true;
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (Exception e) {
			System.err.println(e);
		}
		return false;
	}

	/** 
	 * This function writes to a Kml file. It retrieves the data of each element 
	 * from the Layer and writes them in the file.
	 * @return true if write successfuly
	 * 
	 **/
	private boolean writeKml() {
		if(!kmlFile.endsWith(".kml"))
			throw new RuntimeException("kml file error");

		try {
			writer = new PrintWriter(kmlFile, "UTF-8");

			writer.write(top + "\n");
			for (GIS_element t : ld) { // retrieves the elements from the list
				writer.println("<Placemark>"); 
				ElementData tempElement = new ElementData((ElementData) t); // creates a new object of an element
				MetaData tempMetadata = new MetaData((MetaData)tempElement.getData()); // gets its data
				Point3D tempCoords = new Point3D((Point3D)tempElement.getGeom()); // gets its coordinates 

				writer.println("<name><![CDATA[" + tempMetadata.getName() + "]]></name>");
				writer.println("<description>");
				writer.println("<![CDATA[" + "BBSID: <b>" + tempMetadata.getMac() + "</b><br/>" + 
						"Capabilities: <b>" + tempMetadata.getAuthMode() + "</b><br/>" +
						"TimeStemp: <b>" + tempMetadata.getTimestamp() + "</b><br/>" +
						" Date: <b>" + tempMetadata.getDate() + "</b><br/>" + "]]>");

				writer.println("</description>");

				writer.println("<styleUrl>#" + tempMetadata.getColor() + "</styleUrl>\n<Point><coordinates>");
				writer.println(tempCoords.y() + ", " + tempCoords.x());
				writer.println("</coordinates></Point>");

				writer.println("</Placemark>");

			}

			writer.write(buttom);
			writer.close();
			return true;
		} catch(FileNotFoundException e) {
			System.err.println(e);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		return false;
	}

	/** 
	 * This function returns the Layer
	 * @return ld 
	 **/
	public LayerData getLd() {
		return ld;
	}
}