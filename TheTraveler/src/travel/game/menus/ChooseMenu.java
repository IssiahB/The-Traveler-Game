package travel.game.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import travel.game.GameState;
import travel.game.Loop;
import travel.game.menus.utils.Menu;
import travel.game.menus.utils.MenuManager;
import travel.game.menus.utils.MenuTemplate;
import travel.game.menus.utils.Menus;
import travel.game.worlds.WorldHandler;
import travel.game.worlds.Worlds;

public class ChooseMenu extends MenuTemplate implements Menu {
	
	private BufferedImage image;
	private List<Rectangle> levels = new ArrayList<Rectangle>();
	private List<Color> colors = new ArrayList<Color>();
	private List<Worlds> levelNames = new ArrayList<Worlds>();
	
	private Rectangle back = new Rectangle(50, 430, 100, 40);
	private Color backColor = Color.GRAY;
	
	public ChooseMenu(BufferedImage image) {
		this.image = image;
		addLevel(10, 10, 100, 100, Worlds.world1);
		addLevel(120, 10, 100, 100, Worlds.world2);
		addLevel(230, 10, 100, 100, Worlds.world3);
		addLevel(340, 10, 100, 100, Worlds.world4);
		addLevel(450, 10, 100, 100, Worlds.world5);
		addLevel(560, 10, 100, 100, Worlds.world6);
		addLevel(10, 120, 100, 100, Worlds.world7);
		addLevel(120, 120, 100, 100, Worlds.world8);
		addLevel(230, 120, 100, 100, Worlds.world9);
		addLevel(340, 120, 100, 100, Worlds.world10);
	}
	
	private void addLevel(int x, int y, int width, int height,
			Worlds world) {
		levels.add(new Rectangle(x, y, width, height));
		colors.add(Color.gray);
		levelNames.add(world);
	}
	
	@Override
	public void update(int x, int y, boolean pressed,
			boolean released) {
		
		if(inRect(x, y, back) && pressed)
			backColor = Color.GRAY.darker();
		else
			if(inRect(x, y, back) && released) {
				backColor = Color.GRAY;
				MenuManager.setCurrentMenu(Menus.main);
			}
		
		for (int i = 0; i < levels.size(); i++) {
			if (pressed && inRect(x, y, levels.get(i))) {
				colors.remove(i);
				colors.add(i, Color.gray.darker());
			}
			
			if (released && inRect(x, y, levels.get(i))) {
				colors.remove(i);
				colors.add(i, Color.gray);
				WorldHandler.loadWorld(levelNames.get(i));
				Loop.setCurrentState(GameState.game);
			}
		}
		
	}
	
	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g.drawImage(image, 0, 0, Loop.getWidth(), Loop.getHeight(),
				null);
		
		drawButton(g2, back, backColor);
		
		g2.setFont(new Font("Arial Bold", 20, 20));
		
		for (int i = 0; i < levels.size(); i++) {
			g2.setColor(colors.get(i));
			g2.fill(levels.get(i));
			g2.setColor(Color.white);
			
			// realigns the letters if length is bigger
			if(levelNames.get(i).toString().length() == 6) {
				g2.drawString(levelNames.get(i).toString(),
						((levels.get(i).x + levels.get(i).width / 2) - 30),
						(levels.get(i).y + levels.get(i).height / 2));
			} else
				if(levelNames.get(i).toString().length() == 7) {
					g2.drawString(levelNames.get(i).toString(),
							((levels.get(i).x + levels.get(i).width / 2) - 35),
							(levels.get(i).y + levels.get(i).height / 2));
				}
		}
		
		g2.drawString("Back", (back.x + 30), (back.y + 25));
	}
	
}
