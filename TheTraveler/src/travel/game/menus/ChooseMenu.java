package travel.game.menus;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import travel.game.Loop;
import travel.game.menus.utils.Menu;

public class ChooseMenu implements Menu {
	
	private BufferedImage image;
	private List<Rectangle> levels = new ArrayList<Rectangle>();
	
	
	public ChooseMenu(BufferedImage image) {
		this.image = image;
	}
	
	private void addLevel() {
		
	}
	
	private boolean inRect(int x, int y, Rectangle rect) {
		y = y + 25;
		
		if(x > rect.getX() && x < (rect.getX() + rect.getWidth())) {
			if(y > rect.getY() && y < (rect.getY() + rect.getHeight())) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public void update(int x, int y, boolean pressed, boolean released) {
		
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g.drawImage(image, 0, 0, Loop.getWidth(), Loop.getHeight(), null);
		
		
	}

}
