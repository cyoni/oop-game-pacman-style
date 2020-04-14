package GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import GameObjects.Fruit;
import GameObjects.Pacman;
import GameObjects.Player;
import GameObjects.game_object;
import Geom.Point2D;
import game.DropingItemsOnScreen;

public class MouseClickOnScreen implements MouseListener {
	
	
	private Gui_algo gui_algo;

	public MouseClickOnScreen(Gui_algo gui_algo) {
		this.gui_algo = gui_algo;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {

		System.out.println(e.getX() + "," + e.getY());
		
		if (e.getButton() == java.awt.event.MouseEvent.BUTTON1) { // left mouse click
				if (DropingItemsOnScreen.dropping_apples) dropApple(e);	
				else if (DropingItemsOnScreen.dropping_pacmans) dropPacman(e); 
				else if (DropingItemsOnScreen.dropping_player) dropPlayer(e); 
		}
		else 
			if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) { // right mouse click
				if (DropingItemsOnScreen.dropping_apples) {DropingItemsOnScreen.dropping_apples = false;}
				if (DropingItemsOnScreen.dropping_pacmans) {DropingItemsOnScreen.dropping_pacmans = false;} 
				
			} 

	}

	private void dropPlayer(MouseEvent e) {
		Point2D point = new Point2D(e.getX()-20, e.getY()-70);
		Player player = new Player(1, point, 1);
		gui_algo.gameboard.setPlayer(player);
		System.out.println("Player is set " + player.getLocation());
		gui_algo.repaint();
	}
	
	private void dropPacman(MouseEvent e) {
		Point2D point = new Point2D(e.getX()-20, e.getY()-70);
		Pacman pacman = new Pacman(Pacman.id++, point, 1);
		gui_algo.gameboard.getPacmans().add(pacman);
		System.out.println(Pacman.id + " pacman/s was/were added " + pacman.getLocation());
		gui_algo.repaint();
	}


	private void dropApple(MouseEvent e) {
		Point2D point = new Point2D(e.getX()-15, e.getY()-70);
		Fruit fruit = new Fruit(Fruit.id++, point , 9999);
		gui_algo.gameboard.getFruits().add(fruit);
		System.out.println(Fruit.id + " apple/s was/were added " + fruit.getLocation());
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
