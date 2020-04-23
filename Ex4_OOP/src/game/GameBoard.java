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
import mouse.MouseClickOnScreen;

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
	protected boolean cleanObjectsFromPreviousGame;
	
	public GameBoard(Gui_algo gui_algo) {
		this.gui_algo = gui_algo;
		initializeDataStructure();
	}
	
	public void initializeDataStructure() {
		pacmans = new ArrayList<>();
		ghosts = new ArrayList<>();
		fruits = new ArrayList<>();
		moveableObjects = new ArrayList<>();
		manageGhostThread = new ArrayList<>();
		player = null;
		game_running = false;
		graph = new Graph();
		graph_Game = new ArrayList<>();
		MST_graph = new ArrayList<>();
		managePacmanThread = new ArrayList<>();
		autoGame = false;
		cleanObjectsFromPreviousGame = false;
	}

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
		String result = "";
		for (int i = 0; i < moveableObjects.size(); i++) {
			MoveableObject object = moveableObjects.get(i);
			result+= object.getId() + " ate " + object.getNumEatenFruits() + " fruits.\n";
		}
		return result;
	}
	
	public void cleanBoard() {
		initializeDataStructure();
		Game_object.resetTotalObjects();
		DropingItemsOnScreen.selectNone();
		getGuiAlgo().repaint();
	}
	

	
	public boolean isCleanOfOldGameNeeded() {
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
			if (object_to_remove instanceof Fruit) 
				fruits.remove(object_to_remove);
			 else if (object_to_remove instanceof Ghost) 
				ghosts.remove(object_to_remove);
			 else if (object_to_remove instanceof Pacman) 
					pacmans.remove(object_to_remove);
			 else if (object_to_remove instanceof Player) 
					player = null;
			getGuiAlgo().repaint();
		}
	
	public void alterSpeed(MoveableObject object) {
		String str = Gui_dialog.getInputDialog("Enter a new velocity...", object.getVelocity()+"");
		try {
			double newVelocity = Double.valueOf(str);
			object.setVelocity(newVelocity);
			System.out.println("OK");
		}
		catch(Exception e) {
			System.out.println(str + " is not a number");
		}
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

	public boolean isAnimationOnProgress() {
		return isRunning() && gui_algo.getGameboard().getGraph().nodeSize()==0;
	}

	public void alterWeight(Fruit object) {
		String str = Gui_dialog.getInputDialog("Enter a new velocity...", object.getWeight() +"");
		try {
			double newWeight = Double.valueOf(str);
			object.setWeight(newWeight);
			System.out.println("OK");
		}
		catch(Exception e) {
			System.out.println(str + " is not a number");
		}
		
	}

	public void alterEatingRadius(MoveableObject object) {

		String str = Gui_dialog.getInputDialog("Enter a new eating radius...", object.getEatingRadius()/10 +"");
		try {
			double newRadius = Double.valueOf(str);
			object.setEatingRadius(newRadius);
			System.out.println("OK");
		}
		catch(Exception e) {
			System.out.println(str + " is not a number");
		}
		
	}
	public void initializeAndStartPacmansThreads() {
		for (int i=0; i<getPacmans().size(); i++) {
			Game_object current_pacman = getPacmans().get(i);
			ManagePacmanThread thread = new ManagePacmanThread(this, (Pacman)current_pacman);
			addPacmanThread(thread);
			thread.start();
		}		
	}


	public void initializeAndStartGhosts() {
		for (int i=0; i<getGhosts().size(); i++) {
			Game_object current_ghost = getGhosts().get(i);
			ManageGhostThread thread = new ManageGhostThread(this, (Ghost)current_ghost);
			addGhostThread(thread);
			thread.start();
		}		
	}
	
	public void flushIfNeeded() {
		if (isCleanOfOldGameNeeded() || 
				isAnimationOnProgress() /*|| getGraph().nodeSize() > 0*/) 
			cleanBoard();		
	}

	
	public List<Game_object> addAllObjects() {
		List<Game_object> game_objects = new ArrayList<>();
		
		if (getPlayer()!=null) game_objects.add(getPlayer());
		game_objects.addAll(getPacmans());
		game_objects.addAll(getGhosts());
		game_objects.addAll(getFruits());
		
		return game_objects;
	}
}
