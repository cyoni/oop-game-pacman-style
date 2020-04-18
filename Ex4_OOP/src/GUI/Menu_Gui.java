package GUI;

import java.awt.BorderLayout;
import java.awt.CheckboxMenuItem;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import FileFormat.CSVReaderAndWriter;
import game.DropingItemsOnScreen;
import game.InitGame;

public class Menu_Gui {
	
    private JMenuItem loadFile, saveToCSV, exit, dropItems, startGame, cleanBoard;
    private JMenuBar menuBar; // Window menu bar
	private Gui_algo gui_algo;
	private JCheckBoxMenuItem  gameGraph;
	private JCheckBoxMenuItem mstPath;
	private boolean show_game_graph;
	private boolean showMSTPath;
	
	
	public Menu_Gui(Gui_algo gui_algo) {
		this.gui_algo = gui_algo;
		setMenu();
		startMouseListener();
	}

	protected void setMenu() {
		menuBar = new JMenuBar(); 
		JMenu menu_File = new JMenu("File"); menu_File.setMnemonic(KeyEvent.VK_F);  
	    JMenu menu_Game = new JMenu("Game"); menu_File.setMnemonic(KeyEvent.VK_G);  
	    JMenu menu_Graph = new JMenu("Graph"); menu_File.setMnemonic(KeyEvent.VK_R);  
        
	    loadFile = new JMenuItem("Load a file...", KeyEvent.VK_O);
	    saveToCSV = new JMenuItem("Save...", KeyEvent.VK_S); 
	    exit = new JMenuItem("Exit", KeyEvent.VK_E);
	        
	    dropItems = new JMenuItem("Drop items...");
	    startGame = new JMenuItem("Start Game", KeyEvent.VK_G);
	    cleanBoard = new JMenuItem("Clean Board");
	    
	    gameGraph = new JCheckBoxMenuItem("Show Game Graph");
	    mstPath = new JCheckBoxMenuItem("Show MST Path");

	    menu_File.add(loadFile); 
	    menu_File.add(saveToCSV);
	    menu_File.addSeparator();
	    menu_File.add(exit); 
	        
	    menu_Game.add(dropItems);
	    menu_Game.addSeparator();
	    menu_Game.add(startGame);
	    menu_Game.add(cleanBoard);

	    menu_Graph.add(gameGraph);
	    menu_Graph.add(mstPath);

	    menuBar.add(menu_File); 
	    menuBar.add(menu_Game);
	    menuBar.add(menu_Graph);
	}

    private void startMouseListener() {
    	
    	gameGraph.addActionListener((ActionEvent e) -> { 
        	show_game_graph = gameGraph.isSelected();
        	gui_algo.repaint();
        });
        
    	mstPath.addActionListener((ActionEvent e) -> { 
    		showMSTPath = mstPath.isSelected();
    		gui_algo.repaint();
        });
    	
        cleanBoard.addActionListener((ActionEvent e) -> { 
        	gui_algo.gameboard.cleanBoard();
        });
        
        startGame.addActionListener((ActionEvent e) -> { 
        	InitGame init = new InitGame(gui_algo);
        	init.startGame();
        });
    	
        dropItems.addActionListener((ActionEvent e) -> { 
        	DropingItemsOnScreen dropping = new DropingItemsOnScreen();
        	dropping.selectToDropAll();
        	dropping.startDroppingItems();
        });
    	
        loadFile.addActionListener((ActionEvent e) -> { 
        	loadFileAndRead();
        });
    	
        saveToCSV.addActionListener((ActionEvent e) -> { 
        	try {
				gui_algo.exportToCSV();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        });
        
        exit.addActionListener((ActionEvent e) -> { 
        	System.exit(0);
        });
    }
	
	private void loadFileAndRead() {
		CSVReaderAndWriter reader = new CSVReaderAndWriter();
		String path = reader.chooseFile();
		List<String> elements = reader.processFile(path);
		gui_algo.initGame.initGameboard(elements);
	}

	public JMenuBar getMenu() {
		return menuBar;
	}
	
	public boolean getIsShow_Game_Graph_Selected() { return show_game_graph;}
	public boolean getIsShow_MST_Graph_Selected() { return showMSTPath;}

}
