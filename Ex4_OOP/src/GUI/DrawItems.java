package GUI;

import java.awt.Graphics;
import java.util.List;

import GIS.Map;
import GameObjects.Block;
import GameObjects.Fruit;
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

	public void drawObjects(Graphics graphics, List<game_object> list) {
		for (int i=0; i< list.size(); i++) {
			game_object obj = list.get(i);
			Point3D pos = obj.getLocation();
			graphics.drawImage(obj.getPicture() , (int) ( pos.x()  ), (int) (pos.y() ), 23,	23, null);
		}
	}

	public void drawBlocks(Graphics graphics, List<Block> list) {
		
		for (int i=0; i< list.size(); i++) {
			Block obj = list.get(i);
			Point3D pos1 = obj.getLocation_p1();
			Point3D pos2 = obj.getLocation_p2();
			
			int x = (int) ((pos1.x()-pos2.x())/2);
			int y = (int) ((pos1.y()-pos2.y())/2);
			int width = (int) Math.abs(pos1.x()-pos2.x());
			int height = (int) Math.abs( pos1.y()-pos2.y());
			
			graphics.fillRect(((int)pos1.x()), (int)pos2.y(), width, height); 
		}
	}

}
