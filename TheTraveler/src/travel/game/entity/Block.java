package travel.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import travel.game.entity.slide.ISlidable;
import travel.game.gfx.ImageHandler;

public class Block extends Entity{
	
	private ISlidable slide;

	public Block(ISlidable slide, int x, int y, int width, int height) {
		super(x, y, width, height);
		image = ImageHandler.getImage("res/textures/Block.png");
		this.slide = slide;
	}

	@Override
	public void update(EntityHandler entities) {
		
		for(int i = 0; i < entities.getObjects().size(); i++) {
			if(collider.touchedByPlayer(entities.getObjects().get(i))) {
				collider.pushBack(entities.getObjects().get(i));
			}
			
			if(collider.touchedByEnemy(entities.getObjects().get(i))) {
				collider.pushBack(entities.getObjects().get(i));
			}
		}
		
		slide.slide(this);
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.black);
		
		if(image == null)
			g.fillRect(x, y, width, height);
		else
			g.drawImage(image, x, y, width, height, null);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
