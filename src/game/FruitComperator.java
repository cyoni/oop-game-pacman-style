package game;

import java.util.Comparator;

import Coords.MyCoords;
import GameObjects.GameObject;
import algorithms.Line;

public class FruitComperator implements Comparator<GameObject> {
	
	private GameObject eater;

	public FruitComperator(GameObject eater) {
		this.eater = eater;
	}

	@Override
	public int compare(GameObject f1, GameObject f2) {
		double distance_object_to_fruit1 = Line.distance(f1.getLocation(), eater.getLocation());
		double distance_object_to_fruit2 = Line.distance(f2.getLocation(), eater.getLocation());
		if (distance_object_to_fruit1 < distance_object_to_fruit2) 
			return -1;
		else 
			return 1;	
	}

}
