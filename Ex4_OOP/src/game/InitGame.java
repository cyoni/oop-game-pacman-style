package game;

import java.util.Iterator;
import java.util.List;
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
		Graph gameGraph = new Graph();
		node_data node = new Node(gui_algo.getGameboard().player.getId(), gui_algo.getGameboard().player.getLocation());
		node.setTag(Player.getTag());
		gameGraph.addNode(node);
		gui_algo.getGameboard().getMoveableObjects().add(gui_algo.getGameboard().getPlayer());
		
		for (int i=0; i < gui_algo.getGameboard().fruits.size(); i++) {
			node = new Node(gui_algo.getGameboard().fruits.get(i).getId(), gui_algo.getGameboard().fruits.get(i).getLocation());
			node.setTag(Fruit.getTag());
			gameGraph.addNode(node);
		}
		for (int i=0; i < gui_algo.getGameboard().pacmans.size(); i++) {
			gui_algo.getGameboard().getMoveableObjects().add((MoveableObject) gui_algo.getGameboard().getPacmans().get(i));
			node = new Node(gui_algo.getGameboard().pacmans.get(i).getId(), gui_algo.getGameboard().pacmans.get(i).getLocation());
			node.setTag(Pacman.getTag());
			gameGraph.addNode(node);
		}

		int num_nodes = gameGraph.nodeSize(); 
		
		Iterator<node_data> nodesIterator = gameGraph.getV().iterator();
		while (nodesIterator.hasNext()) {
			node_data currentNode = nodesIterator.next();
			for (int j=currentNode.getKey()+1; j<num_nodes; j++) {
				Point2D p1 = currentNode.getLocation(); 
				Point2D p2 = gameGraph.getNode(j).getLocation();

				gameGraph.connect(currentNode.getKey(), gameGraph.getNode(j).getKey(), Line.distance(p1, p2));	
				gui_algo.gameboard.getLinesOfGameGraph().add(new Line(gui_algo.map.global2pixel(p1), gui_algo.map.global2pixel(p2)));
			}
		}
		
		gui_algo.gameboard.setGameGraph(gameGraph);
		
		//// Prim
		System.out.println("---------");

		Graph primGraph = new Graph();
		for (int i=0; i< gameGraph.nodeSize(); i++) {
			if (gameGraph.getNode(i).getTag().equals(Fruit.getTag())) {
				
				primGraph.addNode(gameGraph.getNode(i));
				System.out.println("adding " + gameGraph.getNode(i).getKey());
				}
		}
		System.out.println("adding.. " + gui_algo.getGameboard().getPlayer().getId());
		primGraph.addNode(new Node( gui_algo.getGameboard().getPlayer().getId(), gui_algo.getGameboard().player.getLocation()));
		
		
		nodesIterator = primGraph.getV().iterator();
		while (nodesIterator.hasNext()) {
			node_data currentNode = nodesIterator.next();
			for (int j=currentNode.getKey()+1; j < gameGraph.nodeSize(); j++) {
				if (primGraph.getNode(j) != null) {
					Point2D p1 = currentNode.getLocation(); 
					Point2D p2 = primGraph.getNode(j).getLocation();
									
					primGraph.connect(currentNode.getKey(), primGraph.getNode(j).getKey(), Line.distance(p1, p2));
				}
			}
		}
				
		double[][] mat = primGraph.getMatrixGraph(gameGraph.nodeSize());

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				System.out.print(mat[i][j] + "\t");
			}
			System.out.println();
		}
		
		Prim prim = new Prim(gameGraph, mat);
		Graph graph_MST = prim.getMST();
		
		// DFS

		DFS dfs = new DFS(gameGraph.nodeSize(), graph_MST);
		dfs.startDFS(gui_algo.gameboard.getPlayer().getId());
		Queue<Integer> path = dfs.getPath();
		
		while(path.size() > 1) {
			Point2D p1 = gameGraph.getNode(path.poll()).getLocation();
			Point2D p2 = gameGraph.getNode(path.peek()).getLocation();
			gui_algo.getGameboard().MST_graph.add(new Line(gui_algo.map.global2pixel(p1), gui_algo.map.global2pixel(p2)));
		}
	}

	public void startGame() {
		DropingItemsOnScreen thread_drop = new DropingItemsOnScreen();
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
		gui_algo.getGameboard().setGameStatus(true);
		
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
		gui_algo.getGameboard().getPacmanThreads().clear();
		for (int i=0; i<gui_algo.getGameboard().getPacmans().size(); i++) {
			Game_object current_pacman = gui_algo.getGameboard().getPacmans().get(i);
			ManagePacmanThread thread = new ManagePacmanThread(gui_algo.getGameboard(), (Pacman)current_pacman);
			gui_algo.getGameboard().addPacmanThread(thread);
			thread.start();
		}
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
					gameboard.addFruit(new Fruit(Game_object.totalObjects++, new Point2D(lon, lat), velocity_or_weight));}
				//else if (type.equals("G"))
				//	gameboard.addGhost(new Ghost(id, new Point2D(lat, lon), Double.parseDouble(data[5])));
				else if (type.equals("P")) {
					double eatingRadius = Double.parseDouble(data[5]);
					gameboard.addPacman(new Pacman(Game_object.totalObjects++, new Point2D(lon, lat), velocity_or_weight, eatingRadius));
				} else if (type.equals("M")) {
					double eatingRadius = Double.parseDouble(data[5]);
					gameboard.setPlayer(new Player(Game_object.totalObjects++, new Point2D(lon, lat), velocity_or_weight, eatingRadius));
				}
			}
			gui_algo.setGameBoard(gameboard);
		}
	}
	
}
