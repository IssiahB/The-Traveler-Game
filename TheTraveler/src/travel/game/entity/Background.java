package travel.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Background extends Entity {
	
	public Background(int x, int y, int width, int height,
			BufferedImage image) {
		super(x, y, width, height);
		this.image = image;
	}
	
	@Override
	public void update(EntityHandler entities) {
		
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.gray.brighter());
		
		if (image == null)
			g.fillRect(x, y, width, height);
		else
			g.drawImage(image, x, y, width, height, null);
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
}
