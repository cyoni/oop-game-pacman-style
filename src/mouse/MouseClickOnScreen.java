package mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import GIS.Map;
import GUI.Gui_algo;
import GUI.ObjectPopupMenu;
import GameObjects.Fruit;
import GameObjects.Pacman;
import GameObjects.Player;
import GameObjects.GameObject;
import GameObjects.MoveableObject;
import algorithms.Line;
import algorithms.NumberGenerator;
import algorithms.Point2D;
import game.DropingItemsOnScreen;
import game.GameBoard;
import game.InitGame;

public class MouseClickOnScreen implements MouseListener, MouseMotionListener {

	private Gui_algo gui_algo;
	private ObjectPopupMenu popup;
	private DragFruitOnBoard dragObject;

	public MouseClickOnScreen(Gui_algo gui_algo) {
		this.gui_algo = gui_algo;
		dragObject = new DragFruitOnBoard(gui_algo);
		popup = new ObjectPopupMenu(gui_algo);
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		int pixelX = e.getX();// - 20;
		int pixelY = e.getY();// - 65;
		
		System.out.println(e.getX() + "," + e.getY());
		
		
		Point2D localCoords = new Point2D(pixelX, pixelY);

		dragObject.setCurrentDraggedObject(null);

	//	gui_algo.getGameboard().flushIfNeeded();
		
		System.out.println(isAnObjectBeingPressed(localCoords, e));

		if (isGameRunning() && isManualVersionOn()) {
			getAndSetDegreeOfPlayer(localCoords);
		}
		else if (GameBoard.isGenerateGame() && isRightButtonMousePressed(e) && isAnObjectBeingPressed(localCoords, e) == false && isGameRunning() == false)
			showGlobalMenu(e);
		else if (isGameRunning() == false && isAnObjectBeingPressed(localCoords, e))
			showPopupMenu(e);
		else if (isGameRunning() == false && isDroppingObjectsOnScreen())
			dropItems(localCoords, e);
	}

	private void showGlobalMenu(MouseEvent e) {
		popup.setMenuAnyPressExceptObject();
		popup.showPopup(e);
	}

	private void showPopupMenu(MouseEvent e) {
		popup.showPopup(e);
	}

	private boolean isDroppingObjectsOnScreen() {
		return DropingItemsOnScreen.isDropping();
	}

	private boolean isAnObjectBeingPressed(Point2D localCoords, MouseEvent e) {
		Point2D globalPoint = Map.convertPixelToglobal(new Point2D(localCoords.x(), localCoords.y()));
		return lookForMoveableObjects(globalPoint) || lookForFruits(globalPoint);
	}

	// need to be in gui_algo of game
	private boolean lookForMoveableObjects(Point2D globalPoint) {
		List<MoveableObject> moveable = new ArrayList<>();
		
		for (int i = 0; i < gui_algo.getGameboard().getPacmans().size(); i++)
			moveable.add((MoveableObject) gui_algo.getGameboard().getPacmans().get(i));
		
		if (gui_algo.getGameboard().getPlayer() != null)
			moveable.add(gui_algo.getGameboard().getPlayer());

		if (gui_algo.getGameboard().getGhost() != null)
			moveable.add((MoveableObject) gui_algo.getGameboard().getGhost());

		for (GameObject moveable_object : moveable) {
			double distance = Line.distance(globalPoint, moveable_object.getLocation());
			

			
			if (distance < 10) {
				popup.setObjectThatIsBeingPressed(moveable_object);
				popup.setMenuOfMoveableObject();
				return true;
			}
		}

		return false;
	}

	private boolean lookForFruits(Point2D globalPoint) {
		for (GameObject fruit : gui_algo.getGameboard().getFruits()) {
			double distance = Line.distance(globalPoint, fruit.getLocation());
			if (distance < 10) {
				popup.setObjectThatIsBeingPressed(fruit);
				popup.setMenuOfFruit();
				return true;
			}
		}
		return false;
	}

	private boolean isGameRunning() {
		return gui_algo.getGameboard().isRunning();
	}

	private void dropItems(Point2D localCoords, MouseEvent e) {
		if (!gui_algo.getGameboard().isRunning()) {
			DropingItemsOnScreen drop = new DropingItemsOnScreen();
			drop.dropItem(gui_algo, localCoords, e);
		}
	}

	public static boolean isLeftButtonPressed(MouseEvent e) {
		return e.getButton() == java.awt.event.MouseEvent.BUTTON1;
	}

	public static boolean isWheelButtonPressed(MouseEvent e) {
		return e.getButton() == java.awt.event.MouseEvent.BUTTON2;
	}

	private boolean isRightButtonMousePressed(MouseEvent e) {
		return e.getButton() == java.awt.event.MouseEvent.BUTTON3;
	}

	private void getAndSetDegreeOfPlayer(Point2D localCoords) {
		Point2D global_coords_target = Map.convertPixelToglobal(localCoords);
		double degree = Line.getMouseDegree(gui_algo.getGameboard(), gui_algo.getGameboard().getPlayer().getLocation(),
				global_coords_target);
		gui_algo.getGameboard().getPlayer().setDegree(degree);
	}

	private boolean isManualVersionOn() {
		return (gui_algo.getGameboard().isRunning() && gui_algo.getGameboard().isAutoGame() == false);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		int pixelX = e.getX() - 20;
		int pixelY = e.getY() - 65;

		Point2D localCoords = new Point2D(pixelX, pixelY);

		if (gui_algo.getGameboard().isRunning() == false) {
			dragObject.drag(localCoords);
		}

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	}

}
