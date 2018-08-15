package travel.game.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import travel.game.Loop;
import travel.game.entity.slide.ISlidable;
import travel.game.entity.slide.SlideHorizontal;
import travel.game.entity.slide.SlideNone;
import travel.game.entity.slide.SlideVertical;
import travel.game.filemanagers.InputFile;
import travel.game.filemanagers.OutputFile;
import travel.game.gfx.ImageHandler;
import travel.game.menus.utils.Menu;
import travel.game.menus.utils.MenuManager;
import travel.game.menus.utils.MenuTemplate;
import travel.game.menus.utils.Menus;
import travel.game.worlds.Worlds;

public class CreatorMenu extends MenuTemplate implements Menu {
	
	// needed Values
	private static int width, height;
	private static Worlds world;
	private static boolean loadWorld = false;
	private int hidden = 0;
	private int deleting = 0;
	private boolean isHidden = false;
	private boolean isDeleting = false;
	private BufferedImage backgroundImage = ImageHandler
			.getImage("res/worlds/textures/World1.png");
	
	// done Button
	private Rectangle done = new Rectangle(30, 440, 100, 40);
	private Rectangle hide = new Rectangle(635, 20, 60, 30);
	private Rectangle delete = new Rectangle(140, 440, 100, 40);
	
	private Color doneColor = Color.GRAY;
	private Color hideColor = Color.GRAY;
	private Color deleteColor = Color.GRAY;
	
	// Selector Images
	private BufferedImage exitImage = ImageHandler
			.getImage("res/textures/Exit.png");
	private BufferedImage playerImage = ImageHandler
			.getImage("res/textures/Player.png");
	private BufferedImage enemyImage = ImageHandler
			.getImage("res/textures/Enemy.png");
	private BufferedImage blockImage = ImageHandler
			.getImage("res/textures/Block.png");
	private BufferedImage lavaImage = ImageHandler
			.getImage("res/textures/LavaBlock.png");
	private BufferedImage pickedImage = ImageHandler
			.getImage("res/textures/Picked.png");
	
	// Selector Area's
	private Rectangle exit = new Rectangle(550, 20, 80, 80);
	private Rectangle player = new Rectangle(550, 110, 80, 80);
	private Rectangle enemy = new Rectangle(550, 200, 80, 80);
	private Rectangle block = new Rectangle(550, 290, 80, 80);
	private Rectangle lava = new Rectangle(550, 380, 80, 80);
	
	// selected Selector
	private enum ImageSelected {
		exit, player, enemy, block, lava, none;
	}
	
	private ImageSelected selected = ImageSelected.none;
	
	// All Entities in World
	private List<Point> entities = new ArrayList<Point>();
	
	// File Writing Values
	private int exitX = -1, exitY = -1;
	private int playerX = -1, playerY = -1;
	private List<Point> enemyPos = null;
	private List<Point> blockPos = null;
	private List<Point> lavaPos = null;
	
	private List<ISlidable> blockSlidable = null;
	private List<ISlidable> lavaSlidable = null;
	
	public static void passValuesOfNewWorld(int width, int height,
			Worlds world) {
		CreatorMenu.width = width;
		CreatorMenu.height = height;
		CreatorMenu.world = world;
	}
	
	public static void loadWorld(Worlds world) {
		loadWorld = true;
		CreatorMenu.world = world;
	}
	
