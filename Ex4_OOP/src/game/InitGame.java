package game;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;

import GUI.Gui_algo;
import GUI.Gui_dialog;
import GameObjects.Fruit;
import GameObjects.Ghost;
import GameObjects.MoveableObject;
import GameObjects.Pacman;
import GameObjects.Player;
import Threads_Game.Eat_Thread;
import Threads_Game.ManageGhostThread;
import Threads_Game.ManagePacmanThread;
import Threads_Game.MovementThread;
import GameObjects.Game_object;
import algorithms.DFS;
import algorithms.Graph;
import algorithms.Line;
import algorithms.Node;
import algorithms.Point2D;
import algorithms.Prim;
import algorithms.node_data;

public class InitGame{


	private Gui_algo gui_algo;
	
	public InitGame(Gui_algo gui_algo) {
		this.gui_algo = gui_algo;
	}
	
	
	public void buildGraphGame() {
		if (gui_algo.getGameboard().getPlayer() == null) return;
		
		Graph gameGraph = new Graph();
		addNodesToGameGraph(gameGraph);
		connectEdges(gameGraph);
		gui_algo.gameboard.setGameGraph(gameGraph);
		
		// Prim
		Graph primGraph = new Graph();
		addFruitsToPrimGraph(gameGraph, primGraph);
		connectEdges(primGraph);
		double[][] mat = primGraph.getMatrixGraph(gameGraph.nodeSize());
		printDistanceMatrix(mat);
		
		Prim prim = new Prim(gameGraph, mat);
		Graph graph_MST = prim.getMST();
		
		// DFS
		getPathByDFS(graph_MST, gameGraph);
	}

