package GUI;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Coords.MyCoords;
import FileFormat.CSVReaderAndWriter;
import GIS.Background;
import GIS.LineIntersect;
import GIS.Map;
import GameObjects.Fruit;
import GameObjects.Ghost;
import GameObjects.Line;
import GameObjects.Pacman;
import GameObjects.Player;
import GameObjects.Rectangle;
import GameObjects.game_object;
import Geom.Point2D;
import game.DropingItemsOnScreen;
import game.GameBoard;
import game.GameManager;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Gui_algo extends JPanel  {

	/**
	 * 
	 */
	boolean updateScreen = false;
	protected JFrame jframe = new JFrame();
	public Map map;
	protected GameBoard gameboard;
	Player player;
	Images images = new Images();
	private static final long serialVersionUID = -4673139390645816489L;
	private JFrame frame;
	DrawItems drawItems;
	RefreshScreen refreshScreen = new RefreshScreen(this);
	MouseClickOnScreen mouse_event_listener = new MouseClickOnScreen(this);
    private boolean refereshScreen = false;

	public Gui_algo() {
		setScreen();
		map = new Map(frame);
		game_object.map = map;
		
	}

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
    	graphics.drawImage(images.getBackground(), 0, 0, getWidth(), getHeight(), this); // background
   
    	if (updateScreen) {    	
	    	List<game_object> fruits = gameboard.getFruits();
	    	draw(graphics, fruits, images.getFruit_image());
	    	List<game_object> pacmans = gameboard.getPacmans();
	    	draw(graphics, pacmans, images.getPacman_image());
	    	List<game_object> ghosts = gameboard.getGhosts();
	    	draw(graphics, ghosts, images.getGhost_image());
	    	
	    	if (gameboard.getPlayer() != null) {
	    		List<game_object> list = new ArrayList<>();
	    		list.add(gameboard.getPlayer());
	    		draw(graphics, list, images.getPlayer_image());}
			
    	}
    }

	private void draw(Graphics graphics, List<game_object> obj, Image picture) {
		for (int i=0; i<obj.size(); i++) {
			game_object item = obj.get(i);
			graphics.drawImage(picture, (int) ( item.getLocation().x()), (int) (item.getLocation().y()), 23, 23, null);
		}		
	}

	public void setScreen() {
		Menu_Gui menu = new Menu_Gui(this);
		frame = new JFrame("pacman");
		frame.setLayout(new BorderLayout());
		frame.setJMenuBar(menu.getMenu());
		frame.addMouseListener(mouse_event_listener);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(1200,600);
		frame.setResizable(false);
		frame.add(this);	
		frame.setLocationRelativeTo(null);
	}
	
	public void test() throws Exception {

		CSVReaderAndWriter csv = new CSVReaderAndWriter("..");
		List<String> elements = csv.processFile();
		//player = new Player(1, map, new Point2D(35.208207,32.10538), 3);

		 gameboard = new GameBoard(map);	
		// drawItems = new DrawItems(map, gameboard);

		 
		 updateScreen = true;
		// refreshScreen.start();
		// refereshScreen = true;
		 
	//	 GameManager gameManager = new GameManager(gameboard);
	//	 gameManager.startGame();
		 
		// DropingItemsOnScreen dropping = new DropingItemsOnScreen();
		// dropping.startDroppingItems();
		 
		
	}

	public void setGameBoard(GameBoard gameBoard) {
		this.gameboard = gameBoard;
	}

	public void exportToCSV() throws IOException {
		CSVReaderAndWriter csvWriter = new CSVReaderAndWriter();
		String file_name = csvWriter.chooseFolder();
		csvWriter.writeToCSV(gameboard, file_name);
		
	}

}
