package Threads_Game;



import java.util.ArrayList;
import java.util.List;
import GameObjects.*;
import algorithms.Line;
import game.GameBoard;
import GUI.Gui_algo;

public class Eat_Thread extends Thread {
	
	private GameBoard gameboard;
	private List<Game_object> fruits;
	private List<MoveableObject> moveable_objects;
	
	public Eat_Thread(GameBoard gameboard) {
		this.gameboard = gameboard;
		moveable_objects = new ArrayList<>();
		moveable_objects = gameboard.getMoveableObjects();
		fruits = gameboard.getFruits();
	}
	
	
	public synchronized void run() {
		System.out.println("Thread " + getId() + " joined.");
		while (gameboard.isRunning()) {
			try {sleep(200);} catch (InterruptedException e) {}
			
			for (MoveableObject currentMoveable_object : moveable_objects) {
				
				if (currentMoveable_object instanceof Player) {
					for (int i=0; i<gameboard.getPacmans().size(); i++) {
						Pacman current_pacman = (Pacman) gameboard.getPacmans().get(i);
						double distance = Line.distance(currentMoveable_object.getLocation(), current_pacman.getLocation());
						if (distance <= currentMoveable_object.getEatingRadius()) {
							currentMoveable_object.increaseEatenFruits();
							System.out.println("Pacman " + current_pacman.getId() + " was eaten.");
							gameboard.getPacmans().remove(current_pacman);
							i--;
						}
					}
				}
				
				
				for (int i=0; i<fruits.size(); i++) {
					Fruit current_fruit = (Fruit) fruits.get(i);
					double distance = Line.distance(currentMoveable_object.getLocation(), current_fruit.getLocation());
					if (distance <= currentMoveable_object.getEatingRadius()) {
						currentMoveable_object.increaseEatenFruits();
						System.out.println("Fruit " + current_fruit.getId() + " was eaten.");
						fruits.remove(current_fruit);
						tellItToOtherThreads(current_fruit);
						i--;
					}
				}
				if (fruits.size() == 0) gameboard.stopGame();
			}			
		}
		System.out.println("Thread " + getId() + " (eat_thread) terminated.");
	}


	private void tellItToOtherThreads(Fruit current_fruit) {
		for (int i=0; i<gameboard.getPacmanThreads().size(); i++) {
			gameboard.getPacmanThreads().get(i).getNotifiedOfDeadFruits(current_fruit);
			// ghsosts too
		}
	}
}
