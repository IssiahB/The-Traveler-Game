package travel.game.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import travel.game.GameState;
import travel.game.Loop;
import travel.game.menus.utils.Menu;
import travel.game.menus.utils.MenuManager;
import travel.game.menus.utils.Menus;
import travel.game.worlds.WorldHandler;
import travel.game.worlds.Worlds;

public class MainMenu implements Menu {
	
	private BufferedImage image;
	
	private Rectangle play = new Rectangle(Loop.getWidth()/2-100, Loop.getHeight()/2-150, 200, 50);
	private Rectangle createWorld = new Rectangle(Loop.getWidth()/2-100, Loop.getHeight()/2-50, 200, 50);
	private Rectangle settings = new Rectangle(Loop.getWidth()/2-100, Loop.getHeight()/2+50, 200, 50);
	private Rectangle exit = new Rectangle(Loop.getWidth()/2-100, Loop.getHeight()/2+150, 200, 50);
	
	private Color playColor = Color.GRAY;
	private Color createColor = Color.GRAY;
	private Color setColor = Color.GRAY;
	private Color exitColor = Color.GRAY;
	private Font font = new Font("TimesNewRoman", 3, 40);
	private Font titleFont = new Font("TimesNewRoman", 2, 70);
	
	public MainMenu(BufferedImage image) {
		this.image = image;
	}
	
	private void drawButton(Graphics2D g2, Rectangle rect, Color color) {
		g2.setColor(color);
		g2.fill(rect);
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
		if(inRect(x, y, play) && pressed) {
			playColor = Color.gray.darker();
		} else 
			if(inRect(x, y, play) && released) {
				playColor = Color.GRAY;
				MenuManager.setCurrentMenu(Menus.choose);
			}
		
		if(inRect(x, y, createWorld) && pressed) {
			createColor = Color.gray.darker();
		} else 
			if(inRect(x, y, createWorld) && released) {
				createColor = Color.GRAY;
				MenuManager.setCurrentMenu(Menus.create);
			}
		
		if(inRect(x, y, settings) && pressed) {
			setColor = Color.gray.darker();
		} else 
			if(inRect(x, y, settings) && released) {
				setColor = Color.GRAY;
				MenuManager.setCurrentMenu(Menus.setting);
			}
		
		if(inRect(x, y, exit) && pressed) {
			exitColor = Color.gray.darker();
		} else 
			if(inRect(x, y, exit) && released) {
				exitColor = Color.GRAY;
			}
	}
	
	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g.drawImage(image, 0, 0, Loop.getWidth(), Loop.getHeight(), null);
		
		g.setFont(titleFont);
		g.setColor(Color.white);
		g.drawString("The Traveler", Loop.getWidth()/3-70, 50);
		
		drawButton(g2, play, playColor);
		drawButton(g2, createWorld, createColor);
		drawButton(g2, settings, setColor);
		drawButton(g2, exit, exitColor);
		
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString("Play", (int) (play.getX() + 55), (int) (play.getY() + 35));
		g.drawString("Create", (int) (createWorld.getX() + 40), (int) (createWorld.getY() + 35));
		g.drawString("Settings", (int) (settings.getX() + 25), (int) (settings.getY() + 35));
		g.drawString("Exit", (int) (exit.getX() + 55), (int) (exit.getY() + 35));
	}
}