	@Override
	public void update(int x, int y, boolean pressed,
			boolean released) {
		
		// hide action
		if (inRect(x, y, hide) && pressed) {
			hideColor = Color.GRAY.darker();
			pressed = false;
			released = true;
		} else if (inRect(x, y, hide) && released) {
			hideColor = Color.GRAY;
			hidden++;
			if (hidden == 1)
				isHidden = true;
			if (hidden == 2) {
				isHidden = false;
				hidden = 0;
			}
			
		}
		
		// delete action
		if (inRect(x, y, delete) && pressed) {
			deleteColor = Color.GRAY.darker();
			selected = ImageSelected.none;
		} else if (inRect(x, y, delete) && released) {
			deleteColor = Color.GRAY;
			deleting++;
			if (deleting == 1) {
				isDeleting = true;
			}
			if (deleting == 2) {
				isDeleting = false;
				deleting = 0;
			}
		}
		
		if (!isHidden) {
			// done action
			if (inRect(x, y, done) && pressed) {
				doneColor = Color.GRAY.darker();
				selected = ImageSelected.none;
			} else if (inRect(x, y, done) && released) {
				doneColor = Color.GRAY;
				
				setUpForFileSaving();
				OutputFile.setUp(width, height, playerX, playerY,
						exitX,
						exitY, enemyPos, blockPos, blockSlidable,
						lavaPos,
						lavaSlidable);
				OutputFile.writeFile(
						"res/worlds/create/" + world + ".lvl");
				reset();
				MenuManager.setCurrentMenu(Menus.options);
				return;
			}
			
			if (!isDeleting) {
				// checks if selected any image
				switchSelectedImage(x, y, released, exit,
						ImageSelected.exit);
				switchSelectedImage(x, y, released, player,
						ImageSelected.player);
				switchSelectedImage(x, y, released, enemy,
						ImageSelected.enemy);
				switchSelectedImage(x, y, released, block,
						ImageSelected.block);
				switchSelectedImage(x, y, released, lava,
						ImageSelected.lava);
			}
		}
		
		if (isDeleting) {
			for (Point pos : entities) {
				Rectangle rect = new Rectangle(pos.x, pos.y, 50, 50);
				if (inRect(x, y, rect)) {
					
					if (enemyPos != null)
						if (enemyPos.contains(pos))
							enemyPos.remove(pos);
						
					if (blockPos != null)
						if (blockPos.contains(pos)) {
							int index = blockPos.indexOf(pos);
							blockPos.remove(pos);
							blockSlidable.remove(index);
						}
					if (lavaPos != null)
						if (lavaPos.contains(pos)) {
							int index = lavaPos.indexOf(pos);
							lavaPos.remove(pos);
							blockSlidable.remove(index);
						}
				}
			}
		}
		
		// placing entities
		if (x <= width && y <= height
				&& selected != ImageSelected.none && pressed) {
			
			if (!isDeleting) {
				if (isHidden && inSelectors(x, y))
					addBlocks(x, y + 25);
				
				if (!isHidden && !inSelectors(x, y))
					addBlocks(x, y + 25);
				
				if (isHidden)
					addBlocks(x, y + 25);
			}
			
		}
	}
	
	@Override
	public void render(Graphics g) {
		if (loadWorld)
			getFileValues();
		
		Graphics2D g2 = (Graphics2D) g;
		
		// draw black background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Loop.getWidth(), Loop.getHeight());
		
		// draw background image at right size
		g.drawImage(backgroundImage, 0, 0, width, height, null);
		
		// draw exit block if user has added one
		if (exitX != -1 && exitY != -1)
			g.drawImage(exitImage, exitX, exitY, 50, 50, null);
		
		// draw player blocks if user has added one
		if (playerX != -1 && playerY != -1)
			g.drawImage(playerImage, playerX, playerY, 50, 50,
					null);
		
		// draw all the enemy blocks if user has added some
		if (enemyPos != null) {
			for (int i = 0; i < enemyPos.size(); i++) {
				g.drawImage(enemyImage, enemyPos.get(i).x,
						enemyPos.get(i).y, 50, 50, null);
			}
		}
		
		// draw all the wall blocks if user has added some
		if (blockPos != null) {
			for (int i = 0; i < blockPos.size(); i++) {
				g.drawImage(blockImage, blockPos.get(i).x,
						blockPos.get(i).y, 50, 50, null);
			}
		}
		
		// draw all the lava blocks if user has added some
		if (lavaPos != null) {
			for (int i = 0; i < lavaPos.size(); i++) {
				g.drawImage(lavaImage, lavaPos.get(i).x,
						lavaPos.get(i).y, 50, 50, null);
			}
		}
		
		// draw Hide Button
		drawButton(g2, hide, hideColor);
		g.setColor(Color.white);
		g.setFont(new Font("TimesNewRoman", 3, 20));
		if (!isHidden)
			g.drawString("Hide", hide.x + 10, hide.y + 20);
		else
			g.drawString("Show", hide.x + 3, hide.y + 20);
		
		// draw delete Button
		drawButton(g2, delete, deleteColor);
		g.setColor(Color.white);
		g.setFont(new Font("TimesNewRoman", 3, 25));
		if (!isDeleting)
			g.drawString("Delete", delete.x + 9, delete.y + 27);
		else
			g.drawString("Select", delete.x + 9, delete.y + 27);
		
		// draws selected image if selected
		if (!isHidden) {
			drawSelectedImage(g2, exitImage, exit,
					ImageSelected.exit);
			drawSelectedImage(g2, playerImage, player,
					ImageSelected.player);
			drawSelectedImage(g2, enemyImage, enemy,
					ImageSelected.enemy);
			drawSelectedImage(g2, blockImage, block,
					ImageSelected.block);
			drawSelectedImage(g2, lavaImage, lava,
					ImageSelected.lava);
			
			// draw done button
			drawButton(g2, done, doneColor);
			g.setColor(Color.white);
			g.setFont(new Font("TimesNewRoman", 3, 25));
			g.drawString("Done", done.x + 18, done.y + 27);
		}
	}
	
