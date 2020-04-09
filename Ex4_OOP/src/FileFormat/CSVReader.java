package FileFormat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import GIS.Map;
import GUI.Gui_algo;
import GameObjects.game_object;
import game.GameBoard;

public class CSVReader {
	private Gui_algo gui_algo;
	private String csvFile;
	private List<game_object> gameObjects = new ArrayList<>();
	private Map map;
	
	public CSVReader(Gui_algo gui_algo, String file_name) {
		this.csvFile = file_name;
		this.gui_algo = gui_algo;
		map = gui_algo.map;
	}
	

	
/*	public game_object parseLine(String str) {
         String[] line = str.split(",");
         System.out.println("Country [code= " + line[4] + " , name=" + line[5] + "]");
         return null;
	}*/
	
	public void processFile() {
		String line = "";
		List<String> elements = new ArrayList<>();
		 try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			 br.readLine();
	            while (( line = br.readLine()) != null) {
	            	elements.add(line);
	            	System.out.println(line);
	            	}
	            	gui_algo.setGameBoard(new GameBoard(map, elements));
	            }
		 catch(Exception e) {System.out.println(e.getMessage());}
	}


	

	
	
}
