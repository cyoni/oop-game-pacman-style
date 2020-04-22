package GUI;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import GameObjects.Fruit;
import GameObjects.Game_object;
import GameObjects.MoveableObject;
import algorithms.Point2D;
import game.DropingItemsOnScreen;
import game.GameBoard;

 
public class ObjectPopupMenu {

   private JPopupMenu popup;
   private JMenuItem removeItem, alterWeight, alterEatingRadius, alterSpeed, addNewFruit, addNewPacman, doNothing, addNewGhost;
   private Game_object objectThatIsBeingPressed;
   private GameBoard gameBoard;
   private MouseEvent mouseEvent;
   private Point2D mousePoint;
   
   public ObjectPopupMenu(GameBoard gameBoard) {
	   this.gameBoard = gameBoard;
   }
   
   public void setObjectThatIsBeingPressed(Game_object objectThatIsBeingPressed) {
	   this.objectThatIsBeingPressed = objectThatIsBeingPressed;
   }
   
   private void initializeMenu() {
	   popup = new JPopupMenu();
	   removeItem = new JMenuItem("Remove");
	   alterEatingRadius = new JMenuItem("Alter Eating Radius...");
	   doNothing = new JMenuItem("Close Menu");
	   alterSpeed = new JMenuItem("Alter Velocity...");
	   alterWeight = new JMenuItem("Alter Weight...");
	   addNewFruit = new JMenuItem("Add a New Fruit...");
	   addNewPacman = new JMenuItem("Add a New Pacman...");
	   addNewGhost = new JMenuItem("Add a New Ghost...");
	   startMouseListenning();
   }
   
   private void setDefaultMenuForObjects() {
	   initializeMenu();	  
	   popup.add(doNothing);
	   popup.addSeparator();
	   popup.add(addNewFruit);
	   popup.add(addNewPacman);
	   popup.add(addNewGhost);
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
	   this.mouseEvent = mouseEvent;
	   this.mousePoint =  gameBoard.getMap().pixel2global(new Point2D(mouseEvent.getX()+5, mouseEvent.getY()-40));
      if(mouseEvent.isPopupTrigger())
         popup.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
   }

}