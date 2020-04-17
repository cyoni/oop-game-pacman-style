package GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import GameObjects.Fruit;
import GameObjects.Pacman;
import GameObjects.Player;
import GameObjects.game_object;
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
		System.out.println(e.getX() + "," + e.getY());
		
		Point2D mouseCoords = gui_algo.map.pixel2global(new Point2D(e.getX() , e.getY()));
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

	}

	public void dropPlayer(Point2D mouseCoords) {
		int id;
		if (gui_algo.getGameboard().getPlayer() == null)
		  id = game_object.totalObjects++;
		else id = gui_algo.getGameboard().getPlayer().getId();
		
		Player player = new Player(id, mouseCoords, 1);
		gui_algo.gameboard.setPlayer(player);
		System.out.println("Player is set " + player.getLocation());
		gui_algo.repaint();
	}
	
	private void dropPacman(Point2D mouseCoords) {
		Pacman pacman = new Pacman(game_object.totalObjects++, mouseCoords, 1);
		gui_algo.gameboard.getPacmans().add(pacman);
		System.out.println(game_object.totalObjects + " pacman/s was/were dropped " + pacman.getLocation());
		gui_algo.repaint();
	}


	private void dropApple(Point2D mouseCoords) {
		Fruit fruit = new Fruit(game_object.totalObjects++, mouseCoords , 9999);
		System.out.println("got " + fruit.getId());
		gui_algo.gameboard.getFruits().add(fruit);
		System.out.println(game_object.totalObjects + " apple/s was/were dropped " + fruit.getLocation());
		gui_algo.repaint();
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
