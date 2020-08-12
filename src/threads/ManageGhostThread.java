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
	
	public void run() {
		while (gameboard.isRunning()) {
			try {
				sleep(100);
				if (gameboard.getPlayer() != null) {
					setDegreeToPlayer();
					if (howCloseGhostToPlayer() < 5) {
						decreasePointsToPlayer();
						gameboard.getPlayer().increaseEatenObject(ghost);
						
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Thread " + getId() + " (ghost) terminated.");
	}

	private void decreasePointsToPlayer() {
		int points_to_decrease = -10;
		gameboard.getPlayer().addToScore(points_to_decrease);
		System.out.println("Ghost just decreased the player's score by " + points_to_decrease + " points");
	}

	private double howCloseGhostToPlayer() {
		return Line.distance(ghost.getLocation(), gameboard.getPlayer().getLocation());
	}

	private void setDegreeToPlayer() {
		ghost.setDegree(Line.getMouseDegree(gameboard, ghost.getLocation(), gameboard.getPlayer().getLocation()));		
	}


}
