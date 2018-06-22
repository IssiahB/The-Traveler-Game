package travel.game.menus.utils;

import java.awt.Graphics;

public interface Menu {
	
	public void update(int x, int y, boolean pressed, boolean released);
	
	public void render(Graphics g);
}
