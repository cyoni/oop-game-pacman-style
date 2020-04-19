package GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import GameObjects.Fruit;
import GameObjects.Pacman;
import GameObjects.Player;
import GameObjects.Game_object;
import algorithms.Line;
import algorithms.NumberGenerator;
import algorithms.Point2D;
import game.DropingItemsOnScreen;
import game.InitGame;

public class MouseClickOnScreen implements MouseListener {
	
	private Gui_algo gui_algo;

	public MouseClickOnScreen(Gui_algo gui_algo) {
		this.gui_algo = gui_algo;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		int pixelX = e.getX() - 20;
		int pixelY = e.getY() - 65;
		Point2D localCoords =  new Point2D(pixelX, pixelY);
		
		System.out.println(pixelX + "," + pixelY);
		
		checkGameVersion(localCoords);
		
		if (!gui_algo.getGameboard().isRunning()) {
		
		Point2D mouseCoords = gui_algo.map.pixel2global(localCoords);
		if (e.getButton() == java.awt.event.MouseEvent.BUTTON1) { // left mouse click
				if (DropingItemsOnScreen.global_dropping_apples) DropingItemsOnScreen.dropApple(gui_algo.getGameboard(), mouseCoords);	
				else if (DropingItemsOnScreen.global_dropping_pacmans) DropingItemsOnScreen.dropPacman(gui_algo.getGameboard(), mouseCoords); 
				else if (DropingItemsOnScreen.global_dropping_player) DropingItemsOnScreen.dropPlayer(gui_algo.getGameboard(), mouseCoords); 
		}
		else 
			if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) { // right mouse click
				if (DropingItemsOnScreen.global_dropping_apples) {DropingItemsOnScreen.global_dropping_apples = false;}
				else if (DropingItemsOnScreen.global_dropping_pacmans) {DropingItemsOnScreen.global_dropping_pacmans = false;} 
				else if (DropingItemsOnScreen.global_dropping_player) {
						DropingItemsOnScreen.global_dropping_player = false;
			        	InitGame init = new InitGame(gui_algo);
						init.buildGraphGame();
						gui_algo.repaint();
					 }
		   } 
	}}

	private void checkGameVersion(Point2D localCoords) {
		if (isManualVersion()) {
			Point2D global_coords_target = gui_algo.map.pixel2global(localCoords);
			double degree = Line.getMouseDegree(gui_algo.getGameboard(), gui_algo.getGameboard().getPlayer().getLocation(), global_coords_target);
			gui_algo.getGameboard().getPlayer().setDegree(degree);
			
		}
	}

	private boolean isManualVersion() {
		return (gui_algo.getGameboard().isRunning() && gui_algo.getGameboard().isAutoGame() == false);
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

}
