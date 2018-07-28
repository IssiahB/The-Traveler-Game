package travel.game.menus.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class MenuTemplate {
	
	protected Font font = new Font("TimesNewRoman", 3, 40);
	
	protected void drawButton(Graphics2D g2, Rectangle rect, Color color) {
		g2.setColor(color);
		g2.fill(rect);
	}
	
	protected boolean inRect(int x, int y, Rectangle rect) {
		y = y + 25;
		
		if(x > rect.getX() && x < (rect.getX() + rect.getWidth())) {
			if(y > rect.getY() && y < (rect.getY() + rect.getHeight())) {
				return true;
			}
		}
		
		return false;
	}
}
