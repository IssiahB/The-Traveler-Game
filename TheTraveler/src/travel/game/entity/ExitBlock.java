package travel.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import travel.game.gfx.ImageHandler;

public class ExitBlock extends Entity{
	
	private static boolean exit = false;

	public ExitBlock(int x, int y, int width, int height) {
		super(x, y, width, height);
		image = ImageHandler.getImage("res/textures/Exit.png");
	}

	@Override
	public void update(EntityHandler entities) {
		for(int i = 0; i < entities.getObjects().size(); i++) {
			if(collider.touchedByPlayer(entities.getObjects().get(i))) {
				collider.suckInAndShrink(entities.getObjects().get(i));
				exit = true;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.PINK);

		if(image == null)
			g.fillRect(x, y, width, height);
		else
			g.drawImage(image, x, y, width, height, null);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public static boolean isExit() {
		return exit;
	}
	
	public static void resetExit() {
		ExitBlock.exit = false;
	}


}
