package FileFormat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import GIS.Map;
import GUI.Gui_algo;
import GameObjects.game_object;
import game.GameBoard;

public class CSVReaderAndWriter {

	private String csvFile;

	public CSVReaderAndWriter(String file_name) {
		this.csvFile = file_name;
	}
	



	public CSVReaderAndWriter() {
		// TODO Auto-generated constructor stub
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

	
	
	public String chooseFolder() {
		// parent component of the dialog
		JFrame parentFrame = new JFrame();
		JFileChooser fileChooser = new JFileChooser();
		String file_name = "";
		fileChooser.setDialogTitle("Specify a file to save");   
		 
		int userSelection = fileChooser.showSaveDialog(parentFrame);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
		    file_name = fileToSave.getAbsolutePath();
		    if (!file_name.endsWith(".csv")) file_name+=".csv";
		}
		return file_name;
	}

	public void writeToCSV(GameBoard gameboard, String file_name) throws IOException {
		FileWriter writer  = new FileWriter(file_name);
		
		List<game_object> fruits = gameboard.getFruits();
		List<game_object> pacmans = gameboard.getPacmans();

		int fruits_num = fruits.size();
		int pacmans_num = pacmans.size();
		
		writer.write("Type, ID, Lat, Lon, Alt, Speed/Weight, Radius [Fruits: "+ fruits_num +", Pacmans: "+ pacmans_num +"]\n");
		
		Iterator<game_object> iter = fruits.iterator();
		game_object curr;
		
		while(iter.hasNext()) {
			curr = iter.next(); 
			writer.write(curr.toString() + "\n");
		}
		
		iter = pacmans.iterator();
		while(iter.hasNext()) {
			curr = iter.next(); 
			writer.write(curr.toString() + "\n");
		}
		
		writer.write(gameboard.getPlayer().toString()+"\n");
		writer.close();
		System.out.println("OK");
	}

	
}
