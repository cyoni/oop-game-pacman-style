
package File_format;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import GIS.Fruit;
import GIS.GameMetaData;
import Geom.Point3D;
/**
* This class builds a kml file that represents paths of fruits
*/
public class Path2kml{
	private ArrayList<Fruit> fruits;

	private final String top = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
			"<kml xmlns=\"http://www.opengis.net/kml/2.2\"\r\n" + 
			"xmlns:gx=\"http://www.google.com/kml/ext/2.2\">\r\n" + 
			"<Folder>\r\n" + 
			"  <Placemark>\r\n" + 
			"    <gx:Track>";

	private final String buttom = "\n" + 
			"    </gx:Track> </Placemark>\n"
			+ "</Folder></kml>";

	/*
	*this function creates kml file
	 */
	public boolean writeKml(String kmlFile) {
		int counter=0;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		String p1 = date1.substring(0,10) + "T";
		String p2 = p1 + date1.substring(11);
	
		
		try {
			PrintWriter pw = new PrintWriter(kmlFile, "UTF-8");

			pw.write(top);

			for(Fruit temp : fruits) {
				String newS = Integer.toString(counter);
				if (newS.length()==1) newS="000" + newS + "Z";
				if (newS.length()==2) newS="00" + newS + "Z";
				if (newS.length()==3) newS="0" + newS +"Z";
				String newS2;
				newS2 = newS;
				newS = newS.substring(0, 2);
				newS2 = newS2.substring(2);
				
				pw.println("<when>"+ p2 + newS +":" + newS2 +"</when>");
				counter++;
			}
			
			
			for(Fruit temp : fruits) {
				GameMetaData g = (GameMetaData)temp.getData();
				Point3D p = (Point3D)temp.getGeom();
			
				pw.println("<gx:coord>"+ p.y() +" " + p.x() +"</gx:coord>");

			}
			
			pw.write(buttom);
			pw.close();
		System.out.println("done");

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	*this function gets list of fruits
	 */
	public void getFruit(ArrayList<Fruit> fruits) {
		this.fruits = fruits;
	}
	
	
}