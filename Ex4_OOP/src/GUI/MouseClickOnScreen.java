package GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import GameObjects.Fruit;
import GameObjects.Pacman;
import GameObjects.Player;
import GameObjects.Game_object;
import algorithms.Point2D;
import game.DropingItemsOnScreen;
import game.InitGame;

public class MouseClickOnScreen implements MouseListener {
	
	private Gui_algo gui_algo;

	public MouseClickOnScreen(Gui_algo gui_algo) {
		this.gui_algo = gui_algo;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int pixelX = e.getX() - 20;
		int pixelY = e.getY() - 65;
		Point2D localCoords =  new Point2D(pixelX, pixelY);
		
		System.out.println(pixelX + "," + pixelY);
		
		checkGameVersion(localCoords);
		
		if (!gui_algo.getGameboard().isRunning()) {
		
		Point2D mouseCoords = gui_algo.map.pixel2global(localCoords);
		if (e.getButton() == java.awt.event.MouseEvent.BUTTON1) { // left mouse click
				if (DropingItemsOnScreen.dropping_apples) dropApple(mouseCoords);	
				else if (DropingItemsOnScreen.dropping_pacmans) dropPacman(mouseCoords); 
				else if (DropingItemsOnScreen.dropping_player) dropPlayer(mouseCoords); 
		}
		else 
			if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) { // right mouse click
				if (DropingItemsOnScreen.dropping_apples) {DropingItemsOnScreen.dropping_apples = false;}
				else if (DropingItemsOnScreen.dropping_pacmans) {DropingItemsOnScreen.dropping_pacmans = false;} 
				else if (DropingItemsOnScreen.dropping_player) {
						DropingItemsOnScreen.dropping_player = false;
			        	InitGame init = new InitGame(gui_algo);
						init.buildGraphGame();
						gui_algo.repaint();
					 }
		   } 
	}}

	private void checkGameVersion(Point2D localCoords) {
		if (isManualVersion()) {
			
			double degree = getMouseDegree(localCoords);
			gui_algo.getGameboard().getPlayer().setDegree(degree);
			
		}
	}

	private boolean isManualVersion() {
		return (gui_algo.getGameboard().isRunning() && gui_algo.getGameboard().isAutoGame() == false);
	}

	private double getMouseDegree(Point2D localCoords) {
			Point2D player_loc_pix = gui_algo.map.global2pixel(gui_algo.getGameboard().getPlayer().getLocation());
			double radian = Math.atan2(player_loc_pix.y()-localCoords.y(), player_loc_pix.x()-localCoords.x());
			double degree = Math.toDegrees(radian);
			degree = degree + 360;
			degree = 540 - degree;
			System.out.println(degree  + "*");
			return degree;
	}

	public void dropPlayer(Point2D mouseCoords) {
		int id;
		if (gui_algo.getGameboard().getPlayer() == null)
		  id = Game_object.totalObjects++;
		else id = gui_algo.getGameboard().getPlayer().getId();
		
		Player player = new Player(id, mouseCoords, 1, 2);
		gui_algo.gameboard.setPlayer(player);
		System.out.println("Player is set " + player.getLocation());
		gui_algo.repaint();
	}
	
	private void dropPacman(Point2D mouseCoords) {
		Pacman pacman = new Pacman(Game_object.totalObjects++, mouseCoords, 1, 2);
		gui_algo.gameboard.getPacmans().add(pacman);
		System.out.println(Game_object.totalObjects + " pacman/s was/were dropped " + pacman.getLocation());
		gui_algo.repaint();
	}


	private void dropApple(Point2D mouseCoords) {
		int randomWeight = getRandomNumber(10, 50);
		Fruit fruit = new Fruit(Game_object.totalObjects++, mouseCoords, randomWeight);
		gui_algo.gameboard.getFruits().add(fruit);
		System.out.println(Game_object.totalObjects + "# id of that apple " + fruit.getLocation());
		gui_algo.repaint();
	}


	private int getRandomNumber(int Min, int Max) {
		return Min + (int)(Math.random() * ((Max - Min) + 1));
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
