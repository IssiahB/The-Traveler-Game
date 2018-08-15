package travel.game.entity;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import travel.game.gfx.ImageHandler;

public class Player extends Entity {
	
	private int worldWidth = 0, worldHeight = 0;
	
	private static int staticX, staticY;
	private static int staticWidth, staticHeight;
	private static boolean up = false, down = false, left = false,
			right = false;
	private static boolean dead = false;
	
	private byte maxSpeed = 4;
	private int speed = 2;
	private double velX = 0, velY = 0;
	
	public Player(int x, int y, int width, int height,
			Dimension worldSize) {
		super(x, y, width, height);
		worldWidth = worldSize.width;
		worldHeight = worldSize.height;
		image = ImageHandler.getImage("res/textures/Player.png");
	}
	
	private void updatePosition() {
		if (up && velY > -maxSpeed) {
			velY = velY - speed;
		}
		
		if (down && velY < maxSpeed) {
			velY = velY + speed;
		}
		
		if (left && velX > -maxSpeed) {
			velX = velX - speed;
		}
		
		if (right && velX < maxSpeed) {
			velX = velX + speed;
		}
	}
	
	private void comeToAStop() {
		if (!left && !right) {
			if (velX > 0) {
				velX = velX - 0.5d;
			} else if (velX < 0) {
				velX = velX + 0.5d;
			}
		}
		
		if (!up && !down) {
			if (velY > 0) {
				velY = velY - 0.5d;
			} else if (velY < 0) {
				velY = velY + 0.5d;
			}
		}
	}
	
	private void checkInBounds() {
		if (x < 0) {
			x = 0;
		}
		
		if (y < 0) {
			y = 0;
		}
		
		if ((x + width) > worldWidth) {
			x = (worldWidth - width);
		}
		
		if ((y + height) > worldHeight) {
			y = (worldHeight - height);
		}
	}
	
	@Override
	public void update(EntityHandler entities) {
		checkInBounds();
		updatePosition();
		comeToAStop();
		
		staticX = x;
		staticY = y;
		staticWidth = width;
		staticHeight = height;
		
		x = x + (int) velX;
		y = y + (int) velY;
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		
		if (image == null)
			g.fillRect(x, y, width, height);
		else
			g.drawImage(image, x, y, width, height, null);
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	public static void updateInput(boolean up, boolean down,
			boolean left, boolean right) {
		Player.up = up;
		Player.down = down;
		Player.left = left;
		Player.right = right;
	}
	
	public static Point getPlayerPosition() {
		return new Point(staticX, staticY);
	}
	
	public static int getStaticWidth() {
		return staticWidth;
	}
	
	public static int getStaticHeight() {
		return staticHeight;
	}
	
	public static void resetDead() {
		Player.dead = false;
	}
	
	public static boolean isDead() {
		return dead;
	}
	
	public static void setDead(boolean dead) {
		Player.dead = dead;
	}
}
