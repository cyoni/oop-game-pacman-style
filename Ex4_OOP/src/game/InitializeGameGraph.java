package game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Map.Entry;

import GameObjects.Fruit;
import GameObjects.Game_object;
import GameObjects.Ghost;
import GameObjects.MoveableObject;
import GameObjects.Pacman;
import GameObjects.Player;
import algorithms.DFS;
import algorithms.Dijkstra;
import algorithms.Graph;
import algorithms.Line;
import algorithms.Node;
import algorithms.Point2D;
import algorithms.Prim;
import algorithms.edge_data;
import algorithms.node_data;

public class InitializeGameGraph {
	
	private GameBoard gameboard;

	public InitializeGameGraph(GameBoard gameboard) {
		this.gameboard = gameboard;
	}
	
	public void buildGraphGame() {
		if (gameboard.getPlayer() == null) return;
		
		gameboard.MST_graph.clear();
		gameboard.graph_Game.clear();
		Graph gameGraph = new Graph();
		addNodesToGameGraph(gameGraph);
		connectEdges(gameGraph);
		gameboard.setGameGraph(gameGraph);
		
		// Prim
		Graph primGraph = new Graph();
		addPlayerToPrimGraph(gameGraph, primGraph);
		addFruitsToPrimGraph(gameGraph, primGraph);
		connectEdges(primGraph);
		double[][] mat = primGraph.getMatrixGraph(/*gameGraph.nodeSize()*/);
		//printDistanceMatrix(mat);
		Prim prim = new Prim(gameGraph, mat);
		List<edge_data> graph_MST = prim.getMST();
		
		Queue<Integer> shortestEatingPath = createShortestEatingPath(graph_MST);
		sendBestPathToGameboard(shortestEatingPath, gameGraph);
		
		/*
		
		double[][] mst_graph =  graph_MST.getMatrixGraph();
		Dijkstra dijkstra = new Dijkstra(mst_graph);
		dijkstra.startDijkstra(gameboard.getPlayer().getId());
		
		double[] distanceArray = dijkstra.getDistances();
		Queue<Integer> shortestEatingPath = createShortestEatingPath(distanceArray);
		
		*/

	}

	private void sendBestPathToGameboard(Queue<Integer> path, Graph gameGraph) {
		while(path.size() > 1) {
			Point2D p1 = gameGraph.getNode(path.poll()).getLocation();
			Point2D p2 = gameGraph.getNode(path.peek()).getLocation();
			gameboard.MST_graph.add(new Line(gameboard.getMap().global2pixel(p1), gameboard.getMap().global2pixel(p2)));
		}
	}

	private Queue<Integer> createShortestEatingPath(List<edge_data> edges_of_MST) {
		int startingPoint = gameboard.getPlayer().getId();
		Queue<Integer> path = new LinkedList<>();
		path.add(startingPoint);
		
		for (int i = edges_of_MST.size()-1; i >= 0; i--) {
			edge_data current_edge = edges_of_MST.get(i);
			
			if (path.contains(current_edge.getSrc())) {
				path.add(current_edge.getDest());
				System.out.print(current_edge.getDest()  + "->");
			}
			else {
				path.add(current_edge.getSrc());
				System.out.print(current_edge.getSrc()  + "->");
				}

		}
		System.out.println();
		return path;
	}

	private void addPlayerToPrimGraph(Graph gameGraph, Graph primGraph) {
		 int id = gameboard.getPlayer().getId();
	     Node node = new Node(id, gameboard.player.getLocation());
	     primGraph.addNode(node);		
	}

	private void printDistanceMatrix(double[][] mat) {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				System.out.print(mat[i][j] + "\t");
			}
			System.out.println();
		}		
	}

/*	private void getPathByDFS(Graph graph_MST, Graph gameGraph) {
		System.out.println("MST PATH: " );
		
		DFS dfs = new DFS(graph_MST);
		int firstNodeToStartFrom = gameboard.getPlayer().getId();
		dfs.startDFS(firstNodeToStartFrom);
		Queue<Integer> path = dfs.getPath();
		while(path.size() > 1) {
			Point2D p1 = gameGraph.getNode(path.poll()).getLocation();
			Point2D p2 = gameGraph.getNode(path.peek()).getLocation();
			gameboard.MST_graph.add(new Line(gameboard.getMap().global2pixel(p1), gameboard.getMap().global2pixel(p2)));
		}		
	}*/

	private void addFruitsToPrimGraph(Graph gameGraph, Graph primGraph) {
		 for (Entry<Integer, node_data> nodes : gameGraph.getGraph().entrySet()) {
			 node_data current_node = nodes.getValue();
				if (current_node.getTag().equals(Fruit.getTag())) 
					primGraph.addNode(current_node);
		 }
	}

	private void connectEdges(Graph graph) {
		List<node_data> nodes = graph.getNodes();
		for (int i=0; i<nodes.size(); i++) {
			node_data currentNode = nodes.get(i);
			for (int j =i+1; j < nodes.size(); j++) {
				node_data j_node = nodes.get(j);
				Point2D p1 = currentNode.getLocation(); 
				Point2D p2 = j_node.getLocation();
				graph.connect(currentNode.getKey(), j_node.getKey(), Line.distance(p1, p2));	
				gameboard.getLinesOfGameGraph().add(new Line(gameboard.getMap().global2pixel(p1), gameboard.getMap().global2pixel(p2)));
			}
		}
	}

	private void addNodesToGameGraph(Graph gameGraph) {
		node_data graph_nodes = new Node(gameboard.player.getId(), gameboard.player.getLocation());
		graph_nodes.setTag(Player.getTag());
		gameGraph.addNode(graph_nodes);
		gameboard.getMoveableObjects().add(gameboard.getPlayer());
		
		addNodesToGraph_addMoveable_Objects(gameboard.getFruits(), Fruit.getTag(), gameGraph);
		addNodesToGraph_addMoveable_Objects(gameboard.getPacmans(), Pacman.getTag(), gameGraph);
		addNodesToGraph_addMoveable_Objects(gameboard.getGhosts(), Ghost.getTag(), gameGraph);
	}

	private void addNodesToGraph_addMoveable_Objects(List<Game_object> objects, String object_tag, Graph gameGraph) {
		for (int i=0; i < objects.size(); i++) {
			addNewNodeToGraph(objects.get(i), object_tag, gameGraph);
			isMoveableObject_IfSo_Add_It_To_Moveable_List(objects.get(i));
		}
	}

	private void isMoveableObject_IfSo_Add_It_To_Moveable_List(Game_object game_object) {
		if (game_object instanceof MoveableObject) 
			gameboard.getMoveableObjects().add((MoveableObject) game_object);		
	}

	private void addNewNodeToGraph(Game_object game_object, String object_tag, Graph gameGraph) {
		node_data node = new Node(game_object.getId(), game_object.getLocation());
		node.setTag(object_tag);
		gameGraph.addNode(node);		
	}

}
