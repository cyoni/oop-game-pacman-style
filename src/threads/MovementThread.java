package threads;

import GIS.Map;
import GUI.Gui_algo;
import GUI.Screen;
import GameObjects.MoveableObject;
import GameObjects.Player;
import algorithms.Point2D;
import game.GameBoard;

public class MovementThread extends Thread {
	
	private GameBoard gameBoard;
	private MoveableObject moveableObject;

	public MovementThread(GameBoard gameBoard, MoveableObject hungryObject) {
		this.gameBoard = gameBoard;
		this.moveableObject = hungryObject;
	}
	
	public void run() {
		while (gameBoard.isRunning()) {
			try {sleep(10);} catch (InterruptedException e) {}
			Point2D global_location = moveableObject.getLocation();
			Point2D local_location = Map.global2pixel(global_location);
			
			if ( (moveableObject instanceof Player && closeToBlock(moveableObject, local_location))) {
				moveableObject.setDegree((-1)*moveableObject.getDegree());
				moveableObject.setDegree(180-moveableObject.getDegree());
			}
			
			
			double degree_to_radian = Math.toRadians(moveableObject.getDegree());
			double x = local_location.x() + moveableObject.getVelocity() * Math.cos(degree_to_radian);
			double y = local_location.y() - moveableObject.getVelocity() * Math.sin(degree_to_radian);
			correctObjectIfItsOutOfBounds(x, y);
			Point2D new_location = gameBoard.getMap().pixel2global(new Point2D(x, y));
			moveableObject.setLocation(new_location);
			gameBoard.getGuiAlgo().repaint();
			
		}
	}

	private boolean closeToBlock(MoveableObject object, Point2D local_location) {
		return gameBoard.isCloseToBlock(object, local_location);
	}

	private void correctObjectIfItsOutOfBounds(double x, double y) {
		if ( y < 5 || y > (Screen.HEIGHT-35) ) {
			moveableObject.setDegree((-1)*moveableObject.getDegree());
		}
		if (x > (Screen.WIDTH-40) || (x < 10)) {
			moveableObject.setDegree(180-moveableObject.getDegree());
		}			
	}

}
