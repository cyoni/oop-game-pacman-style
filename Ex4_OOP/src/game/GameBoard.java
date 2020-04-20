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
import Threads_Game.ManageGhostThread;
import Threads_Game.ManagePacmanThread;
import GameObjects.Fruit;
import GameObjects.Ghost;
import GameObjects.MoveableObject;
import GameObjects.Pacman;
import GameObjects.Player;
import GameObjects.Game_object;
import algorithms.DFS;
import algorithms.Graph;
import algorithms.Line;
import algorithms.Node;
import algorithms.Point2D;
import algorithms.Prim;
import algorithms.node_data;

public class GameBoard {
	protected List<Game_object> pacmans;
	protected List<Game_object> ghosts;
	protected List<Game_object> fruits;
	protected List<MoveableObject> moveableObjects;
	protected MoveableObject player;
	private Gui_algo gui_algo;
	protected boolean game_running;
	protected Graph graph;
	protected List<Line> graph_Game;
	protected List<Line> MST_graph;
	protected List<ManagePacmanThread> managePacmanThread;
	protected List<ManageGhostThread> manageGhostThread;
	protected boolean autoGame; 
	private GameBoard_algo gameboard_algo = new GameBoard_algo(this);
	protected boolean cleanObjectsFromPreviousGame;
	
	public GameBoard(Gui_algo gui_algo) {
		this.gui_algo = gui_algo;
		gameboard_algo.initializeDataStructure();
	}
	
	public GameBoard() {}

	public List<Line> getLinesOfGameGraph() {
		return graph_Game;
	}
	
	public List<ManagePacmanThread> getPacmanThreads() {
		return managePacmanThread;
	}
	
	public void addPacmanThread(ManagePacmanThread thread) {
		managePacmanThread.add(thread);
	}
	
	public List<Line> getMST_Game() {
		return MST_graph;
	}
	
	public Graph getGraph() {
		return graph;
	}
	
	public void addMoveableObject(MoveableObject obj) {
		moveableObjects.add(obj);
	}
	
	public Gui_algo getGuiAlgo() {
		return gui_algo;
	}

	public String getGameStat() {
		return gameboard_algo.getGameStat();
	}
	
	public void cleanBoard() {
		gameboard_algo.cleanBoard();
	}
	
	public boolean doesCleanNeeded() {
		return cleanObjectsFromPreviousGame;
	}
	
	public Map getMap() {
		return gui_algo.map;
	}
	
	public boolean isRunning() {
		return game_running;
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


	public void setGameGraph(Graph gameGraph) {
		this.graph = gameGraph;
	}
	
	public boolean isAutoGame() {
		return this.autoGame;
	}
	
	public void setIsAutoGame(boolean answer) {
		this.autoGame = answer;
	}

	public List<MoveableObject> getMoveableObjects() {
		return moveableObjects;
	}

	public void stopGame() {
		cleanObjectsFromPreviousGame = true;
		game_running = false;
	}
	
	public void startGame() {
		game_running = true;
	}

	public void removeItem(Game_object object_to_remove) {
		gameboard_algo.removeItem(object_to_remove);
	}

	public GameBoard_algo getGameAlgo() {
		return gameboard_algo;
	}
	
	public List<Game_object> getPacmans() {return pacmans;}
	public List<Game_object> getGhosts() {return ghosts;}
	public synchronized List<Game_object> getFruits() {return fruits;}
	public synchronized MoveableObject getPlayer() { return player;}

	public void addGhostThread(ManageGhostThread thread) {
		manageGhostThread.add(thread);
	}

	public List<ManageGhostThread> getGhostsThreads() {
		return manageGhostThread;
	}

}
