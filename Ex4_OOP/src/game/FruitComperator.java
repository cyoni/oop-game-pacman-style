package game;

import java.util.Comparator;

import Coords.MyCoords;
import GameObjects.Game_object;
import algorithms.Line;

public class FruitComperator implements Comparator<Game_object> {
	
	private Game_object eater;

	public FruitComperator(Game_object eater) {
		this.eater = eater;
	}

	@Override
	public int compare(Game_object f1, Game_object f2) {
		double distance_object_to_fruit1 = Line.distance(f1.getLocation(), eater.getLocation());
		double distance_object_to_fruit2 = Line.distance(f2.getLocation(), eater.getLocation());
		if (distance_object_to_fruit1 < distance_object_to_fruit2) 
			return -1;
		else 
			return 1;	
	}

}
