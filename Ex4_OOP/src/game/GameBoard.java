package game;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.imageio.ImageIO;

import Coords.MyCoords;
import GIS.Map;
import GUI.Gui_algo;
import GUI.Gui_dialog;
import GUI.MouseClickOnScreen;
import GameObjects.Rectangle;
import GameObjects.Fruit;
import GameObjects.Ghost;
import GameObjects.Line;
import GameObjects.Pacman;
import GameObjects.Player;
import GameObjects.game_object;
import Geom.Point2D;
import algorithms.DFS;
import algorithms.Graph;
import algorithms.Node;
import algorithms.Prim;
import algorithms.node_data;

public class GameBoard {
	private List<Rectangle> rectangles = new ArrayList<>();
	private List<game_object> pacmans = new ArrayList<>();
	private List<game_object> ghosts = new ArrayList<>();
	private List<game_object> fruits = new ArrayList<>();
	private Player player;
	private Gui_algo gui_algo;
	private boolean game_status;


	public GameBoard(Gui_algo gui_algo) {
		this.gui_algo = gui_algo;
	}
	

	public void startGame() {
		DropingItemsOnScreen drop = new DropingItemsOnScreen();
		if (fruits.size() == 0) {
			if (player == null && fruits.size() != 0) DropingItemsOnScreen.dropping_player = true;
			else if (player != null && fruits.size() == 0) DropingItemsOnScreen.dropping_apples = true;
			else if (player == null && fruits.size() == 0) drop.selectToDropAll();
			
			drop.start();
			return;
		}
		else if (player == null) {
			DropingItemsOnScreen.dropping_player = true;
			drop.startDroppingItems();
			return;
		}
		
		buildGraph();
		
		
		System.out.println("START");
		this.game_status = true;
		/*ObjectMovement gameManager = new ObjectMovement(gui_algo);*/
		
		
		
		
	}
	
	private void buildGraph() {
		Graph graph = new Graph();
		
		graph.addNode(new Node(player.getId(), player.getLocation())); // player's ID is -1
		for (int i=0; i< fruits.size(); i++) {
			node_data node = new Node(fruits.get(i).getId(), fruits.get(i).getLocation()); 
			graph.addNode(node);
		}
		for (int i=0; i< pacmans.size(); i++) {
			node_data node = new Node(pacmans.get(i).getId(), pacmans.get(i).getLocation()); 
			graph.addNode(node);
		}
		
		
		int num_nodes = graph.nodeSize(); 
		
		Iterator<node_data> nodesIterator_1 = graph.getV().iterator();
		
		while (nodesIterator_1.hasNext()) {
			node_data currentNode = nodesIterator_1.next();
			for (int j=currentNode.getKey()+1; j<num_nodes; j++) {
				Point2D p1 = currentNode.getLocation(); 
				Point2D p2 = graph.getNode(j).getLocation();
				graph.connect(currentNode.getKey(), graph.getNode(j).getKey(), Line.distance(p1, p2));			
				gui_algo.drawLine(gui_algo.map.global2pixel(p1), gui_algo.map.global2pixel(p2));
			}
			gui_algo.repaint();
		}
		
		
		System.out.println(graph.edgeSize());
		System.out.println(graph.nodeSize());
		
		double[][] mat = graph.getMatrixGraph();
		
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				System.out.print(mat[i][j] + "\t");
			}
			System.out.println();
		}
		
		
		Prim prim = new Prim(graph, mat);
		Graph graph_MST = prim.getMST();
		
		DFS dfs = new DFS(graph_MST);
		dfs.startDFS(player.getId());
		
	}


	public void stoptGame() {
		this.game_status = false;
	}
	
	public void setGameStatus(boolean game_status) {
		this.game_status = game_status;
	}

	
	public Map getMap() {
		return gui_algo.map;
	}
	


	public List<game_object> getPacmans() {return pacmans;}
	public List<game_object> getGhosts() {return ghosts;}
	public synchronized List<game_object> getFruits() {return fruits;}
	public synchronized Player getPlayer() { return player;}

	public boolean isRunning() {
		return game_status;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}


	public void addFruit(Fruit fruit) {
		fruits.add(fruit);
	}

	public void addGhost(Ghost ghost) {
		ghosts.add(ghost);
	}

	public void addPacman(Pacman pacman) {
		pacmans.add(pacman);		
	}


	public void cleanBoard() {
		game_object.resetTotalObjects();
		fruits.clear();
		ghosts.clear();
		pacmans.clear();
		player = null;
		gui_algo.removeAll();
		gui_algo.revalidate();
		gui_algo.repaint();
		
	}
	


}
