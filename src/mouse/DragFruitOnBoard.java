package mouse;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import GUI.Gui_algo;
import GameObjects.Game_object;
import GameObjects.MoveableObject;
import algorithms.Line;
import algorithms.Point2D;

public class DragFruitOnBoard {

	private Gui_algo gui_algo;
	Game_object object_to_drag;

	public DragFruitOnBoard(Gui_algo gui_algo) {
		this.gui_algo = gui_algo;
	}

	public void drag(Point2D localCoords) {
		Point2D global_point = gui_algo.map.pixel2global(localCoords);
		
		if (object_to_drag == null)
			lookForObject(global_point);
		else {
			
			object_to_drag.setLocation(global_point);
			gui_algo.repaint();
		}
	}

	private void lookForObject(Point2D globalPoint) {

			List<Game_object> game_objects = new ArrayList<>();
			
			List<Game_object> allObjects = gui_algo.getGameboard().addAllObjects();
			game_objects.addAll(allObjects);
			
			for (Game_object current_object : game_objects) {
			double distance = Line.distance(globalPoint, current_object.getLocation());
				if (distance < 10) {
					setCurrentDraggedObject(current_object);
				}
			}	
	}

	public void setCurrentDraggedObject(Game_object object) {
		this.object_to_drag = object;
	}

}
