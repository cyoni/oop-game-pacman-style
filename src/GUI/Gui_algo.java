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
import GIS.Map;
import GameObjects.Fruit;
import GameObjects.Ghost;
import GameObjects.MoveableObject;
import GameObjects.Pacman;
import GameObjects.Player;
import GameObjects.Rectangle;
import GameObjects.GameObject;
import algorithms.Line;
import algorithms.Point2D;
import game.DropingItemsOnScreen;
import game.GameBoard;
import game.InitGame;
import mouse.MouseClickOnScreen;
import threads.ManagePacmanThread;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * 
 * @author Yoni
 *
 */

public class Gui_algo extends JPanel {

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
			List<GameObject> fruits = gameboard.getFruits();
			List<GameObject> pacmans = gameboard.getPacmans();
			List<Rectangle> blocks = gameboard.getBlocks();
			List<GameObject> ghost = new ArrayList<>();
			
			if (gameboard.getGhost() != null)
				ghost.add(gameboard.getGhost());

			draw(graphics, ghost, images.getGhost_image());
			draw(graphics, fruits, images.getFruit_image());
			draw(graphics, pacmans, images.getPacman_image());
			draw(graphics, blocks);
			
			if (gameboard.isRunning()) {
				gameboard.updateLabelStatus();
			}
			
			displayStatusLabel(graphics);	
	
			if (gameboard.getPlayer() != null) {
				List<GameObject> list = new ArrayList<>();
				list.add(gameboard.getPlayer());
				draw(graphics, list, images.getPlayer_image());
			}
		}
	}

	private void displayStatusLabel(Graphics graphics) {			
		Graphics2D g2 = (Graphics2D) graphics;
		Font myFont = new Font("Arial", Font.BOLD, 25);
		g2.setColor(Color.green);
		g2.setFont(myFont);
		drawLabel(g2, 200, 30, 0, gameboard.getGameStatus());
	}

	public static void drawLabel(Graphics2D g2d, double x, double y, double angle, String text) {
		 g2d.translate((float) x, (float) y); g2d.rotate(Math.toRadians(angle));
		 g2d.drawString(text, 0, 0); g2d.rotate(-Math.toRadians(angle));
		 g2d.translate(-(float) x, -(float) y);
	}

	public synchronized void paint(Graphics g) {
		super.paint(g);
	}

	private void draw(Graphics graphics, List<Rectangle> blocks) {

		for (int i=0; i<blocks.size(); i++) {
	        graphics.setColor(Color.blue);
	        Point2D p1 = Map.global2pixel(blocks.get(i).getP_up_left());
	        Point2D p2 = Map.global2pixel(blocks.get(i).getP_down_right());
	        double width = Math.abs(p2.x()-p1.x());
	        double height = Math.abs(p2.y()-p1.y());
	        graphics.fillRect((int)p1.x(), (int)p1.y(), (int)width,(int)height);
		}
	}

	private void draw(Graphics graphics, List<GameObject> obj, Image picture) {
		for (int i = 0; i < obj.size(); i++) {
			GameObject item = obj.get(i);
			Point2D point = Map.global2pixel(item.getLocation());
			
		
			graphics.drawImage(picture, (int) (point.x()), (int) (point.y()), item.getObjectSize(),
					item.getObjectSize(), null);
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
		frame.setSize(1200, 600);
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




}
