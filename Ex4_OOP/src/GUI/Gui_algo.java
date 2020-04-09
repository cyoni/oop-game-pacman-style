package GUI;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import FileFormat.CSVReader;

import GIS.Map;
import GameObjects.game_object;
import Geom.Point3D;
import game.GameBoard;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Gui_algo extends JPanel implements MouseListener  {

	/**
	 * 
	 */
	boolean updateScreen = false;
	 JPanel panel;
	public Map map;
	protected GameBoard gameboard;
	 
	private static final long serialVersionUID = -4673139390645816489L;

	private JFrame frame;
	private DrawItems drawItems;
	
	public Gui_algo() {
		setScreen();
		map = new Map(frame);
		drawItems = new DrawItems(map, gameboard);
		updateScreen = true;
	}

	Image x=null,y = null;
	
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
		try {
			x = ImageIO.read(new File("Ariel1.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		graphics.drawImage(x, 0, 0, this.getWidth(), this.getHeight(), null); // background 
        
        
        if (updateScreen) {
     
        	drawItems.drawObjects(graphics, gameboard.getFruits());
        	drawItems.drawObjects(graphics, gameboard.getGhosts());
        	drawItems.drawObjects(graphics, gameboard.getPacmans());
        	drawItems.drawBlocks(graphics, gameboard.getBlocks());
        	
        }
       
        // iterator to draw stuff
   //     Point3D xx = new Point3D(32.102514, 35.207410);
    //    Point3D abc = map.coordsToPixel(xx.x(), xx.y());
        
     //   G.drawImage(y, (int) (abc.x()  ), (int) (abc.y() ), 23,	23, null);
     //   System.out.println(abc.x() + "," + abc.y());
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		Gui_dialog.alert(e.getX() + "," + e.getY());

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void setScreen() {
		frame = new JFrame("pacman");

	
		frame.setLayout(new BorderLayout());
		frame.addMouseListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(1433, 642);
		frame.add(this);
		


	}

	public void test() {

		String file_path = "C:\\Users\\Yoni\\git\\oop_game_1\\Ex4_OOP\\data\\Ex4_OOP_example9.csv"; 
		CSVReader csv = new CSVReader(this, file_path);
		csv.processFile();
	}

	public void setGameBoard(GameBoard gameBoard) {
		this.gameboard = gameBoard;
	}

}
