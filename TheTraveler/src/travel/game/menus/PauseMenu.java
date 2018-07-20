package travel.game.menus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import travel.game.GameState;
import travel.game.Loop;
import travel.game.menus.utils.Menu;
import travel.game.menus.utils.MenuManager;
import travel.game.menus.utils.MenuTemplate;
import travel.game.menus.utils.Menus;
import travel.game.util.ImageHandler;
import travel.game.worlds.WorldHandler;
import travel.game.worlds.Worlds;;

public class PauseMenu extends MenuTemplate implements Menu {
	
	private BufferedImage image = ImageHandler.getImage("res/textures/Player.png");
	
	private Rectangle resume = new Rectangle((Loop.getWidth()/2-100), 100, 200, 50);
	private Rectangle restart = new Rectangle((Loop.getWidth()/2-100), 200, 200, 50);
	
	
	private Color resumeColor = Color.GRAY;
	private Color restartColor = Color.GRAY;

	@Override
	public void update(int x, int y, boolean pressed,
			boolean released) {
		
		if(inRect(x, y, resume) && pressed) 
			resumeColor = Color.GRAY.darker();
		else 
			if(inRect(x, y, resume) && released) {
				resumeColor = Color.GRAY;
				MenuManager.setCurrentMenu(Menus.NOT);
				Loop.setCurrentState(GameState.game);
			}
		
		if(inRect(x, y, restart) && pressed) 
			restartColor = Color.GRAY.darker();
		else
			if(inRect(x, y, restart) && released) {
				restartColor = Color.GRAY;
				MenuManager.setCurrentMenu(Menus.NOT);
				Loop.setCurrentState(GameState.game);
				Worlds world = WorldHandler.getPickedWorld();
				WorldHandler.clearWorld();
				WorldHandler.loadWorld(world);
				
			}
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(image, 0, 0, Loop.getWidth(), Loop.getHeight(), null);
		
		drawButton(g2, resume, resumeColor);
		drawButton(g2, restart, restartColor);
		
		g.setFont(font);
		g.setColor(Color.white);
		g2.drawString("Resume", (resume.x + 30), (resume.y + 35));
		g2.drawString("Restart", (restart.x + 25), (restart.y + 35));
	}
	
}
