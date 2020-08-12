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
import game.GameBoard;
import game.InitGame;

public class Menu_Gui {
	
    private JMenuItem loadFile, saveToCSV, exit, startGame, cleanBoard, stopGame, generateGame;
    private JMenuBar menuBar; 
	private Gui_algo gui_algo;
	
	public Menu_Gui(Gui_algo gui_algo) {
		this.gui_algo = gui_algo;
		setMenu();
		startMouseListener();
	}

	protected void setMenu() {
		menuBar = new JMenuBar(); 
		JMenu menu_File = new JMenu("File");
	    JMenu menu_Game = new JMenu("Game");
	    
        
	    loadFile = new JMenuItem("Load a game file...", KeyEvent.VK_O);
	    saveToCSV = new JMenuItem("Export to a file...", KeyEvent.VK_S); 
	    exit = new JMenuItem("Exit", KeyEvent.VK_E);
	        
	    startGame = new JMenuItem("Start Game", KeyEvent.VK_G);
	    cleanBoard = new JMenuItem("Clean Board");
	    stopGame = new JMenuItem("Stop Game");
	    generateGame = new JMenuItem("Generate New Game");
	    

	    menu_File.add(loadFile); 
	    menu_File.add(saveToCSV);
	    menu_File.addSeparator();
	    menu_File.add(exit); 
	        

	    menu_Game.add(startGame);
	    menu_Game.addSeparator();
	    menu_Game.add(stopGame);
	    menu_Game.addSeparator();
	    
	    menu_Game.add(generateGame);

	    menu_Game.add(cleanBoard);


	    menuBar.add(menu_File); 
	    menuBar.add(menu_Game);
	}

    private void startMouseListener() {
    
    	generateGame.addActionListener((ActionEvent e) -> { 
    		boolean state = GameBoard.isGenerateGame();
    		if (state) 
    			gui_algo.gameboard.cleanBoard();
    		GameBoard.setGenerateGame(!state);
        });
    	
        cleanBoard.addActionListener((ActionEvent e) -> { 
        	gui_algo.gameboard.cleanBoard();
        });
        
        startGame.addActionListener((ActionEvent e) -> { 
        	InitGame init = new InitGame(gui_algo);
        	init.startGame();
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


}
