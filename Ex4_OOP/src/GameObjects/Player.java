package GameObjects;

import GIS.Map;
import GUI.Gui_dialog;
import Geom.Point2D;
import game.GameBoard;

/**

 */
public class Player extends game_object implements IHungry{

	public static String picture = "player.jpg";
	private double degree = 30;
	private game_object target;

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
	
	@Override
	public game_object getTarget() {
		return target;
	}

	@Override
	public void setTarget(game_object target) {
		this.target = target;
	}


}
