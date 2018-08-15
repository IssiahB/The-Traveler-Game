package travel.game.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import travel.game.Loop;
import travel.game.menus.utils.Menu;
import travel.game.menus.utils.MenuManager;
import travel.game.menus.utils.MenuTemplate;
import travel.game.menus.utils.Menus;
import travel.game.worlds.Worlds;

public class CreateMenu extends MenuTemplate implements Menu {
	
	private Rectangle back = new Rectangle(50, 430, 100, 40);
	private Rectangle world1 = new Rectangle(70, 50, 150, 100);
	private Rectangle world2 = new Rectangle(270, 200, 150, 100);
	private Rectangle world3 = new Rectangle(470, 350, 150, 100);
	
	private Color backColor = Color.GRAY;
	private Color world1Color = Color.GRAY;
	private Color world2Color = Color.GRAY;
	private Color world3Color = Color.GRAY;
	
	@Override
	public void update(int x, int y, boolean pressed,
			boolean released) {
		
		if (inRect(x, y, back) && pressed)
			backColor = Color.GRAY.darker();
		else if (inRect(x, y, back) && released) {
			backColor = Color.GRAY;
			MenuManager.setCurrentMenu(Menus.main);
		}
		
		// button 1
		if (inRect(x, y, world1) && pressed)
			world1Color = Color.GRAY.darker();
		else if (inRect(x, y, world1) && released) {
			world1Color = Color.GRAY;
			CreateOptionsMenu.passInValue(Worlds.create1);
			MenuManager.setCurrentMenu(Menus.options);
		}
		
		// button 2
		if (inRect(x, y, world2) && pressed)
			world2Color = Color.GRAY.darker();
		else if (inRect(x, y, world2) && released) {
			world2Color = Color.GRAY;
			CreateOptionsMenu.passInValue(Worlds.create2);
			MenuManager.setCurrentMenu(Menus.options);
		}
		
		// button 3
		if (inRect(x, y, world3) && pressed)
			world3Color = Color.GRAY.darker();
		else if (inRect(x, y, world3) && released) {
			world3Color = Color.GRAY;
			CreateOptionsMenu.passInValue(Worlds.create3);
			MenuManager.setCurrentMenu(Menus.options);
		}
	}
	
	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g.setColor(Color.black);
		g.fillRect(0, 0, Loop.getWidth(), Loop.getHeight());
		
		// drawing buttons
		g.setColor(Color.gray);
		drawButton(g2, world1, world1Color);
		drawButton(g2, world2, world2Color);
		drawButton(g2, world3, world3Color);
		
		// words for buttons
		g2.setColor(Color.white);
		g2.setFont(new Font("Arial Bold", 25, 25));
		g2.drawString("World 1", (world1.x + 30), (world1.y + 55));
		g2.drawString("World 2", (world2.x + 30), (world2.y + 55));
		g2.drawString("World 3", (world3.x + 30), (world3.y + 55));
		
		// Title
		g2.setFont(new Font("Arial Bold", 60, 60));
		g2.drawString("Create", 360, 120);
		g2.drawString("Worlds", 80, 380);
		
		// Back Button
		drawButton(g2, back, backColor);
		g2.setFont(new Font("Arial Bold", 20, 20));
		g2.setColor(Color.white);
		g2.drawString("Back", (back.x + 30), (back.y + 25));
	}
}
