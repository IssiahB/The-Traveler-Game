package travel.game.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import travel.game.GameState;
import travel.game.Loop;
import travel.game.menus.utils.Menu;
import travel.game.menus.utils.MenuManager;
import travel.game.menus.utils.MenuTemplate;
import travel.game.menus.utils.Menus;
import travel.game.worlds.WorldHandler;
import travel.game.worlds.Worlds;

public class CreateOptionsMenu extends MenuTemplate implements Menu {
	
	private Rectangle back = new Rectangle(50, 430, 100, 40);
	private Rectangle edit = new Rectangle(120, 250, 200, 50);
	private Rectangle play = new Rectangle(370, 250, 200, 50);
	private Rectangle reset = new Rectangle(225, 330, 250, 50);
	
	private Color backColor = Color.GRAY;
	private Color editColor = Color.GRAY;
	private Color playColor = Color.GRAY;
	private Color resetColor = Color.GRAY;
	
	private static Worlds currentWorld;
	
	public static void passInValue(Worlds world) {
		currentWorld = world;
	}
	
	private String getTitle() {
		switch(currentWorld) {
			case create1: return "World 1";
			case create2: return "World 2";
			case create3: return "World 3";
			default:
				return "Unknown World";
		}
	}
	
	@Override
	public void update(int x, int y, boolean pressed,
			boolean released) {
		
		// back actions
		if(inRect(x, y, back) && pressed)
			backColor = Color.GRAY.darker();
		else
			if(inRect(x, y, back) && released) {
				backColor = Color.GRAY;
				MenuManager.setCurrentMenu(Menus.create);
			}
		
		// edit actions
		if(inRect(x, y, edit) && pressed)
			editColor = Color.GRAY.darker();
		else
			if(inRect(x, y, edit) && released) {
				editColor = Color.GRAY;
				
			}
		
		// play actions
		if(inRect(x, y, play) && pressed)
			playColor = Color.GRAY.darker();
		else
			if(inRect(x, y, play) && released) {
				playColor = Color.GRAY;
				WorldHandler.loadWorld(currentWorld);
				Loop.setCurrentState(GameState.game);
			}
		
		// reset actions
		if(inRect(x, y, reset) && pressed)
			resetColor = Color.GRAY.darker();
		else
			if(inRect(x, y, reset) && released) {
				resetColor = Color.GRAY;
				MenuManager.setCurrentMenu(Menus.create);
			}
	}
	
	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.black);
		g2.fillRect(0, 0, Loop.getWidth(), Loop.getHeight());
		
		// fills buttons
		drawButton(g2, edit, editColor);
		drawButton(g2, play, playColor);
		drawButton(g2, reset, resetColor);
		
		// button labels
		g2.setColor(Color.white);
		g2.setFont(new Font("Arial Bold", 30, 30));
		g2.drawString("Edit", (edit.x + 65), (edit.y + 35));
		g2.drawString("Play", (play.x + 65), (play.y + 35));
		g2.drawString("Restore Default", (reset.x + 10), (reset.y + 35));
		
		// Title
		g2.setFont(new Font("Arial Bold", 80, 80));
		g2.drawString(getTitle(), 200, 100);
		
		// Back Button
		drawButton(g2, back, backColor);
		g2.setFont(new Font("Arial Bold", 20, 20));
		g2.setColor(Color.white);
		g2.drawString("Back", (back.x + 30), (back.y + 25));
	}
	
}
