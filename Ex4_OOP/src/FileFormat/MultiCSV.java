package FileFormat;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import GIS.ElementData;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.LayerData;
import GIS.MetaData;
import GIS.ProjectData;
import Geom.Point3D;
/**
 * this class represents a data structure of kml elements 
 * We took an idea of a code in this link to implement the function that scan CSV files in folders recursively    
 * http://www.avajava.com/tutorials/lessons/how-do-i-recursively-display-all-files-and-directories-in-a-directory.html
 * 
 * @author Nizan and Yoni
 */


public class MultiCSV  {

	private ProjectData pd;
	private File file;
	private ArrayList<File> al;
	private String kmlFile;

	/**
	 * Constructor that receives a path and a name of a kml file
	 * @param path the folder to search
	 * @param name of the kml to save
	 **/
	public MultiCSV(String path, String kmlFile) {
		this.pd = new ProjectData();
		this.al = new ArrayList<>();
		this.file = new File(path);
		this.kmlFile = kmlFile;
	}

	/**
	 * this function scan recursively files that ends with csv and adds them to a list which is called "al"
	 * @param path the folder to search
	 **/
	public void findAllCsv(File path) {
		if(path.isDirectory()) {
			for(File temp : path.listFiles()) {
				if(temp.isDirectory())
					findAllCsv(temp);
				else if(temp.isFile()) {
					if(temp.getName().endsWith(".csv"))
						al.add(temp);
					findAllCsv(temp);
				}
			}
		}
	}

	/**
	 * this function read all csv files and convert them and add the to ProjectData
	 * @return true if read successfully
	 */
	public boolean read() {
		if(al.size() > 0) {
			for (int i = 0; i < al.size(); i++) {
				Csv2kml t = new Csv2kml(al.get(i).getPath(), "");
				if(t.readCsv()) {}
				//	pd.add(t.getLd());
				//else
				//	return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * This function writes one Kml file. It goes through the items in the list of the CSV items that it found and
	 * creates the proper elements.
	 * @return true if write was successfull
	 **/
	public boolean write() {

		try{
			if(!kmlFile.endsWith(".kml"))
				throw new RuntimeException("kml file error");

			PrintWriter writer = new PrintWriter(file.getPath() + "/" + this.kmlFile, "UTF-8");

			writer.write(Csv2kml.top + "\n");
			for(GIS_layer t1 : pd) { // a loop that goes through the package and receives its Layers
				for (GIS_element t : t1) { // now we will use a loop that will read the elements in the current layer
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
			}
			writer.write(Csv2kml.buttom); // writes the ending part of the file
			writer.close();
			return true;
		} catch(FileNotFoundException e) {
			System.err.println(e);
			return false;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
	}

	/**
	 * This function finds the files that end with "csv", reads them and writes all of them into one kml file
	 * @return true if the conversion was successful 
	 **/
	public boolean ConvertAll() {
		findAllCsv(file);
		boolean b = read();
		if(b)
			System.out.println("read completed");
		else {
			System.out.println("read failed");
			return false;
		}
		b = write();
		if(b) {
			System.out.println("write completed");
			return true;
		}
		else
			System.out.println("write failed");
		return false;
	}
}