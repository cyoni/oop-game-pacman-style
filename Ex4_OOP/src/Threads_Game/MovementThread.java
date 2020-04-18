package Threads_Game;

import GUI.Gui_algo;
import GUI.Screen;
import GameObjects.MoveableObject;
import algorithms.Point2D;

public class MovementThread extends Thread {
	
	private Gui_algo gui_algo;
	private MoveableObject moveableObject;

	public MovementThread(Gui_algo gui_algo, MoveableObject hungryObject) {
		this.gui_algo = gui_algo;
		this.moveableObject = hungryObject;
	}
	
	public synchronized void run() {
		while (gui_algo.getGameboard().isRunning()) {
			try {sleep(10);} catch (InterruptedException e) {}
			Point2D global_location = moveableObject.getLocation();
			Point2D local_location = gui_algo.map.global2pixel(global_location);
			double degree_to_radian = Math.toRadians(moveableObject.getDegree());
			double x = local_location.x() + moveableObject.getVelocity() * Math.cos(degree_to_radian);
			double y = local_location.y() - moveableObject.getVelocity() * Math.sin(degree_to_radian);
			correctObjectIfItsOutOfBounds(x, y);
			Point2D new_location = gui_algo.map.pixel2global(new Point2D(x, y));
			moveableObject.setLocation(new_location);
			gui_algo.repaint();
		}
	}

	private void correctObjectIfItsOutOfBounds(double x, double y) {
		if ( y < 5 ||   y > (Screen.HEIGHT-35) ) {
			moveableObject.setDegree((-1)*moveableObject.getDegree());
		}
		if (x > (Screen.WIDTH-40) || (x < 10)) {
			moveableObject.setDegree(180-moveableObject.getDegree());
		}			
	}

}
