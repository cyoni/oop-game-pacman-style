package GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class MouseClick implements MouseListener {

	
	private Gui_algo gui_algo;

	public MouseClick(Gui_algo gui_algo) {
		this.gui_algo = gui_algo;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		System.out.println(e.getX() + "," + e.getY());
		
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

}
