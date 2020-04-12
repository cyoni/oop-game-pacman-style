package GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import GameObjects.Fruit;
import Geom.Point2D;
import game.DropingItemsOnScreen;

public class MouseClick implements MouseListener {
	
	
	private Gui_algo gui_algo;

	public MouseClick(Gui_algo gui_algo) {
		this.gui_algo = gui_algo;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {

		System.out.println(e.getX() + "," + e.getY());
		
		if (e.getButton() == java.awt.event.MouseEvent.BUTTON1) { // left mouse click
				if (DropingItemsOnScreen.dropping_apples) dropApple(e);
				
		}
		else 
			if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) { // right mouse click
				if (DropingItemsOnScreen.dropping_apples) {DropingItemsOnScreen.dropping_apples = false;}
				if (DropingItemsOnScreen.dropping_pacmans) {DropingItemsOnScreen.dropping_pacmans = false;} 
				
			} 
			
			//	System.out.println(e.getX() + "," + e.getY());
		
	}

	private void dropApple(MouseEvent e) {
		Point2D point = new Point2D(e.getX(), e.getY());
		Point2D insert = new Point2D(gui_algo.map.pixel2global(point).y(),gui_algo.map.pixel2global(point).x());
		
		Fruit fruit = new Fruit(gui_algo.gameboard, gui_algo.map, 999,insert , 9999);
		gui_algo.gameboard.getFruits().add(fruit);
		System.out.println("apple added " + fruit.getLocation().x() + "," + fruit.getLocation().y() );
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
