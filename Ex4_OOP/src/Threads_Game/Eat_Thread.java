package Threads_Game;



import java.util.ArrayList;
import java.util.List;
import GameObjects.*;
import algorithms.Line;
import GUI.Gui_algo;

public class Eat_Thread extends Thread {
	
	private Gui_algo gui_algo;
	List<Game_object> fruits;
	private List<MoveableObject> moveable_objects;
	
	public Eat_Thread(Gui_algo gui_algo) {
		this.gui_algo = gui_algo;
		moveable_objects = new ArrayList<>();
		moveable_objects = gui_algo.getGameboard().getMoveableObjects();
		fruits = gui_algo.getGameboard().getFruits();
	}
	
	
	public synchronized void run() {
		while (gui_algo.getGameboard().isRunning()) {
			try {sleep(200);} catch (InterruptedException e) {}
			
			for (MoveableObject currentMoveable_object : moveable_objects) {
				for (int i=0; i<fruits.size(); i++) {
					Fruit current_fruit = (Fruit) fruits.get(i);
					double distance = Line.distance(currentMoveable_object.getLocation(), current_fruit.getLocation());
					if (distance <= currentMoveable_object.getEatingRadius()) {
						fruits.remove(current_fruit);
						i--;
					}
				}
			}			
		}
	}
}
