package travel.game.worlds;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;

import travel.game.Loop;
import travel.game.entity.Background;
import travel.game.entity.Block;
import travel.game.entity.Enemy;
import travel.game.entity.EntityHandler;
import travel.game.entity.ExitBlock;
import travel.game.entity.LavaBlock;
import travel.game.entity.Player;
import travel.game.entity.slide.ISlidable;
import travel.game.filemanagers.InputFile;
import travel.game.gfx.ImageHandler;
import travel.game.phisics.Translation;;

public class WorldHandler {
	
	private static Player player;
	private static ExitBlock exit;
	
	private Loop loop;
	
	private static EntityHandler entities = new EntityHandler();
	private Translation translate = new Translation();
	
	private static BufferedImage background = null;
	
	private static List<Point> blockPosition = null;
	private static List<Point> lavaPosition = null;
	private static List<ISlidable> blockSlidable = null;
	private static List<ISlidable> lavaSlidable = null;
	private static List<Point> enemyPosition = null;
	private static Dimension sizeOfWorld = null;
	private static Point playerXY = null;
	private static Point exitXY = null;
	
	private static Worlds pickedWorld;
	
	public WorldHandler(Loop loop) {
		this.loop = loop;
	}
	
	public static void loadWorld(Worlds world) {
		clearWorld();
		
		pickedWorld = world;
		
		switch (world) {
			case create1:
				InputFile.readFile("res/worlds/create/create1.lvl");
				break;
			case create2:
				InputFile.readFile("res/worlds/create/create2.lvl");
				break;
			case create3:
				InputFile.readFile("res/worlds/create/create3.lvl");
				break;
			default:
				InputFile.readFile(
						"res/worlds/" + world.toString() + ".lvl");
		}
		
		pickWorldTexture(world);
		
		sizeOfWorld = new Dimension(InputFile.getWidth(),
				InputFile.getHeight());
		playerXY = new Point(InputFile.getX(), InputFile.getY());
		exitXY = InputFile.getExit();
		blockPosition = InputFile.getBlockPosition();
		lavaPosition = InputFile.getLavaPosition();
		blockSlidable = InputFile.getBlockSlidables();
		lavaSlidable = InputFile.getLavaSlidables();
		enemyPosition = InputFile.getEnemyPosition();
		
		addBackground();
		addExit();
		addBlocks();
		addLava();
		addPlayer();
		addEnemies();
	}
	
	private static void pickWorldTexture(Worlds world) {
		switch (world) {
			case world3:
				background = ImageHandler
						.getImage("res/worlds/textures/Lava.png");
				break;
			case world6:
				background = ImageHandler
						.getImage("res/worlds/textures/Desert.png");
				break;
			case world7:
				background = ImageHandler
						.getImage("res/worlds/textures/Forest.png");
				break;
			default:
				background = ImageHandler
						.getImage("res/worlds/textures/world1.png");
		}
	}
	
	private static void addBackground() {
		entities.addEntity(
				new Background(0, 0, (int) sizeOfWorld.getWidth(),
						(int) sizeOfWorld.getHeight(), background));
	}
	
	private static void addPlayer() {
		entities.addEntity((player = new Player((int) playerXY.getX(),
				(int) playerXY.getY(), 50, 50, sizeOfWorld)));
	}
	
	private static void addExit() {
		entities.addEntity((exit = new ExitBlock((int) exitXY.getX(),
				(int) exitXY.getY(), 50, 50)));
	}
	
	private static void addBlocks() {
		for (int i = 0; i < blockPosition.size(); i++) {
			entities.addEntity(new Block(blockSlidable.get(i),
					(int) blockPosition.get(i).getX(),
					(int) blockPosition.get(i).getY(), 50, 50));
		}
	}
	
	private static void addEnemies() {
		for (int i = 0; i < enemyPosition.size(); i++) {
			entities.addEntity(new Enemy(
					(int) enemyPosition.get(i).getX(),
					(int) enemyPosition.get(i).getY(), 50, 50));
		}
	}
	
	private static void addLava() {
		for (int i = 0; i < lavaPosition.size(); i++) {
			entities.addEntity(new LavaBlock(lavaSlidable.get(i),
					(int) lavaPosition.get(i).getX(),
					(int) lavaPosition.get(i).getY(), 50, 50));
		}
	}
	
	public static void clearWorld() {
		entities.cleanUp();
		InputFile.cleanUp();
		background = null;
		lavaPosition = null;
		enemyPosition = null;
		blockPosition = null;
		blockSlidable = null;
		lavaSlidable = null;
		sizeOfWorld = null;
		playerXY = null;
		exitXY = null;
		player = null;
		exit = null;
	}
	
	public EntityHandler getEntities() {
		return entities;
	}
	
	public List<Point> getBlockPosition() {
		return blockPosition;
	}
	
	public Dimension getSizeOfWorld() {
		return sizeOfWorld;
	}
	
	public Point getPlayerXY() {
		return playerXY;
	}
	
	public Point getExitXY() {
		return exitXY;
	}
	
	public BufferedImage getBackground() {
		return background;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public ExitBlock getExit() {
		return exit;
	}
	
	public List<Point> getEnemyPosition() {
		return enemyPosition;
	}
	
	public Translation getTranslate() {
		return translate;
	}
	
	public static Worlds getPickedWorld() {
		return pickedWorld;
	}
}
