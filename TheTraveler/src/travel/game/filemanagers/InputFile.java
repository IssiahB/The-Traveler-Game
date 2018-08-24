package travel.game.filemanagers;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import travel.game.entity.slide.ISlidable;
import travel.game.entity.slide.SlideHorizontal;
import travel.game.entity.slide.SlideNone;
import travel.game.entity.slide.SlideVertical;

public class InputFile {
	
	private static int width, height;
	private static List<Point> blockPosition = new ArrayList<Point>();
	private static List<Point> lavaPosition = new ArrayList<Point>();
	private static List<ISlidable> lavaSlidables = new ArrayList<ISlidable>();
	private static List<ISlidable> blockSlidables = new ArrayList<ISlidable>();
	private static List<Point> enemyPosition = new ArrayList<Point>();
	private static int x, y;
	private static Point exit;
	
	public static void readFile(String path) {
		Scanner s = null;
		
		try {
			s = new Scanner(new File(path));
			
			// gets width and height
			width = s.nextInt();
			height = s.nextInt();
			
			// gets player
			x = s.nextInt();
			y = s.nextInt();
			
			// gets exit
			int tempX = s.nextInt();
			int tempY = s.nextInt();
			exit = new Point(tempX, tempY);
			
			// gets enemy
			while ((tempX = s.nextInt()) != -1) {
				tempY = s.nextInt();
				enemyPosition.add(new Point(tempX, tempY));
			}
			
			String type = null;
			// gets blocks
			while ((tempX = s.nextInt()) != -1) {
				tempY = s.nextInt();
				type = s.next();
				
				if (type.equals("n")) {
					blockPosition.add(new Point(tempX, tempY));
					blockSlidables.add(new SlideNone());
				}
				
				if (type.equals("v")) {
					int distance = s.nextInt();
					int speed = s.nextInt();
					blockPosition.add(new Point(tempX, tempY));
					blockSlidables.add(new SlideVertical()
							.setVerticalDistanceAndSpeed(distance,
									speed));
				}
				
				if (type.equals("h")) {
					int distance = s.nextInt();
					int speed = s.nextInt();
					blockPosition.add(new Point(tempX, tempY));
					blockSlidables.add(new SlideHorizontal()
							.setHorizontalDistanceAndSpeed(distance,
									speed));
				}
			}
			
			// gets Lava
			while ((tempX = s.nextInt()) != -1) {
				tempY = s.nextInt();
				type = s.next();
				
				if (type.equals("n")) {
					lavaPosition.add(new Point(tempX, tempY));
					lavaSlidables.add(new SlideNone());
				}
				
				if (type.equals("v")) {
					int distance = s.nextInt();
					int speed = s.nextInt();
					lavaPosition.add(new Point(tempX, tempY));
					lavaSlidables.add(new SlideVertical()
							.setVerticalDistanceAndSpeed(distance,
									speed));
				}
				
				if (type.equals("h")) {
					int distance = s.nextInt();
					int speed = s.nextInt();
					lavaPosition.add(new Point(tempX, tempY));
					lavaSlidables.add(new SlideHorizontal()
							.setHorizontalDistanceAndSpeed(distance,
									speed));
				}
			}
			
		} catch (Exception e) {
			System.err.println(path + " could not be found");
			e.printStackTrace();
			s.close();
		}
	}
	
	public static void cleanUp() {
		x = 0;
		y = 0;
		width = 0;
		height = 0;
		exit = null;
		blockPosition.clear();
		enemyPosition.clear();
		blockSlidables.clear();
		lavaPosition.clear();
		lavaSlidables.clear();
	}
	
	public static int getWidth() {
		return width;
	}
	
	public static int getHeight() {
		return height;
	}
	
	public static List<Point> getBlockPosition() {
		return blockPosition;
	}
	
	public static List<Point> getLavaPosition() {
		return lavaPosition;
	}
	
	public static List<ISlidable> getLavaSlidables() {
		return lavaSlidables;
	}
	
	public static List<ISlidable> getBlockSlidables() {
		return blockSlidables;
	}
	
	public static List<Point> getEnemyPosition() {
		return enemyPosition;
	}
	
	public static int getX() {
		return x;
	}
	
	public static int getY() {
		return y;
	}
	
	public static Point getExit() {
		return exit;
	}
	
}
