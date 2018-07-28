package travel.game.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import travel.game.Loop;
import travel.game.menus.utils.Menu;
import travel.game.menus.utils.MenuManager;
import travel.game.menus.utils.MenuTemplate;
import travel.game.menus.utils.Menus;

public class MainMenu extends MenuTemplate implements Menu {
	
	private BufferedImage image;
	
	private Rectangle play = new Rectangle(Loop.getWidth()/2-100, Loop.getHeight()/2-150, 200, 50);
	private Rectangle createWorld = new Rectangle(Loop.getWidth()/2-100, Loop.getHeight()/2-50, 200, 50);
	private Rectangle settings = new Rectangle(Loop.getWidth()/2-100, Loop.getHeight()/2+50, 200, 50);
	private Rectangle exit = new Rectangle(Loop.getWidth()/2-100, Loop.getHeight()/2+150, 200, 50);
	
	private Color playColor = Color.GRAY;
	private Color createColor = Color.GRAY;
	private Color setColor = Color.GRAY;
	private Color exitColor = Color.GRAY;
	private Font titleFont = new Font("TimesNewRoman", 2, 70);
	
	public MainMenu(BufferedImage image) {
		this.image = image;
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
				System.exit(0);
			}
	}
	
	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		// draw Background image
		g.drawImage(image, 0, 0, Loop.getWidth(), Loop.getHeight(), null);
		
		// draw Title
		g.setFont(titleFont);
		g.setColor(Color.white);
		g.drawString("The Traveler", Loop.getWidth()/3-70, 50);
		
		// draw buttons
		drawButton(g2, play, playColor);
		drawButton(g2, createWorld, createColor);
		drawButton(g2, settings, setColor);
		drawButton(g2, exit, exitColor);
		
		// draw button labels
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString("Play", (int) (play.getX() + 55), (int) (play.getY() + 35));
		g.drawString("Create", (int) (createWorld.getX() + 40), (int) (createWorld.getY() + 35));
		g.drawString("Settings", (int) (settings.getX() + 25), (int) (settings.getY() + 35));
		g.drawString("Exit", (int) (exit.getX() + 55), (int) (exit.getY() + 35));
	}
}
