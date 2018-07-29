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

public class SettingMenu extends MenuTemplate implements Menu {
	
	private Rectangle back = new Rectangle(50, 430, 100, 40);
	private Color backColor = Color.GRAY;
	
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
	}
	
	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Loop.getWidth(), Loop.getHeight());
		
		g2.setFont(new Font("Arial Bold", 20, 20));
		drawButton(g2, back, backColor);
		
		g.setColor(Color.white);
		g2.drawString("Back", (back.x + 30), (back.y + 25));
		
		g2.setFont(new Font("Arial Bold", 40, 40));
		g2.drawString("Not Implemented", 200, 200);
	}
	
}
