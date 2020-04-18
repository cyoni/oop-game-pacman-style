package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.swing.JFrame;

import GIS.Map;

import GameObjects.Rectangle;
import GameObjects.Fruit;
import GameObjects.Player;
import GameObjects.Game_object;
import algorithms.Line;
import algorithms.Point2D;
import game.GameBoard;


public class DrawItems {
	
	private GameBoard gameBoard;
	private Map map; 

	
	public DrawItems(Map map, GameBoard gameBoard) {
		this.gameBoard = gameBoard;
		this.map = map;
	}

	public void drawObjects(List<Game_object> list, Graphics graphics,  BufferedImage picture, Gui_algo gui_algo) {
	/*	for (int i=0; i< list.size(); i++) {
			game_object obj = list.get(i);
			graphics.drawImage(picture , (int) ( obj.getLocation().x()), (int) (obj.getLocation().y()), 23, 23, null);
		}*/
	}


	public void drawPlayer(Graphics graphics, BufferedImage picture, Gui_algo gui_algo) {
		Player player = gameBoard.getPlayer();
		Point2D pos = new Point2D(player.getLocation().y(),  player.getLocation().x());
		graphics.drawImage(picture , (int) (pos.y()), (int)pos.x(), 23, 23, gui_algo);
	}

	public void drawLinesToRectengales(List<Line> lines, Graphics graphics) {	
		
		for (int i = 0; i < lines.size(); i++) {
			graphics.drawLine((int)lines.get(i).getP1().x(), (int)lines.get(i).getP1().y(), (int)lines.get(i).getP2().x(), (int)lines.get(i).getP2().y());	
		}
	}

	public synchronized void drawFruits(List<Game_object> list, Graphics graphics, BufferedImage fruit_image, Gui_algo gui_algo) {
/*		System.out.println("##");
		for (int i=0; i<list.size(); i++) {
			game_object obj = list.get(i);
			graphics.drawImage(fruit_image , (int) ( obj.getLocation().x()), (int) (obj.getLocation().y()), 23, 23, null);
		}*/
	}



}
