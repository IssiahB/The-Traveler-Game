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
	
	protected boolean inRect(int mouseX, int mouseY, int posX,
			int posY, int width, int height) {
		mouseY = mouseY + 25;
		
		if(mouseX > posX && mouseX < (posX + width)) {
			if(mouseY > posY && mouseY < (posY + height)) {
				return true;
			}
		}
		
		return false;
	}
	
	protected boolean inRect(int mouseX, int mouseY, Rectangle rect) {
		mouseY = mouseY + 25;
		
		if(mouseX > rect.getX() && mouseX < (rect.getX() + rect.getWidth())) {
			if(mouseY > rect.getY() && mouseY < (rect.getY() + rect.getHeight())) {
				return true;
			}
		}
		
		return false;
	}
}
