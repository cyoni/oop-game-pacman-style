package game;

import GameObjects.Game_object;
import GameObjects.MoveableObject;

public class GameBoard_algo {
	
	private GameBoard gameboard;

	public GameBoard_algo(GameBoard gameboard) {
		this.gameboard = gameboard;
	}

	public void cleanBoard() {
		gameboard.game_running = false;
		Game_object.resetTotalObjects();
		DropingItemsOnScreen.selectNone();
		gameboard.graph_Game.clear();
		gameboard.MST_graph.clear();
		gameboard.fruits.clear();
		gameboard.ghosts.clear();
		gameboard.pacmans.clear();
		gameboard.player = null;
		gameboard.getGuiAlgo().removeAll();
		gameboard.getGuiAlgo().repaint();
	}

	public String getGameStat() {
		String result = "";
		for (int i = 0; i < gameboard.moveableObjects.size(); i++) {
			MoveableObject object = gameboard.moveableObjects.get(i);
			result+= object.getId() + " ate " + object.getNumEatenFruits() + " fruits.\n";
		}
		return result;
	}

	
}
