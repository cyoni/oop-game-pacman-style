package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
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
import GameObjects.MoveableObject;
import GameObjects.Pacman;
import GameObjects.Player;
import GameObjects.Rectangle;
import Threads_Game.ManagePacmanThread;
import GameObjects.Game_object;
import algorithms.Line;
import algorithms.Point2D;
import game.DropingItemsOnScreen;
import game.GameBoard;
import game.InitGame;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *  
 * @author Yoni
 *
 */
public class Gui_algo extends JPanel  {

	boolean updateScreen = false;
	protected JFrame jframe = new JFrame();
	public Map map;
	public GameBoard gameboard = new GameBoard(this);
	Images images = new Images();
	private static final long serialVersionUID = -4673139390645816489L;
	private JFrame frame;
	RefreshScreen refreshScreen = new RefreshScreen(this);
	MouseClickOnScreen mouse_event_listener = new MouseClickOnScreen(this);
	protected InitGame initGame = new InitGame(this);
	Menu_Gui menu = new Menu_Gui(this);
	
	public Gui_algo() {
		setScreen();
		map = new Map(frame);
	}

    @Override
    public synchronized void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
    	graphics.drawImage(images.getBackground(), 0, 0, Screen.WIDTH, Screen.HEIGHT, this); // set background
   
    	if (gameboard != null) {
	    	List<Game_object> fruits = gameboard.getFruits();
	    	draw(graphics, fruits, images.getFruit_image());
	    	List<Game_object> pacmans = gameboard.getPacmans();
	    	draw(graphics, pacmans, images.getPacman_image());
	    	List<Game_object> ghosts = gameboard.getGhosts();
	    	draw(graphics, ghosts, images.getGhost_image());
	    	
	    	if (gameboard.getPlayer() != null) {
	    		List<Game_object> list = new ArrayList<>();
	    		list.add(gameboard.getPlayer());
	    		draw(graphics, list, images.getPlayer_image());
	    	}
    	}
    }

    
    public synchronized void paint(Graphics g) {
        super.paint(g); 
        Graphics2D g2 = (Graphics2D) g;
        
        if (menu.getIsShow_Game_Graph_Selected()) {
        	List<Line> lines = gameboard.getLinesOfGameGraph();
	        for (int i=0; i < lines.size(); i++) {
	            Line currentLine = lines.get(i);
	            Line2D lin = new Line2D.Double(currentLine.getP1().x(), currentLine.getP1().y(), currentLine.getP2().x(), currentLine.getP2().y());
	            g2.setColor(Color.yellow);
	            g2.draw(lin);
	        }
        } 
        
	    if (menu.getIsShow_MST_Graph_Selected()) {
	    	List<Line> lines = gameboard.getMST_Game();
		    for (int i=0; i<lines.size(); i++) {
		        Line currentLine = lines.get(i);
		        Line2D lin = new Line2D.Double(currentLine.getP1().x(), currentLine.getP1().y(), currentLine.getP2().x(), currentLine.getP2().y());
		        g2.setColor(Color.GREEN);
		        g2.draw(lin);
	        }
	    } 
    }

	private void draw(Graphics graphics, List<Game_object> obj, Image picture) {
		for (int i=0; i<obj.size(); i++) {
			Game_object item = obj.get(i);
			Point2D point = map.global2pixel(item.getLocation());
			graphics.drawImage(picture, (int) (point.x()), (int) (point.y()), item.getObjectSize(), item.getObjectSize(), null);
		}		
	}
	
	public GameBoard getGameboard() {
		return this.gameboard;
	}

	public void setScreen() {
		frame = new JFrame("Pacman");
		frame.setLayout(new BorderLayout());
		frame.addMouseListener(mouse_event_listener);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(1200,600);
		frame.setJMenuBar(menu.getMenu());
		frame.setResizable(false);
		frame.add(this);	
		frame.setLocationRelativeTo(null);
	}
	

	public void setGameBoard(GameBoard gameBoard) {
		this.gameboard = gameBoard;
		this.repaint();
	}

	public void exportToCSV() throws IOException {
		CSVReaderAndWriter csvWriter = new CSVReaderAndWriter();
		String file_name = csvWriter.chooseFile();
		csvWriter.writeToCSV(gameboard, file_name);
		
	}

	public void startBackgroundAnimation() {
		AnimatedBackground animation = new AnimatedBackground(this);
		animation.start();
		
	}

	public void buildGraph() {
		initGame.buildGraphGame();
		repaint();
	}



}
