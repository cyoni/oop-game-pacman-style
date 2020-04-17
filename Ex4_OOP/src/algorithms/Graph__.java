package algorithms;

import java.util.HashMap;
import java.util.List;

import GUI.Gui_algo;
import GameObjects.Fruit;
import GameObjects.Player;
import GameObjects.game_object;
import game.GameBoard;

public class Graph__ {

	
	private Gui_algo gui_algo;
	private GameBoard gameboard;
	private int number_of_nodes;
	private Player player;
	private List<game_object> fruits;
	
	HashMap<Integer, List<Integer>> nodes;
	
	public Graph__(Gui_algo gui_algo) {
		this.gui_algo = gui_algo;
		this.gameboard = gui_algo.getGameboard();
		this.fruits = gameboard.getFruits();
		this.player = gameboard.getPlayer();
		nodes = new HashMap<>();
		
	}
	
	public void convertGraphToMatrix() {
		number_of_nodes = fruits.size() + 1;
		int mat[][] = new int[number_of_nodes][number_of_nodes];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				
			}
		}
		
		
	}
	
	
}
