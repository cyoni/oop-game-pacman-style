package GameObjects;

import GIS.Map;
import GUI.Gui_dialog;
import Geom.Point2D;
import game.GameBoard;

/**

 */
public class Player extends game_object {

	public static String picture = "player.jpg";
	private double degree = 30;

	public Player(int id, Point2D location, double d) {
		super(id, location);
	}

	public double getDegree() {
		return degree;
	}
	
	public String toString() {
		Point2D global_location = getGlobalPoint();
		return "M,"	+ getId() + 
				","	+ global_location.x() + 
				"," + global_location.y();
	}
		
	public void setDegree(double degree) {
		this.degree = degree;
	}


}
