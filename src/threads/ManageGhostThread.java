package threads;

import GameObjects.Ghost;
import algorithms.Line;
import game.GameBoard;

public class ManageGhostThread extends Thread {

	private GameBoard gameboard;
	private Ghost ghost;

	public ManageGhostThread(GameBoard gameboard, Ghost ghost) {
		this.gameboard = gameboard;
		this.ghost = ghost;
		
		MovementThread threadMovement = new MovementThread(gameboard, ghost);
		System.out.println("Thread " + getId() + " joined.");
		threadMovement.start();
	}
	
	public synchronized void run() {
		while (gameboard.isRunning()) {
			try {
				sleep(2000);
				if (gameboard.getPlayer() != null)
					ghost.setDegree(Line.getMouseDegree(gameboard, ghost.getLocation(), gameboard.getPlayer().getLocation()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Thread " + getId() + " (ghost) terminated.");
	}


}
