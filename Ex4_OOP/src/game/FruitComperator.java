package game;

import java.util.Comparator;

import Coords.MyCoords;
import GameObjects.game_object;

public class FruitComperator implements Comparator<game_object> {
	
	 private GameBoard gameboard;

	public FruitComperator(GameBoard gameboard) {
		this.gameboard = gameboard;
	}

	@Override
	public int compare(game_object f1, game_object f2) {
		MyCoords mc = new MyCoords();

		double distance_player_to_fruit1 = mc.distance2D(f1.getLocation(), gameboard.getPlayer().getLocation());
		double distance_player_to_fruit2 = mc.distance2D(f2.getLocation(), gameboard.getPlayer().getLocation());
		
		//System.out.println(distance_player_to_fruit1 + "$");
		//System.out.println(distance_player_to_fruit1 + " VS " + distance_player_to_fruit2);
		
		if (distance_player_to_fruit1 < distance_player_to_fruit2) 
			return -1;
		else 
			return 1;	
		
	}

}
