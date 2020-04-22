package game;

import GUI.Gui_dialog;
import GameObjects.Fruit;
import GameObjects.Game_object;
import GameObjects.Ghost;
import GameObjects.Pacman;
import GameObjects.Player;
import algorithms.NumberGenerator;
import algorithms.Point2D;
import mouse.MouseClickOnScreen;

public class DropingItemsOnScreen extends Thread {

	public static boolean global_dropping_apples = false;
	public static boolean global_dropping_pacmans = false;
	public static boolean global_dropping_player = false;
	public static boolean global_dropping_ghosts = false;

	
	public void startThreadDroppingItems() {
		start();
	}
	
	public synchronized void run() {
		if (global_dropping_apples) startDroppingApples();
		if (global_dropping_pacmans) startDroppingPacmans();
		if (global_dropping_ghosts) startDroppingGhosts();
		if (global_dropping_player) startDroppingPlayer();
	}
	
	private void startDroppingGhosts() {
		Gui_dialog.alert("Drop ghosts");
		global_dropping_ghosts = true;
		while(global_dropping_ghosts) {
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}			
	}

	public static boolean isDropping() {
		return global_dropping_apples || global_dropping_pacmans || global_dropping_player;
	}
	
	private void startDroppingPlayer() {
		Gui_dialog.alert("Choose your stating point.");
		global_dropping_player = true;
		while(global_dropping_player) {
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}

	private void startDroppingPacmans() {
		Gui_dialog.alert("Drop pacmans.");
		while (global_dropping_pacmans) {
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void startDroppingApples() {
		
		Gui_dialog.alert("Start dropping fruits. Once done, press the wheel button.");
		while (global_dropping_apples) {
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void selectToDropAll() {
		global_dropping_apples = true;
		global_dropping_pacmans = true;
		global_dropping_player = true;
		global_dropping_ghosts = true;
	}

	public static void selectNone() {
		global_dropping_apples = false;
		global_dropping_pacmans = false;
		global_dropping_player = false;
		global_dropping_ghosts = false;
	}
	
	public static void dropApple(GameBoard gameBoard, Point2D mouseCoords) {
		int randomWeight =  NumberGenerator.getRandomNumber(10, 50);
		Fruit fruit = new Fruit(Game_object.GLOBAL_ID++, mouseCoords, randomWeight);
		gameBoard.getFruits().add(fruit);
		System.out.println(Game_object.GLOBAL_ID + "# id of the apple " + fruit.getLocation());
		gameBoard.getGuiAlgo().repaint();
	}
	
	public static void dropPlayer(GameBoard gameBoard, Point2D mouseCoords) {
		int id;
		if (gameBoard.getPlayer() == null)
		  id = Game_object.GLOBAL_ID++;
		else id = gameBoard.getPlayer().getId();
		
		Player player = new Player(id, mouseCoords, 1, 2);
		gameBoard.setPlayer(player);
		System.out.println("Player is set " + player.getLocation());
		gameBoard.getGuiAlgo().repaint();
	}
	
	public static void dropPacman(GameBoard gameBoard, Point2D mouseCoords) {
		Pacman pacman = new Pacman(Game_object.GLOBAL_ID++, mouseCoords, 1, 2);
		gameBoard.getPacmans().add(pacman);
		System.out.println(Game_object.GLOBAL_ID + " #id of pacman " + pacman.getLocation());
		gameBoard.getGuiAlgo().repaint();
	}
	
	public static void dropGhost(GameBoard gameBoard, Point2D mouseCoords) {
		Ghost ghost = new Ghost(Game_object.GLOBAL_ID++, mouseCoords, 1, 2);
		gameBoard.getGhosts().add(ghost);
		System.out.println(Game_object.GLOBAL_ID + " #id of ghost " + ghost.getLocation());
		gameBoard.getGuiAlgo().repaint();
	}

}
