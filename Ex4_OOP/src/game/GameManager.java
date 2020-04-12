package game;

import java.util.ArrayList;
import java.util.List;

import FileFormat.CSVReader;
import GameObjects.Player;
import Geom.Point2D;

public class GameManager extends Thread {

	GameBoard gameboard;
	boolean isPlaying = false;
	long gameTimer = 50000;
	
	public GameManager(GameBoard gameboard) {
		this.gameboard = gameboard;
				
	}

	public void startGame() {
		isPlaying = true;
		start(); // start thread
	}
	
	
	public boolean getIsPlaying() {
		return isPlaying;
	}
	
	public void run() {
		
		while(isPlaying && gameTimer > 0) {
			gameTimer-=100;
			//System.out.println(gameTimer);
			
			
			Point2D p = gameboard.getPlayer().getLocation();
			double x = p.x();
			double y = p.y();
			Point2D pp = new Point2D(++x, ++y);
			gameboard.getPlayer().setLocation(pp);
			
			try {sleep(100);} catch (InterruptedException e) {}
		}

	}
	
	
}
