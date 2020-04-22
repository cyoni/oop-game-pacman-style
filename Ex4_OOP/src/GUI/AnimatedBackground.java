package GUI;

import GameObjects.Game_object;
import GameObjects.Ghost;
import GameObjects.Pacman;
import algorithms.Point2D;
import game.GameBoard;

public class AnimatedBackground {

	private int pacmanSize = 20;
	private int ghostSize = 10;
	
	private Gui_algo gui_algo;
	GameBoard gameboard;
	
	public AnimatedBackground(Gui_algo gui_algo) {
		this.gui_algo = gui_algo;
		gameboard = gui_algo.getGameboard();
	}
	
	public void start() {
		gameboard.cleanBoard();
		getRandomPacmans();
		getRandomGhosts();
		gameboard.startGame();
		gameboard.initializeAndStartPacmansThreads();
		gameboard.initializeAndStartGhosts();
	}
	
	private void getRandomGhosts() {
		for (int i=0; i<ghostSize; i++) {
			Ghost ghost = new Ghost(Game_object.GLOBAL_ID++, getRandomLocation(), 1, 2);
			ghost.setDegree(getRandomNumber(0,360));
			gameboard.addGhost(ghost);
		}		
	}

	public void getRandomPacmans() {
		for (int i=0; i<pacmanSize; i++) {
			Pacman pacman = new Pacman(Game_object.GLOBAL_ID++, getRandomLocation(), 1, 2);
			pacman.setDegree(getRandomNumber(0,360));
			gameboard.addPacman(pacman);
		}
	}

	private Point2D getRandomLocation() {
		int x = getRandomNumber(10, Screen.WIDTH-30);
		int y = getRandomNumber(10, Screen.HEIGHT-30);
		
		Point2D location = new Point2D(x, y);
		return gui_algo.map.pixel2global(location);
	}
	
	private int getRandomNumber(int Min, int Max) {
		return (Min + (int)(Math.random() * ((Max - Min) + 1)));
	}
	
	
	
	
}
