package travel.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import travel.game.util.ImageHandler;

public class Enemy extends Entity {

	private int speed = 1;

	public Enemy(int x, int y, int width, int height) {
		super(x, y, width, height);

		image = ImageHandler.getImage("res/textures/Enemy.png");

	}

	@Override
	public void update(EntityHandler entities) {
		int playerX = 0;
		int playerY = 0;

		for (int i = 0; i < entities.getObjects().size(); i++) {
			if (entities.getObjects().get(i) instanceof Player) {
				playerX = entities.getObjects().get(i).getX();
				playerY = entities.getObjects().get(i).getY();
			}

			if (collider.touchedByPlayer(entities.getObjects().get(i))) {
				entities.removeEntity(entities.getObjects().get(i));
			}
			
			if (collider.touchedByEnemy(entities.getObjects().get(i))) {
				collider.pushBack(entities.getObjects().get(i));
			}
		}

		if (playerX < x) {
			x = x - speed;
		}

		if (playerX > x) {
			x = x + speed;
		}

		if (playerY < y) {
			y = y - speed;
		}

		if (playerY > y) {
			y = y + speed;
		}
	}

	@Override
	public void render(Graphics g) {

		g.setColor(Color.RED);

		if (image == null)
			g.fillRect(x, y, width, height);
		else
			g.drawImage(image, x, y, width, height, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
