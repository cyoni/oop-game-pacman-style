package game;

import java.util.ArrayList;
import java.util.List;

import GUI.Gui_algo;
import GUI.Gui_dialog;
import GameObjects.Fruit;
import GameObjects.Game_object;
import GameObjects.Ghost;
import GameObjects.MoveableObject;
import GameObjects.Pacman;
import GameObjects.Player;
import Threads_Game.ManageGhostThread;
import Threads_Game.ManagePacmanThread;
import algorithms.Graph;
import algorithms.Line;

public class GameBoard_algo {
	
	private GameBoard gameboard;

	public GameBoard_algo(GameBoard gameboard) {
		this.gameboard = gameboard;
	}

	
	public void initializeDataStructure() {
		gameboard.pacmans = new ArrayList<>();
		gameboard.ghosts = new ArrayList<>();
		gameboard.fruits = new ArrayList<>();
		gameboard.moveableObjects = new ArrayList<>();
		gameboard.manageGhostThread = new ArrayList<>();
		gameboard.player = null;
		gameboard.game_running = false;
		gameboard.graph = new Graph();
		gameboard.graph_Game = new ArrayList<>();
		gameboard.MST_graph = new ArrayList<>();
		gameboard.managePacmanThread = new ArrayList<>();
		gameboard.autoGame = false;
		gameboard.cleanObjectsFromPreviousGame = false;
	}
	
	public void cleanBoard() {
		initializeDataStructure();
		
		Game_object.resetTotalObjects();
		DropingItemsOnScreen.selectNone();

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

	public void removeItem(Game_object object_to_remove) {
		if (object_to_remove instanceof Fruit) 
			gameboard.fruits.remove(object_to_remove);
		 else if (object_to_remove instanceof Ghost) 
			gameboard.ghosts.remove(object_to_remove);
		 else if (object_to_remove instanceof Pacman) 
				gameboard.pacmans.remove(object_to_remove);
		 else if (object_to_remove instanceof Player) 
				gameboard.player = null;
		gameboard.getGuiAlgo().repaint();
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
		for (int i=0; i<gameboard.getPacmans().size(); i++) {
			Game_object current_pacman = gameboard.getPacmans().get(i);
			ManagePacmanThread thread = new ManagePacmanThread(gameboard, (Pacman)current_pacman);
			gameboard.addPacmanThread(thread);
			thread.start();
		}		
	}


	public void initializeAndStartGhosts() {
		for (int i=0; i<gameboard.getGhosts().size(); i++) {
			Game_object current_ghost = gameboard.getGhosts().get(i);
			ManageGhostThread thread = new ManageGhostThread(gameboard, (Ghost)current_ghost);
			gameboard.addGhostThread(thread);
			thread.start();
		}		
	}
	
}
