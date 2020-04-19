package GUI;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import GameObjects.Fruit;
import GameObjects.Game_object;
import GameObjects.MoveableObject;
import game.GameBoard;


public class ObjectPopupMenu {
	
   private JPopupMenu popup;
   private JMenuItem removeItem, alterWeight, alterPosition, alterEatingRadius, alterSpeed, addNewFruit, addNewPacman;
   private Game_object objectThatIsBeingPressed;
   private GameBoard gameBoard;
   
   public ObjectPopupMenu(GameBoard gameBoard) {
	   this.gameBoard = gameBoard;
	   setMenu();
   }
   
   public void setObjectThatIsBeingPressed(Game_object objectThatIsBeingPressed) {
	   this.objectThatIsBeingPressed = objectThatIsBeingPressed;
   }
   
   private void setMenu() {

	   popup = new JPopupMenu();

	   removeItem = new JMenuItem("Remove");
	   alterPosition = new JMenuItem("Alter Position..."); //TODO
	   
	   alterEatingRadius = new JMenuItem("Alter Eating Radius...");
	   alterSpeed = new JMenuItem("Alter Velocity...");
	   alterWeight = new JMenuItem("Alter Weight...");
	   
	   addNewFruit = new JMenuItem("Add a New Fruit...");
	   addNewPacman = new JMenuItem("Add a New Pacman...");

	   startMouseListenning();
   }
   
   private void setDefaultMenuForObjects() {
	   
	   popup.add(removeItem);
	   popup.addSeparator();
	   popup.add(alterPosition);
   }
   
   public void setMenuOfMoveableObject() {
	   
	   setDefaultMenuForObjects();
	   
	   popup.add(alterSpeed);
	   popup.add(alterEatingRadius);

   }
   
   public void setMenuOfFruit() {
	   
	   setDefaultMenuForObjects();

	   popup.add(alterWeight);
	   
   }
   
   public void setsomething() {
	   popup = new JPopupMenu();
	   
	   popup.add(addNewFruit);
	   popup.add(addNewPacman);
   }
  

   private void startMouseListenning() {
	   
	   removeItem.addActionListener((ActionEvent e) -> {
		   gameBoard.removeItem(objectThatIsBeingPressed);
	   });
	   
	   alterSpeed.addActionListener((ActionEvent e) -> {
		   gameBoard.getGameAlgo().alterSpeed((MoveableObject)objectThatIsBeingPressed);
	   });
	   
	   
	   alterWeight.addActionListener((ActionEvent e) -> {
		   gameBoard.getGameAlgo().alterWeight((Fruit)objectThatIsBeingPressed);
	   });
	   
	   alterEatingRadius.addActionListener((ActionEvent e) -> {
		   gameBoard.getGameAlgo().alterEatingRadius((MoveableObject)objectThatIsBeingPressed);
	   });   
	   
   }
   
   public void showPopup(MouseEvent me) {
      if(me.isPopupTrigger())
         popup.show(me.getComponent(), me.getX(), me.getY());
   }

}