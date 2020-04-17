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
import algorithms.DFS;
import algorithms.Graph;
import algorithms.Node;
import algorithms.Point2D;
import algorithms.Prim;
import algorithms.node_data;

public class GameBoard {
	protected List<game_object> pacmans = new ArrayList<>();
	private List<game_object> ghosts = new ArrayList<>();
	protected List<game_object> fruits = new ArrayList<>();
	protected Player player;
	private Gui_algo gui_algo;
	private boolean game_status;
	protected Graph graph = new Graph();
	protected List<Line> graph_Game = new ArrayList<>();
	public List<Line> MST_graph = new ArrayList<>();
	
	public GameBoard(Gui_algo gui_algo) {
		this.gui_algo = gui_algo;
	}
	

	public List<Line> getGraph_Game() {
		return graph_Game;
	}
	
	public List<Line> getMST_Game() {
		return MST_graph;
	}
	
	public Graph getGraph() {
		return graph;
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


	public void setGameGraph(Graph gameGraph) {
		this.graph = gameGraph;
	}
	


}
