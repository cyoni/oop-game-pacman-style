package game;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

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
	private List<game_object> fruits = new ArrayList<>();
	private boolean game_status;



	public GameBoard(Map map) {
		this.map = map;
		//updateGameObjects(elements);
	}
	

	public void setGameStatus(boolean game_status) {
		this.game_status = game_status;
	}

	
	public Map getMap() {
		return map;
	}
	
	public void updateGameObjects(List<String> elements) {
		for (int i=0; i< elements.size(); i++) {
			String current_element = elements.get(i);
			String data[] = current_element.split(",");
			String type = data[0];
			int id =  Integer.parseInt(data[1]);
			double lat = Double.parseDouble(data[2]);
			double lon =  Double.parseDouble(data[3]);
			System.out.println(elements.get(i));
			if (type.equals("F")) {
				fruits.add(new Fruit(id, map.global2pixel(new Point2D(lon, lat)), Double.parseDouble(data[5])));}
			else if (type.equals("G"))
				ghosts.add(new Ghost(id, map.global2pixel(new Point2D(lon, lat)), Double.parseDouble(data[5])));
			else if (type.equals("P"))
				pacmans.add(new Pacman(id, map.global2pixel(new Point2D(lon, lat)), Double.parseDouble(data[6])));			
		}
	}

	public synchronized List<game_object> getFruits() {return fruits;}
	public List<game_object> getPacmans() {return pacmans;}
	public List<game_object> getGhosts() {return ghosts;}
	public synchronized Player getPlayer() { return player;}

	public boolean isRunning() {
		return game_status;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	


}