	private void addBlocks(int x, int y) {
		switch (selected) {
			case exit:
				exitX = x;
				exitY = y;
				break;
			case player:
				playerX = x;
				playerY = y;
				break;
			case enemy:
				addEnemy(x, y);
				entities.add(new Point(x, y));
				break;
			case block:
				addBlock(x, y);
				entities.add(new Point(x, y));
				break;
			case lava:
				addLava(x, y);
				entities.add(new Point(x, y));
				break;
			default:
				return;
		}
	}
	
	private void setUpForFileSaving() {
		if (exitX == -1 || exitY == -1) {
			exitX = 0;
			exitY = 0;
		}
		
		if (playerX == -1 || playerY == -1) {
			playerX = 0;
			playerY = 0;
		}
		if (enemyPos == null)
			enemyPos = new ArrayList<Point>();
		if (blockPos == null)
			blockPos = new ArrayList<Point>();
		if (lavaPos == null)
			lavaPos = new ArrayList<Point>();
		if (blockSlidable == null)
			blockSlidable = new ArrayList<ISlidable>();
		if (lavaSlidable == null)
			lavaSlidable = new ArrayList<ISlidable>();
	}
	
	private boolean inSelectors(int x, int y) {
		if (inRect(x, y, exit) || inRect(x, y, player))
			return true;
		if (inRect(x, y, enemy) || inRect(x, y, block)
				|| inRect(x, y, lava))
			return true;
		
		return false;
	}
	
	private void reset() {
		exitX = -1;
		exitY = -1;
		deleting = 0;
		hidden = 0;
		playerX = -1;
		playerY = -1;
		enemyPos = null;
		blockPos = null;
		lavaPos = null;
		blockSlidable = null;
		lavaSlidable = null;
		entities = new ArrayList<Point>();
	}
	
	private void switchSelectedImage(int mousex, int mousey,
			boolean released, Rectangle rect,
			ImageSelected selected) {
		if (inRect(mousex, mousey, rect) && released) {
			this.selected = selected;
		}
	}
	
	private void addEnemy(int x, int y) {
		if (enemyPos == null)
			enemyPos = new ArrayList<Point>();
		if (enemyPos.size() >= 15)
			enemyPos.remove(0);
		
		enemyPos.add(new Point(x, y));
	}
	
	private void addBlock(int x, int y) {
		if (blockPos == null) {
			blockPos = new ArrayList<Point>();
			blockSlidable = new ArrayList<ISlidable>();
		}
		
		if (blockPos.size() >= 30) {
			blockPos.remove(0);
			blockSlidable.remove(0);
		}
		
		blockPos.add(new Point(x, y));
		addSlidable(blockSlidable);
	}
	
