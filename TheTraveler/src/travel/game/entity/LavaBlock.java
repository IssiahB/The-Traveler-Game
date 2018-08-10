package travel.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import travel.game.entity.slide.ISlidable;
import travel.game.gfx.ImageHandler;

public class LavaBlock extends Entity {
	
	private ISlidable slidable;

	public LavaBlock(ISlidable slidable, int x, int y, int width, int height) {
		super(x, y, width, height);
		image = ImageHandler.getImage("res/textures/LavaBlock.png");
		this.slidable = slidable;
	}

	@Override
	public void update(EntityHandler entities) {
		
		for (int i = 0; i < entities.getObjects().size(); i++) {
			if (collider.touchedByPlayer(entities.getObjects().get(i))) {
				Player.setDead(true);
			}
			
			if (collider.touchedByEnemy(entities.getObjects().get(i))) {
				collider.pushBack(entities.getObjects().get(i));
			}
		}
		
		slidable.slide(this);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.orange);
		
		if(image != null)
			g.drawImage(image, x, y, width, height, null);
		else
			g.fillRect(x, y, width, height);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
}
