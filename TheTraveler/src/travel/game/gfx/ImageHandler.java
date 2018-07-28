package travel.game.gfx;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageHandler {
	
	public static BufferedImage getImage(String path) {
		BufferedImage temp = null;
		try {
			temp = ImageIO.read(new File(path));
		}catch (Exception e) {
			System.err.println(path+" could not be read");
			e.printStackTrace();
			return null;
		}
		
		return temp;
	}
}
