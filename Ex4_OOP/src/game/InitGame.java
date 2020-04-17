package game;

import java.util.List;

import GUI.Gui_algo;
import GameObjects.Fruit;
import GameObjects.Ghost;
import GameObjects.Pacman;
import GameObjects.game_object;
import Geom.Point2D;

public class InitGame {

	private GameBoard gameboard;
	private Gui_algo gui_algo;
	
	public InitGame(Gui_algo gui_algo) {
		this.gui_algo = gui_algo;
		gameboard = new GameBoard(gui_algo);
	}
	
	public void initGameboard(List<String> elements) {
		for (int i=0; i< elements.size(); i++) {
			String current_element = elements.get(i);
			String data[] = current_element.split(",");
			String type = data[0];
			int id =  Integer.parseInt(data[1]);
			double lat = Double.parseDouble(data[2]);
			double lon =  Double.parseDouble(data[3]);
			System.out.println(elements.get(i));
			if (type.equals("F")) {
				gameboard.addFruit(new Fruit(game_object.totalObjects++, new Point2D(lon, lat), Double.parseDouble(data[5])));}
			else if (type.equals("G"))
				gameboard.addGhost(new Ghost(id, new Point2D(lat, lon), Double.parseDouble(data[5])));
			else if (type.equals("P"))
				gameboard.addPacman(new Pacman(game_object.totalObjects++, new Point2D(lat, lon), Double.parseDouble(data[6])));			
		}
		gui_algo.setGameBoard(gameboard);
	}
	
}
