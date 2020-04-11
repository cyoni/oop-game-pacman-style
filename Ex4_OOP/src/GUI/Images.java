package GUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GIS.Background;
import GameObjects.Fruit;
import GameObjects.Ghost;
import GameObjects.Pacman;
import GameObjects.Player;

public class Images {
	
	private BufferedImage background;
	private BufferedImage pacman_image;
	private BufferedImage ghost_image;
	private BufferedImage player_image;
	private BufferedImage fruit_image;
	
	
		public Images() {
			setImages();
		}
	
		public void setImages() {
			background = loadImages(Background.image);
		    pacman_image = loadImages(Pacman.picture);
		    ghost_image = loadImages(Ghost.picture);
		    player_image = loadImages(Player.picture);
		    fruit_image = loadImages(Fruit.picture);	
		}
	

		private BufferedImage loadImages(String image_str) {
			BufferedImage image = null;
			try {
				image = ImageIO.read(new File(image_str));
			} catch (IOException e) {
				e.printStackTrace();
			};
			return image;
		}
		
		

		public BufferedImage getBackground() {
			return background;
		}

		public void setBackground(BufferedImage background) {
			this.background = background;
		}

		public BufferedImage getPacman_image() {
			return pacman_image;
		}

		public void setPacman_image(BufferedImage pacman_image) {
			this.pacman_image = pacman_image;
		}

		public BufferedImage getGhost_image() {
			return ghost_image;
		}

		public void setGhost_image(BufferedImage ghost_image) {
			this.ghost_image = ghost_image;
		}

		public BufferedImage getPlayer_image() {
			return player_image;
		}

		public void setPlayer_image(BufferedImage player_image) {
			this.player_image = player_image;
		}

		public BufferedImage getFruit_image() {
			return fruit_image;
		}

		public void setFruit_image(BufferedImage fruit_image) {
			this.fruit_image = fruit_image;
		}


	
	
}
