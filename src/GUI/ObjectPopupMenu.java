package GUI;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import GameObjects.Fruit;
import GameObjects.GameObject;
import GameObjects.MoveableObject;
import algorithms.Point2D;
import game.DropingItemsOnScreen;
import game.GameBoard;
import game.InitGame;

 
public class ObjectPopupMenu {

   private JPopupMenu popup;
   private JMenuItem removeItem, alterWeight, alterEatingRadius, alterSpeed, addNewFruit, addNewPacman, doNothing, addNewGhost, dropPlayer,startGame;
   private GameObject objectThatIsBeingPressed;
   private GameBoard gameBoard;
   private Point2D mousePoint;
private Gui_algo gui_algo;
   
   public ObjectPopupMenu(Gui_algo gui_algo) {
	   this.gui_algo = gui_algo;
	   this.gameBoard = gui_algo.getGameboard();
   }
   
   public void setObjectThatIsBeingPressed(GameObject objectThatIsBeingPressed) {
	   this.objectThatIsBeingPressed = objectThatIsBeingPressed;
   }
   
   private void initializeMenu() {
	   popup = new JPopupMenu();
	   removeItem = new JMenuItem("Remove");
	   alterEatingRadius = new JMenuItem("Alter Eating Radius...");
	   doNothing = new JMenuItem("Close Menu");
	   alterSpeed = new JMenuItem("Alter Velocity...");
	   alterWeight = new JMenuItem("Alter Weight...");
	   
	   addNewFruit = new JMenuItem("Drop a New Fruit...");
	   addNewPacman = new JMenuItem("Drop a New Pacman...");
	   dropPlayer = new JMenuItem("Set Player Here");
	   addNewGhost = new JMenuItem("Set Ghost here");
	   startGame = new JMenuItem("Start Game");
	   startMouseListenning();
   }
   
   private void setDefaultMenuForObjects() {
	   initializeMenu();	  
	   popup.add(doNothing);
	   popup.addSeparator();
	   popup.add(addNewFruit);
	   popup.add(addNewPacman);
	   popup.add(dropPlayer);
	   popup.add(addNewGhost);
	   popup.addSeparator();
	   popup.add(startGame);
   }
   
   public void setMenuOfMoveableObject() {
	   setDefaultMenuForObjects();
	   popup.addSeparator();
	   addRemoveAndAlterPosition();
	   popup.add(alterSpeed);
	   popup.add(alterEatingRadius);
   }
   
   private void addRemoveAndAlterPosition() {
	   popup.add(removeItem);
	   popup.addSeparator();
}

   public void setMenuOfFruit() {
	   setDefaultMenuForObjects();
	   popup.addSeparator();  
	   addRemoveAndAlterPosition();
	   popup.add(alterWeight);
   }
   
   public void setMenuAnyPressExceptObject() {
	   initializeMenu();
	   setDefaultMenuForObjects();
   }
  
   private void startMouseListenning() {
	   
	   startGame.addActionListener((ActionEvent e) -> {
	       	InitGame init = new InitGame(gui_algo);
	       	init.startGame();
	   });
	   
	   dropPlayer.addActionListener((ActionEvent e) -> {
		   DropingItemsOnScreen.dropPlayer(gameBoard, mousePoint);
	   });
	   
	   addNewGhost.addActionListener((ActionEvent e) -> {
		   DropingItemsOnScreen.dropGhost(gameBoard, mousePoint);
	   });
	   
	   addNewPacman.addActionListener((ActionEvent e) -> {
		   DropingItemsOnScreen.dropPacman(gameBoard, mousePoint);
	   });
	   
	   addNewFruit.addActionListener((ActionEvent e) -> {
		   DropingItemsOnScreen.dropApple(gameBoard, mousePoint);
	   });
	   
	   removeItem.addActionListener((ActionEvent e) -> {
		   gameBoard.removeItem(objectThatIsBeingPressed);
	   });
	   
	   alterSpeed.addActionListener((ActionEvent e) -> {
		   gameBoard.alterSpeed((MoveableObject)objectThatIsBeingPressed);
	   });
	   
	   alterWeight.addActionListener((ActionEvent e) -> {
		   gameBoard.alterWeight((Fruit)objectThatIsBeingPressed);
	   });
	   
	   alterEatingRadius.addActionListener((ActionEvent e) -> {
		   gameBoard.alterEatingRadius((MoveableObject)objectThatIsBeingPressed);
	   });   
	   
   }
   
   public void showPopup(MouseEvent mouseEvent) {
	   this.mousePoint =  gameBoard.getMap().pixel2global(new Point2D(mouseEvent.getX()+5, mouseEvent.getY()-40));
      if(mouseEvent.isPopupTrigger())
         popup.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
   }

}