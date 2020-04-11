package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JFrame;

import GIS.Map;

import GameObjects.Rectangle;
import GameObjects.Fruit;
import GameObjects.Line;
import GameObjects.Player;
import GameObjects.game_object;
import Geom.Point3D;
import game.GameBoard;


public class DrawItems {
	
	private GameBoard gameBoard;
	private Map map; 

	
	public DrawItems(Map map, GameBoard gameBoard) {
		this.gameBoard = gameBoard;
		this.map = map;
	}

	public void drawObjects(List<game_object> list, Graphics graphics,  BufferedImage picture, Gui_algo gui_algo) {
		for (int i=0; i< list.size(); i++) {
			game_object obj = list.get(i);
			Point3D pos = map.coordsToPixel(obj.getLocation().y(), obj.getLocation().x());
			graphics.drawImage(picture , (int) ( pos.x()-20), (int) (pos.y()-40), 23, 23, null);
	
			
		//	graphics.drawImage(picture, (int) (pos.x() - (rP / 2)), (int) (pos.y() - (rP / 2)), rP,	rP, null);
			
		}
	}
/*
	public void drawBlocks( List<Rectangle> list,Graphics graphics) {
		Graphics2D Graphics = (Graphics2D) graphics;
		for (int i=0; i< list.size(); i++) {
			Rectangle obj = list.get(i);
			Point3D pos1 = obj.getP_up_left();
			Point3D pos2 = obj.getP_down_right();
			
			int width = (int) Math.abs(pos1.x()-pos2.x());
			int height = (int) Math.abs( pos1.y()-pos2.y());
			
			
			
			Graphics.fillRect(((int)pos1.x()), (int)pos2.y(), width, height);

			Point3D p1 = map.global2pixel(obj.getP1());// in pixel
			Point3D p2 = map.global2pixel(obj.getP2());// in pixel

			if (p2.x() < p1.x()) {
				swapX(p2, p1);
			}
			if (p2.y() < p1.y()) {
				swapY(p2, p1);
			}
			int width = (int) (p2.x() - p1.x());
			int height = (int) (p2.y() - p1.y());
			graphics.setColor(Color.BLACK);
			graphics.drawRect((int) p1.x(), (int) p1.y(), width, height);
			graphics.fillRect((int) p1.x(), (int) p1.y(), width, height);
			
			
		}
	}*/
	private void swapX(Point3D a, Point3D b) {
		double temp = a.x();
		a.set_x(b.x());
		b.set_x(temp);
	}

	private void swapY(Point3D a, Point3D b) {
		double temp = a.y();
		a.set_y(b.y());
		b.set_y(temp);
	}

	public void drawPlayer(Graphics graphics, BufferedImage picture, Gui_algo gui_algo) {
		Player player = gameBoard.getPlayer();
		

		Point3D pos = map.coordsToPixel( player.getLocation().y(),  player.getLocation().x());
		
		graphics.drawImage(picture , (int) (pos.x()), (int)pos.y(), 23, 23, null);

	}

	public void drawLinesToRectengales(List<Line> lines, Graphics graphics) {	
		
		for (int i = 0; i < lines.size(); i++) {
			graphics.drawLine((int)lines.get(i).getP1().x(), (int)lines.get(i).getP1().y(), (int)lines.get(i).getP2().x(), (int)lines.get(i).getP2().y());	
		}
	}



}
