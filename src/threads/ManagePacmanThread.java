package threads;

import java.util.PriorityQueue;

import GameObjects.Fruit;
import GameObjects.GameObject;
import GameObjects.Pacman;
import algorithms.Line;
import game.FruitComperator;
import game.GameBoard;

public class ManagePacmanThread extends Thread {

	private GameBoard gameboard;
	private Pacman pacman;
	private PriorityQueue<GameObject> closestFruits_to_this_pacman;
	private boolean thread_sleeping = false;
	
	public ManagePacmanThread(GameBoard gameboard, Pacman pacman) {
		this.gameboard = gameboard;
		this.pacman = pacman;
		closestFruits_to_this_pacman =  new PriorityQueue<GameObject>(5, new FruitComperator(pacman)); 
		closestFruits_to_this_pacman.addAll(gameboard.getFruits());
		
		MovementThread threadMovement = new MovementThread(gameboard, pacman);
		System.out.println("Thread " + getId() + " (pacman) joined.");
		threadMovement.start();
	}
	
	public synchronized void run() {
		while (gameboard.isRunning() && gameboard.getPacmans().contains(pacman)) {

			try {
				sleep(200);
				if (closestFruits_to_this_pacman.isEmpty() == false) {
					GameObject closestFruitToMe = closestFruits_to_this_pacman.peek();
					printPL();
					double degree =  Line.getMouseDegree(gameboard, pacman.getLocation(), closestFruitToMe.getLocation());
					pacman.setDegree(degree);
					System.out.println("Thread " + getId() + " is sleeping.");
					
					thread_sleeping = true;
					wait(); // go to sleep until the fruit is eaten.
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Thread " + getId() + " (pacman) terminated.");
	}
	
	private void printPL() {
		for (GameObject xx : closestFruits_to_this_pacman) {
		}
	}

	public  void getNotifiedOfDeadFruits(Fruit deadFruit) {
		closestFruits_to_this_pacman.remove(deadFruit);
		if (is_thread_asleep() && was_my_target_eaten())
			wakeMeUp();
	}
	
	private synchronized void wakeMeUp() {
		thread_sleeping = false;
		System.out.println("Thread " + getId() + " woke up.");
		notify(); // wake this thread up
	}

	private boolean was_my_target_eaten() {
		Fruit my_target = (Fruit)closestFruits_to_this_pacman.peek();
		return my_target==null || gameboard.getFruits().contains(my_target);
	}

	private boolean is_thread_asleep() {
		return thread_sleeping;
	}
	

}
