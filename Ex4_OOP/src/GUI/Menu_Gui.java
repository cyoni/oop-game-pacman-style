package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import game.DropingItemsOnScreen;

public class Menu_Gui {
	
    private JMenuItem loadFile, exportToCSV, exit, dropItems, startGame;
    private JMenuBar menubar; // Window menu bar
	private Gui_algo gui_algo;
	
	
	public Menu_Gui(Gui_algo gui_algo) {
		this.gui_algo = gui_algo;
		setMenu();
		startMouseListener();
	}

	protected void setMenu() {
		menubar = new JMenuBar(); 
		JMenu menu_File = new JMenu("File"); 
	    JMenu menu_Game = new JMenu("Game"); 
        
	    loadFile = new JMenuItem("Load a file...");
	    exportToCSV = new JMenuItem("Export to CSV..."); 
	    exit = new JMenuItem("Exit");
	        
	    dropItems = new JMenuItem("Drop items...");
	    startGame = new JMenuItem("Start Game");
	        
	    // add menu items to menu 
	    menu_File.add(loadFile); 
	    menu_File.add(exportToCSV);
	    menu_File.addSeparator();
	    menu_File.add(exit); //exit
	        
	    menu_Game.add(dropItems);
	    menu_Game.addSeparator();
	    menu_Game.add(startGame);

	    // add menu to menu bar 
	    menubar.add(menu_File); 
	    menubar.add(menu_Game);
	        
	}

    private void startMouseListener() {
    	
    	
        startGame.addActionListener((ActionEvent e) -> { 
        	
        });
    	
        dropItems.addActionListener((ActionEvent e) -> { 
        	DropingItemsOnScreen dropping = new DropingItemsOnScreen();
        	dropping.startDroppingItems();
        });
    	
        loadFile.addActionListener((ActionEvent e) -> { 
        	
        });
    	
        exportToCSV.addActionListener((ActionEvent e) -> { 
        	try {
				gui_algo.exportToCSV();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
        
        exit.addActionListener((ActionEvent e) -> { 
        	System.exit(0);
        });
    }
	
	public JMenuBar getMenu() {
		return menubar;
	}

	
	
}
