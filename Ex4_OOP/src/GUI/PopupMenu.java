package GUI;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


public class PopupMenu {
   private JPopupMenu popup;
   JMenuItem removeItem, alterWeight, alterPosition;
   
   public PopupMenu() {
	   setMenu();
	   startMouseListenning();
   }
   
   private void setMenu() {

	      popup = new JPopupMenu();

	      removeItem = new JMenuItem("Remove");
	      
	      alterWeight = new JMenuItem("Alter Weight...");
	      alterPosition = new JMenuItem("Alter Position...");
	      
	      popup.add(removeItem);
	      popup.addSeparator();
	      popup.add(alterWeight);
	      popup.add(alterPosition);

}

   private void startMouseListenning() {
	   
	   removeItem.addActionListener((ActionEvent e) -> {
		   System.out.println("hi");
	   });
	   
	   
   }
   
   void showPopup(MouseEvent me) {
      if(me.isPopupTrigger())
         popup.show(me.getComponent(), me.getX(), me.getY());
   }

}