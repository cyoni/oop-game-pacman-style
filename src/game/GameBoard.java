package game;

import java.awt.Container;
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
import GameObjects.Fruit;
import GameObjects.Ghost;
import GameObjects.MoveableObject;
import GameObjects.Pacman;
import GameObjects.Player;
import GameObjects.GameObject;
import algorithms.Graph;
import algorithms.Line;
import algorithms.Node;
import algorithms.Point2D;
import algorithms.node_data;
import mouse.MouseClickOnScreen;
import threads.ManageGhostThread;
import threads.ManagePacmanThread;

public class GameBoard {
	
	private static boolean generateGame = false;
	protected ArrayList<GameObject> pacmans;
	protected ArrayList<GameObject> fruits;
	private ArrayList<Rectangle> blocks;
	protected ArrayList<MoveableObject> moveableObjects;
	protected GameObject ghost;
	protected MoveableObject player;
	private Gui_algo gui_algo;
	protected boolean game_running;
	protected Graph graph;
	protected ArrayList<ManagePacmanThread> managePacmanThread;
	protected ArrayList<ManageGhostThread> manageGhostThread;
	protected boolean autoGame; 
	protected boolean cleanObjectsFromPreviousGame;
	private static String gameStatus = "";

	
	public GameBoard(Gui_algo gui_algo) {
		this.gui_algo = gui_algo;
		initializeDataStructure();
	}
	
	public void initializeDataStructure() {
		pacmans = new ArrayList<>();
		fruits = new ArrayList<>();
		moveableObjects = new ArrayList<>();
		manageGhostThread = new ArrayList<>();
		blocks = new ArrayList<>();
		player = null;
		game_running = false;
		graph = new Graph();
		managePacmanThread = new ArrayList<>();
		autoGame = false;
		cleanObjectsFromPreviousGame = false;
	}

	
	public List<ManagePacmanThread> getPacmanThreads() {
		return managePacmanThread;
	}
	
	public void addPacmanThread(ManagePacmanThread thread) {
		managePacmanThread.add(thread);
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
		GameObject.resetTotalObjects();
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

	public void removeItem(GameObject object_to_remove) {
			if (object_to_remove instanceof Fruit) 
				fruits.remove(object_to_remove);
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
	
	public ArrayList<GameObject> getPacmans() {
		return pacmans;
	}
	
	public GameObject getGhost() {
		return ghost;
	}
	
	public List<GameObject> getFruits() { // synchronized ?!
		return fruits;
	}
	
	public MoveableObject getPlayer() { // synchronized ?!
		return player;
	}

	public void addGhostThread(ManageGhostThread thread) {
		manageGhostThread.add(thread);
	}

	public List<ManageGhostThread> getGhostsThreads() {
		return manageGhostThread;
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
			GameObject current_pacman = getPacmans().get(i);
			ManagePacmanThread thread = new ManagePacmanThread(this, (Pacman)current_pacman);
			addPacmanThread(thread);
			thread.start();
		}		
	}

	
	public void initializeAndStartGhostThread() {
		if (ghost != null) {
			ManageGhostThread thread = new ManageGhostThread(this, (Ghost)ghost);
			addGhostThread(thread);
			thread.start();
		}
	}
	
	
	public void flushIfNeeded() {
		
 		if (isCleanOfOldGameNeeded()) {
			cleanBoard();
 		}
	}

	
	public List<GameObject> addAllObjects() {
		List<GameObject> game_objects = new ArrayList<>();
		
		if (getPlayer()!=null) 
			game_objects.add(getPlayer());
		game_objects.addAll(getPacmans());
		game_objects.add(ghost);
		game_objects.addAll(getFruits());
		
		return game_objects;
	}

	public void setMovableObjects(ArrayList<MoveableObject> moveable) {
		moveableObjects.addAll(moveable);
	}

	public void addBlock(Rectangle rectangle) {
		blocks.add(rectangle);		
	}

	public List<Rectangle> getBlocks() {
		return blocks;
	}

	public boolean isCloseToBlock(MoveableObject object, Point2D local_location) {
		Point2D object_location = Map.global2pixel(object.getLocation());
		
		
		for (int i=0; i<blocks.size(); i++) {
			Rectangle block = blocks.get(i);
			
			for (int j=0; j<4 ; j++) {
				Line line = getLine(block, j);
			
			double dis = Line.distBetweenPointAndLine(object_location.x(), object_location.y(), line.getP1().x(), line.getP1().y(), line.getP2().x(), line.getP2().y());
						
			if (dis <= 1 && betweenTheLine(object_location, line)){
				return true;
			}
				 
			}
			
		}
		
		return false;
	}

	private Line getLine(Rectangle block, int i) {

		switch (i) {
			case 0:
				return new Line(Map.global2pixel(block.getP_up_left()), Map.global2pixel(block.getP_down_left()));
			case 1:
				return new Line(Map.global2pixel(block.getP_up_left()), Map.global2pixel(block.getP_up_right()));
			case 2:
				return new Line(Map.global2pixel(block.getP_up_right()), Map.global2pixel(block.getP_down_right()));
			case 3:
				return  new Line(Map.global2pixel(block.getP_down_left()), Map.global2pixel(block.getP_down_right()));
		}
		return null;
	}

	private boolean betweenTheLine(Point2D object_location, Line line) {
		
		if (line.getP1().x() == line.getP2().x()) {
			double max = Math.max(line.getP1().y(), line.getP2().y());
			double min = Math.min(line.getP2().y(), line.getP1().y());
			return (object_location.y() > min && object_location.y() < max); 
		}
		else {
			double max = Math.max(line.getP1().x(), line.getP2().x());
			double min = Math.min(line.getP2().x(), line.getP1().x());
			return (object_location.x() > min && object_location.x() < max); 
		}
	}

	public void addGhost(Ghost ghost) {
		this.ghost = ghost;
	}

	public static void setGenerateGame(boolean state) {
		generateGame  = state;
	}

	public static boolean isGenerateGame() {
		return generateGame;
	}

	public void updateLabelStatus() {
		gameStatus = "Score: " + player.getScore() + ", Fruits eaten: " + player.getNumEatenFruits() + ", Pacmans eaten: " + player.getNumEatenPacmans() + ", Hit times: " + player.getNumHitsByGhosts();
	}
	
	public String getGameStatus() {
		return gameStatus;
	}
	

}
