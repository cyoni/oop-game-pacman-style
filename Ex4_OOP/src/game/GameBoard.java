package game;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import GIS.Map;
import GUI.Gui_dialog;
import GameObjects.Block;
import GameObjects.Fruit;
import GameObjects.Ghost;
import GameObjects.Pacman;
import GameObjects.game_object;

public class GameBoard {
	List<Block> blocks = new ArrayList<>();
	List<game_object> fruits = new ArrayList<>();
	List<game_object> pacmans = new ArrayList<>();
	List<game_object> ghosts = new ArrayList<>();

	private Map map;
	public List<Block> getBlocks() {return blocks;}
	public List<game_object> getFruits() {return fruits;}
	public List<game_object> getPacmans() {return pacmans;}
	public List<game_object> getGhosts() {return ghosts;}

	public GameBoard(Map map, List<String> elements) {
		this.map = map;
		extractsElements(elements);
	}
	private void extractsElements(List<String> elements) {

		for (int i=0; i< elements.size(); i++) {

			String current_element = elements.get(i);
			String data[] = current_element.split(",");
			String type = data[0];
			int id =  Integer.parseInt(data[1]);
			double lat = Double.parseDouble(data[2]);
			double lon =  Double.parseDouble(data[3]);
			
			if (type.equals("F"))
				fruits.add(new Fruit(this, map, id, lat, lon, Integer.parseInt(data[5])));
			else if (type.equals("G"))
				ghosts.add(new Ghost(this, map, id, lat, lon, Integer.parseInt(data[5])));
			else if (type.equals("B"))
				blocks.add(new Block(this, map, id, lat, lon, Double.parseDouble(data[5]), Double.parseDouble(data[6])));
			else if (type.equals("P"))
				pacmans.add(new Pacman(this,map, id, lat, lon, Integer.parseInt(data[6])));
				
		}

		System.out.println("|FINISHED LOADING ITEMS|");

	}



}
