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
import javax.swing.JPanel;

import Coords.MyCoords;
import FileFormat.CSVReader;
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
import Geom.Point3D;
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
	JFrame panel = new JFrame();
	public Map map;
	protected GameBoard gameboard;
	Player player;
	Images images = new Images();
	private static final long serialVersionUID = -4673139390645816489L;
	private JFrame frame;
	DrawItems drawItems;
	RefreshScreen refreshScreen;
	MouseClick mouse_event_listener = new MouseClick(this);
	
	public Gui_algo() {
		setScreen();
		map = new Map(frame);
		refreshScreen = new RefreshScreen(this);
	
	}

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        refreshScreen.refresh(images, graphics);
    }

	

	public void setScreen() {
		frame = new JFrame("pacman");

	
		frame.setLayout(new BorderLayout());
		frame.addMouseListener(mouse_event_listener);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(1200,600);
	//	frame.setResizable(false);
		frame.add(this);		
	}

	public void test() {

		CSVReader csv = new CSVReader("..");
		List<String> elements = csv.processFile();
		player = new Player(1, new Point3D(32.1040,35.2061), 3);

		 gameboard = new GameBoard(map, elements, "..", player);	
		 drawItems = new DrawItems(map, gameboard);
    

		 updateScreen = true;
		 refreshScreen.start();

		 // 
		 DropingItemsOnScreen dropping = new DropingItemsOnScreen();
		 dropping.startDroppingItems();
		 
		
	}

	public void setGameBoard(GameBoard gameBoard) {
		this.gameboard = gameBoard;
	}

}
