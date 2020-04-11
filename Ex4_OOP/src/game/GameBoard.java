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
import GameObjects.Rectangle;
import GameObjects.Fruit;
import GameObjects.Ghost;
import GameObjects.Pacman;
import GameObjects.Player;
import GameObjects.game_object;
import Geom.Point3D;

public class GameBoard {
	List<Rectangle> rectangles = new ArrayList<>();
	List<game_object> fruits = new ArrayList<>();
	List<game_object> pacmans = new ArrayList<>();
	List<game_object> ghosts = new ArrayList<>();
	Player player;
	private String game_scenario;
	
	private Map map;


	public GameBoard(Map map, List<String> elements, String game_scenario, Player player) {
		this.player = player;
		this.map = map;
		updateGameObjects(elements);
	}
	
	public void updateGameObjects(List<String> elements) {
		
/*		fruits.clear();
		pacmans.clear();
		ghosts.clear();
		fruits.clear();
		rectangles.clear();*/
		for (int i=0; i< elements.size(); i++) {

			String current_element = elements.get(i);
			String data[] = current_element.split(",");
			String type = data[0];
			int id =  Integer.parseInt(data[1]);
			double lat = Double.parseDouble(data[2]);
			double lon =  Double.parseDouble(data[3]);
			System.out.println(elements.get(i));
			if (type.equals("F")) {
				fruits.add(new Fruit(this, map, id, new Point3D(lat, lon), Double.parseDouble(data[5])));}
			else if (type.equals("G"))
				ghosts.add(new Ghost(this, map, id, new Point3D(lat, lon), Double.parseDouble(data[5])));
			//else if (type.equals("B"))
		//		rectangles.add(new Rectangle(data));
			else if (type.equals("P"))
				pacmans.add(new Pacman(id, new Point3D(lat, lon), Double.parseDouble(data[6])));
			else if (type.equals("M"))
				player = new Player(id, new Point3D(lat, lon), Double.parseDouble(data[6]));				
		}


	}

	
	//public List<Rectangle> getRectangles() {return rectangles;}
	public List<game_object> getFruits() {return fruits;}
	public List<game_object> getPacmans() {return pacmans;}
	public List<game_object> getGhosts() {return ghosts;}
	public Player getPlayer() { return player;}
	


}