	private void addLava(int x, int y) {
		if (lavaPos == null) {
			lavaPos = new ArrayList<Point>();
			lavaSlidable = new ArrayList<ISlidable>();
		}
		if (lavaPos.size() >= 5) {
			lavaPos.remove(0);
			lavaSlidable.remove(0);
		}
		
		lavaPos.add(new Point(x, y));
		addSlidable(lavaSlidable);
	}
	
	private void addSlidable(List<ISlidable> list) {
		int slidable = JOptionPane.showConfirmDialog(null,
				"Does This Block Slide?", "Slide",
				JOptionPane.YES_NO_OPTION);
		
		if (slidable == 0) {
			String[] options = { "Horizontal", "Vertical" };
			int direction = JOptionPane.showOptionDialog(null,
					"Choose Direction", "Direction",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, 0);
			int speed = getSpeed();
			int distance = getDistance();
			
			if (direction == 0)
				list.add(new SlideHorizontal()
						.setHorizontalDistanceAndSpeed(distance,
								speed));
			else
				list.add(new SlideVertical()
						.setVerticalDistanceAndSpeed(distance,
								speed));
		} else {
			list.add(new SlideNone());
		}
	}
	
	private int getSpeed() {
		int speed = 0;
		try {
			String strSpeed = JOptionPane.showInputDialog(null,
					"Give Speed | (MAX = 6)", "Speed",
					JOptionPane.QUESTION_MESSAGE);
			speed = Integer.parseInt(strSpeed);
			
			if (speed > 6 || speed < 1) {
				throw new IndexOutOfBoundsException();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return getSpeed();
		}
		
		return speed;
	}
	
	private int getDistance() {
		int distance = 0;
		try {
			String strDistance = JOptionPane.showInputDialog(null,
					"Give Distance | (MAX = 100)", "Distance",
					JOptionPane.QUESTION_MESSAGE);
			distance = Integer.parseInt(strDistance);
			
			if (distance > 100 || distance < 1)
				throw new IndexOutOfBoundsException();
		} catch (Exception e) {
			e.printStackTrace();
			return getDistance();
		}
		
		return distance;
	}
	
	private void drawSelectedImage(Graphics2D g, BufferedImage image,
			Rectangle drawSurrface, ImageSelected type) {
		
		if (selected == type)
			g.drawImage(pickedImage, drawSurrface.x, drawSurrface.y,
					drawSurrface.width, drawSurrface.height, null);
		else
			g.drawImage(image, drawSurrface.x, drawSurrface.y,
					drawSurrface.width, drawSurrface.height, null);
	}
	
	private void getFileValues() {
		InputFile.readFile("res/worlds/create/" + world + ".lvl");
		width = InputFile.getWidth();
		height = InputFile.getHeight();
		playerX = InputFile.getX();
		playerY = InputFile.getY();
		exitX = InputFile.getExit().x;
		exitY = InputFile.getExit().y;
		
		enemyPos = new ArrayList<Point>();
		blockPos = new ArrayList<Point>();
		lavaPos = new ArrayList<Point>();
		blockSlidable = new ArrayList<ISlidable>();
		lavaSlidable = new ArrayList<ISlidable>();
		
		for (int i = 0; i < InputFile.getEnemyPosition()
				.size(); i++) {
			enemyPos.add(InputFile.getEnemyPosition().get(i));
		}
		for (int i = 0; i < InputFile.getBlockPosition()
				.size(); i++) {
			blockPos.add(InputFile.getBlockPosition().get(i));
		}
		for (int i = 0; i < InputFile.getLavaPosition().size(); i++) {
			lavaPos.add(InputFile.getLavaPosition().get(i));
		}
		for (int i = 0; i < InputFile.getBlockSlidables()
				.size(); i++) {
			blockSlidable.add(InputFile.getBlockSlidables().get(i));
		}
		for (int i = 0; i < InputFile.getLavaSlidables()
				.size(); i++) {
			lavaSlidable.add(InputFile.getLavaSlidables().get(i));
		}
		entities.addAll(enemyPos);
		entities.addAll(blockPos);
		entities.addAll(lavaPos);
		InputFile.cleanUp();
		loadWorld = false;
	}
	
}