	private void printDistanceMatrix(double[][] mat) {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				System.out.print(mat[i][j] + "\t");
			}
			System.out.println();
		}		
	}


	private void getPathByDFS(Graph graph_MST, Graph gameGraph) {
		DFS dfs = new DFS(graph_MST);
		dfs.startDFS(gui_algo.gameboard.getPlayer().getId());
		Queue<Integer> path = dfs.getPath();
		
		while(path.size() > 1) {
			Point2D p1 = gameGraph.getNode(path.poll()).getLocation();
			Point2D p2 = gameGraph.getNode(path.peek()).getLocation();
			gui_algo.getGameboard().MST_graph.add(new Line(gui_algo.map.global2pixel(p1), gui_algo.map.global2pixel(p2)));
		}		
	}


	private void addFruitsToPrimGraph(Graph gameGraph, Graph primGraph) {
		 for (Entry<Integer, node_data> nodes : gameGraph.getGraph().entrySet()) {
			 node_data current_node = nodes.getValue();
				if (current_node.getTag().equals(Fruit.getTag())) 
					primGraph.addNode(current_node);
		 }
		primGraph.addNode(new Node( gui_algo.getGameboard().getPlayer().getId(), gui_algo.getGameboard().player.getLocation()));	
	}

	private void connectEdges(Graph graph) {
		List<node_data> nodes = graph.getNodes();
		for (int i=0; i<nodes.size(); i++) {
			node_data currentNode = nodes.get(i);
			for (int j = (i+1); j < nodes.size()-1; j++) {
				node_data j_node = nodes.get(j);
				
				Point2D p1 = currentNode.getLocation(); 
				Point2D p2 = j_node.getLocation();

				graph.connect(currentNode.getKey(), j_node.getKey(), Line.distance(p1, p2));	
				gui_algo.gameboard.getLinesOfGameGraph().add(new Line(gui_algo.map.global2pixel(p1), gui_algo.map.global2pixel(p2)));
			}
		}
	}


	private void addNodesToGameGraph(Graph gameGraph) {
		node_data node = new Node(gui_algo.getGameboard().player.getId(), gui_algo.getGameboard().player.getLocation());
		node.setTag(Player.getTag());
		gameGraph.addNode(node);
		gui_algo.getGameboard().getMoveableObjects().add(gui_algo.getGameboard().getPlayer());
		
		for (int i=0; i < gui_algo.getGameboard().fruits.size(); i++) {
			node = new Node(gui_algo.getGameboard().fruits.get(i).getId(), gui_algo.getGameboard().fruits.get(i).getLocation());
			node.setTag(Fruit.getTag());
			System.out.println("adding node " + node.getKey());
			gameGraph.addNode(node);
		}
		for (int i=0; i < gui_algo.getGameboard().pacmans.size(); i++) {
			gui_algo.getGameboard().getMoveableObjects().add((MoveableObject) gui_algo.getGameboard().getPacmans().get(i));
			node = new Node(gui_algo.getGameboard().pacmans.get(i).getId(), gui_algo.getGameboard().pacmans.get(i).getLocation());
			node.setTag(Pacman.getTag());
			gameGraph.addNode(node);
			System.out.println("adding node " + node.getKey());
		}		
	}


	public void startGame() {
		DropingItemsOnScreen thread_drop = new DropingItemsOnScreen();
		if (gui_algo.getGameboard().doesCleanNeeded()) gui_algo.getGameboard().cleanBoard();
		
		if (gui_algo.getGameboard().getFruits().size() == 0) {
			thread_drop.selectToDropAll();
			thread_drop.startThreadDroppingItems();
			return;
		} else if (gui_algo.getGameboard().getPlayer() == null) {
			DropingItemsOnScreen.global_dropping_player = true;
			thread_drop.startThreadDroppingItems();
			return;
		} else if (gui_algo.getGameboard().getGraph().nodeSize() == 0) {
			buildGraphGame();
		}
		
		System.out.println("\nGAME STARTED");
		gui_algo.getGameboard().startGame();;
		
		/////////////////
		
		
		Eat_Thread eat_thread = new Eat_Thread(gui_algo.getGameboard()); // the thread that displaying the fruits on the screen
		eat_thread.start();
		
		startMenualVersion();
	}
	
	private void startMenualVersion() {
		MovementThread movementThread = new MovementThread(gui_algo.getGameboard(), gui_algo.getGameboard().getPlayer());
		movementThread.start();
		initializeAndStartPacmansThreads();
		//init ghosts
	}

	private void initializeAndStartPacmansThreads() {
		gui_algo.getGameboard().getGameAlgo().initializeAndStartPacmansThreads();
		gui_algo.getGameboard().getPacmanThreads().clear();

	}

	public void initGameboard(List<String> elements) {
		if (!elements.isEmpty()) {
	        gui_algo.getGameboard().cleanBoard();
			GameBoard gameboard = new GameBoard(gui_algo);
			for (int i=0; i< elements.size(); i++) {
				String current_element = elements.get(i);
				String data[] = current_element.split(",");
				String type = data[0];
				double lat = Double.parseDouble(data[2]);
				double lon =  Double.parseDouble(data[3]);
				double velocity_or_weight = Double.parseDouble(data[4]);
				System.out.println(elements.get(i));
				
				if (type.equals("F")) {
					gameboard.addFruit(new Fruit(Game_object.GLOBAL_ID++, new Point2D(lon, lat), velocity_or_weight));}
				//else if (type.equals("G"))
				//	gameboard.addGhost(new Ghost(id, new Point2D(lat, lon), Double.parseDouble(data[5])));
				else if (type.equals("P")) {
					double eatingRadius = Double.parseDouble(data[5]);
					gameboard.addPacman(new Pacman(Game_object.GLOBAL_ID++, new Point2D(lon, lat), velocity_or_weight, eatingRadius));
				} else if (type.equals("M")) {
					double eatingRadius = Double.parseDouble(data[5]);
					gameboard.setPlayer(new Player(Game_object.GLOBAL_ID++, new Point2D(lon, lat), velocity_or_weight, eatingRadius));
				}
			}
			gui_algo.setGameBoard(gameboard);
		}
	}
	
}
