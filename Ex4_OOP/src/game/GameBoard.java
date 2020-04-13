package game;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import javax.imageio.ImageIO;

import Coords.MyCoords;
import GIS.Map;
import GUI.Gui_dialog;
import GameObjects.Rectangle;
import GameObjects.Fruit;
import GameObjects.Ghost;
import GameObjects.Pacman;
import GameObjects.Player;
import GameObjects.game_object;
import Geom.Point2D;

public class GameBoard {
	private List<Rectangle> rectangles = new ArrayList<>();
	private List<game_object> pacmans = new ArrayList<>();
	private List<game_object> ghosts = new ArrayList<>();
	private Player player;
	private Map map;
	private PriorityQueue<game_object> closestFruits = new PriorityQueue<>(5, new FruitComperator(this));
	private boolean game_status;



	public GameBoard(Map map, List<String> elements, Player player) {
		this.player = player;
		this.map = map;
		
		updateGameObjects(elements);
		
	}
	

	public void setGame_status(boolean game_status) {
		this.game_status = game_status;
	}


	
	public Map getMap() {
		return map;
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
				closestFruits.add(new Fruit(this, map, id, new Point2D(lon, lat), Double.parseDouble(data[5])));}
			else if (type.equals("G"))
				ghosts.add(new Ghost(this, map, id, new Point2D(lon, lat), Double.parseDouble(data[5])));
			//else if (type.equals("B"))
		//		rectangles.add(new Rectangle(data));
			else if (type.equals("P"))
				pacmans.add(new Pacman(id, map, new Point2D(lon, lat), Double.parseDouble(data[6])));
		//	else if (type.equals("M"))
		//		player = new Player(id, map.global2pixel(new Point3D(lat, lon)), Double.parseDouble(data[6]));				
		}


	}


	
	//public List<Rectangle> getRectangles() {return rectangles;}
	public synchronized PriorityQueue<game_object> getFruits() {return closestFruits;}
	public List<game_object> getPacmans() {return pacmans;}
	public List<game_object> getGhosts() {return ghosts;}
	public synchronized Player getPlayer() { return player;}




	public boolean isRunning() {
		return game_status;
	}
	


}
