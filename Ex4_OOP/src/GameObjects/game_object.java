package GameObjects;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GIS.Map;
import Geom.Point3D;
import game.GameBoard;

public class game_object {
	protected String picture;
	protected GameBoard game_metadata;
	int id = -1;
	private Point3D location;

	public game_object(GameBoard gameBoard, Map map, String picture, int id, Point3D location) {
		this.game_metadata = gameBoard;
		this.picture = picture;
		this.id = id;
		this.location = new Point3D(map.coordsToPixel(location.x(), location.y()));
	}
	
	public Image getPicture() {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(picture));
		} catch (IOException e) {
			e.printStackTrace();
		};
		
		return image;
	}
	
	public Point3D getLocation() {
		return location;
	}

	public int getId() {
		return id;
	}


}
