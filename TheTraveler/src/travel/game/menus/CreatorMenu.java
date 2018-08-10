package travel.game.menus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import travel.game.Loop;
import travel.game.gfx.ImageHandler;
import travel.game.menus.utils.Menu;
import travel.game.menus.utils.MenuTemplate;

public class CreatorMenu extends MenuTemplate implements Menu {
	
	private static int scaleNumX;
	private static int scaleNumY;
	private static int width, height;
	private BufferedImage backgroundImage = ImageHandler.getImage("res/worlds/textures/World1.png");
	
	private Rectangle done = new Rectangle();
	private Color doneColor = Color.GRAY;
	
	private BufferedImage exitImage = ImageHandler.getImage("res/textures/Exit.png");
	private BufferedImage playerImage = ImageHandler.getImage("res/textures/Player.png");
	private BufferedImage enemyImage = ImageHandler.getImage("res/textures/Enemy.png");
	private BufferedImage blockImage = ImageHandler.getImage("res/textures/Block.png");
	private BufferedImage lavaImage = ImageHandler.getImage("res/textures/LavaBlock.png");
	private BufferedImage pickedImage = ImageHandler.getImage("res/textures/Picked.png");
	
	private Rectangle exit = new Rectangle();
	private Rectangle player = new Rectangle();
	private Rectangle enemy = new Rectangle();
	private Rectangle block = new Rectangle();
	private Rectangle lava = new Rectangle();
	
	private enum ImageSelected {
		exit, player,
		enemy, block,
		lava, none;
	}
	
	private ImageSelected selected = ImageSelected.none;
	
	public CreatorMenu() {
		
	}
	
	public static void passWidthHeight(int width, int height) {
		CreatorMenu.width = width;
		CreatorMenu.height = height;
		getScaledWidth();
		getScaledHeight();
	}
	
	@Override
	public void update(int x, int y, boolean pressed,
			boolean released) {
		
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Loop.getWidth(), Loop.getHeight());
		
		g.drawImage(backgroundImage, 0, 0, (width / scaleNumX), (height / scaleNumY), null);
	}
	
	private void getSelectedImage(int x, int y, boolean pressed,
			boolean released) {
		
		
		
	}
	
	private static void getScaledWidth() {
		if(width > Loop.getWidth()) {
			for(int i = 1; i < Loop.getWidth(); i++) {
				if((width / i) < Loop.getWidth()) {
					scaleNumX = i;
					break;
				}
			}
		} else {
			scaleNumX = 1;
		}
	}
	
	private static void getScaledHeight() {
		if(height > Loop.getHeight()) {
			for(int i = 1; i < Loop.getHeight(); i++) {
				if((height / i) < Loop.getHeight()) {
					scaleNumY = i;
					break;
				}
			}
		} else {
			scaleNumY = 1;
		}
	}
	
}
