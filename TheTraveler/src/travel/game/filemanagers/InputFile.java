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

	private int width, height;
	private List<Point> blockPosition = new ArrayList<Point>();
	private List<ISlidable> blockSlidables = new ArrayList<ISlidable>();
	private List<Point> enemyPosition = new ArrayList<Point>();
	private int x, y;
	private Point exit;

	public void readFile(String path) {
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
					blockSlidables.add(new SlideVertical().setVerticalDistanceAndSpeed(distance, speed));
				}
				
				if(type.equals("h")) {
					int distance = s.nextInt();
					int speed = s.nextInt();
					blockPosition.add(new Point(tempX, tempY));
					blockSlidables.add(new SlideHorizontal().setHorizontalDistanceAndSpeed(distance, speed));
				}
			}
		} catch (Exception e) {
			System.err.println(path + " could not be found");
			e.printStackTrace();
			s.close();
		}
	}
	
	public void cleanUp() {
	/*	x = 0;
		y = 0;
		width = 0;
		height = 0;
		exit = null;
		blockPosition = null;
		blockSlidables = null;
		enemyPosition = null; */
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Point getExit() {
		return exit;
	}

	public void setExit(Point exit) {
		this.exit = exit;
	}

	public List<Point> getEnemyPosition() {
		return enemyPosition;
	}

	public void setEnemyPosition(List<Point> enemyPosition) {
		this.enemyPosition = enemyPosition;
	}

	public List<Point> getBlockPosition() {
		return blockPosition;
	}

	public void setBlockPosition(List<Point> blockPosition) {
		this.blockPosition = blockPosition;
	}

	public List<ISlidable> getBlockSlidables() {
		return blockSlidables;
	}

	public void setBlockSlidables(List<ISlidable> blockSlidables) {
		this.blockSlidables = blockSlidables;
	}
}
