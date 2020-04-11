package GUI;

public class RefreshScreen extends Thread {
	
	Gui_algo gui_algo;
	
	public RefreshScreen(Gui_algo gui_algo) {
		
		this.gui_algo = gui_algo;
	}
	
	
	public void run() {
		
		while(true) {
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			gui_algo.repaint();
			
		}
		
		
	}

}
