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

	private String csvFile;

	public CSVReader(String file_name) {
		this.csvFile = file_name;
	}
	



	public List<String> processFile() {
		String line = "";
		List<String> elements = new ArrayList<>();
		 try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Yoni\\git\\oop_game_1\\Ex4_OOP\\data\\Ex4_OOP_example9.csv"))) {
			 br.readLine();
	            while (( line = br.readLine()) != null) {
	            	elements.add(line);
	            	System.out.println(line);
	            	}
	            	
	            }
		 catch(Exception e) {System.out.println(e.getMessage());}
		 return elements;
	}


	

	
	
}
