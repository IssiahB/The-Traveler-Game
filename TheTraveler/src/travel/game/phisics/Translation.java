package travel.game.phisics;

import java.awt.Graphics;

import travel.game.entity.Entity;

public class Translation {
	
	public void translate(Entity entity, Graphics g) {
		g.translate((-entity.getX() + 325), (-entity.getY() + 250));
	}
	
}
