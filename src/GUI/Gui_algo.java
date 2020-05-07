package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

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
import mouse.MouseClickOnScreen;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *  TODO:
 *  implement TSP algo with targets. Input targets, output a path.
 *  fix small bugs
 *  add obstacles
 *  add a statics line in the bottom
 *  auto play
 *  save results in local SQL. (enter your name.. enter your 3 last digits of your id.. )
 * @author Yoni
 *
 */
  
//https://github.com/search?q=public+List%3Cnode_data%3E+TSP%28List%3CInteger%3E&type=Code
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
    public static void drawLabel(Graphics2D g2d, double x, double y, double angle, String text) 
    {    
        g2d.translate((float)x,(float)y);
        g2d.rotate(Math.toRadians(angle));
        g2d.drawString(text,0,0);
        g2d.rotate(-Math.toRadians(angle));
        g2d.translate(-(float)x,-(float)y);
    }  
    
    public synchronized void paint(Graphics g) {
        super.paint(g); 
       
        drawGraph(g);
	    drawBottomLabel(g);

    }

	private void drawBottomLabel(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Font myFont = new Font("Arial", Font.BOLD, 25);    
        g2.setColor(Color.green);
        g2.setFont(myFont);
        drawLabel(g2, (Screen.WIDTH/2)-100 , Screen.HEIGHT-20 , 0, Screen.getScreenLabel());  
	}

	private void drawGraph(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (GameBoard.show_game_graph) {
        	List<Line> lines = gameboard.getLinesOfGameGraph();

        	Screen.resetDistance();
	        for (int i=0; i < lines.size(); i++) {
	            Line currentLine = lines.get(i);
		        drawDistanceLabels(g, currentLine);
	            Line2D lin = new Line2D.Double(currentLine.getP1().x(), currentLine.getP1().y(), currentLine.getP2().x(), currentLine.getP2().y());
	            g2.setColor(Color.yellow);
	            g2.draw(lin);
	        }
        } 
        
	    if (GameBoard.showShortestPath) {
	    	Screen.resetDistance();
	    	List<Line> lines = gameboard.getMST_Game();
		    for (int i=0; i<lines.size(); i++) {
		        Line currentLine = lines.get(i);
		        drawDistanceLabels(g, currentLine);
		        Line2D lin = new Line2D.Double(currentLine.getP1().x(), currentLine.getP1().y(), currentLine.getP2().x(), currentLine.getP2().y());
		        g2.setColor(Color.GREEN);
		        g2.draw(lin);
	        }
		    
	    }		
	}

	private void drawDistanceLabels(Graphics g, Line line) {
        Graphics2D g2 = (Graphics2D) g;
        Point2D p1 = line.getP1();
        Point2D p2 = line.getP2();
        double distance = Line.distance(map.pixel2global(p1), map.pixel2global(p2)); // IMPORTANT: to optimize!!!!!!!!!!!!!!!!
        distance = Double.parseDouble(new DecimalFormat("##.#").format(distance));
        double y1 = (int) p1.y();
        double x1 = (int) p1.x();
        double y2 = (int) p2.y();
        double x2 = (int) p2.x();
        double rad = Math.atan2(y1-y2, x1-x2);
        double degrees = Math.toDegrees(rad);
            
        if (x2 > x1) degrees = 180-degrees;
        
        Font myFont = new Font("Arial", Font.BOLD, 14);    
        g2.setColor(Color.WHITE);
        g2.setFont(myFont);
        Screen.sumDistance(distance);
        drawLabel(g2, (x1+x2)/2, (y1+y2)/2, degrees, distance+"");  
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
		frame.addMouseMotionListener(mouse_event_listener);
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
