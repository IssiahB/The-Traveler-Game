package travel.game.menus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import travel.game.GameState;
import travel.game.Loop;
import travel.game.menus.utils.Menu;
import travel.game.worlds.WorldHandler;
import travel.game.worlds.Worlds;

public class ChooseMenu implements Menu {
	
	private BufferedImage image;
	private List<Rectangle> levels = new ArrayList<Rectangle>();
	private List<Color> colors = new ArrayList<Color>();
	private List<String> levelNames = new ArrayList<String>();
	
	public ChooseMenu(BufferedImage image) {
		this.image = image;
		addLevel(10, 10, 100, 100, "Level1");
		addLevel(120, 10, 100, 100, "Level2");
	}
	
	private void addLevel(int x, int y, int width, int height,
			String name) {
		levels.add(new Rectangle(x, y, width, height));
		colors.add(Color.gray);
		levelNames.add(name);
	}
	
	private boolean inRect(int x, int y, Rectangle rect) {
		y = y + 25;
		
		if (x > rect.getX() && x < (rect.getX() + rect.getWidth())) {
			if (y > rect.getY()
					&& y < (rect.getY() + rect.getHeight())) {
				return true;
			}
		}
		
		return false;
	}
	
	private Worlds getChoosenWorld(String name) {
		switch (name) {
			case "Level1":
				return Worlds.world1;
			case "Level2":
				return Worlds.world2;
			default:
				return Worlds.world1;
		}
	}
	
	@Override
	public void update(int x, int y, boolean pressed,
			boolean released) {
		
		for (int i = 0; i < levels.size(); i++) {
			if (pressed && inRect(x, y, levels.get(i))) {
				colors.remove(i);
				colors.add(i, Color.gray.darker());
			}
			
			if (released && inRect(x, y, levels.get(i))) {
				colors.remove(i);
				colors.add(i, Color.gray);
				WorldHandler.clearWorld();
				WorldHandler.loadWorld(
						getChoosenWorld(levelNames.get(i)));
				Loop.setCurrentState(GameState.game);
			}
		}
		
	}
	
	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g.drawImage(image, 0, 0, Loop.getWidth(), Loop.getHeight(),
				null);
		
		for (int i = 0; i < levels.size(); i++) {
			g2.setColor(colors.get(i));
			g2.fill(levels.get(i));
			g2.setColor(Color.white);
			g2.drawString(levelNames.get(i),
					((levels.get(i).x + levels.get(i).width / 2)
							- (levelNames.get(i).length() / 2) - 3),
					(levels.get(i).y + levels.get(i).height / 2));
		}
	}
	
}
