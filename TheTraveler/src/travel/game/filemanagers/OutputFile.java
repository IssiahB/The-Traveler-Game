package travel.game.filemanagers;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import travel.game.entity.slide.ISlidable;
import travel.game.entity.slide.SlideHorizontal;
import travel.game.entity.slide.SlideVertical;

public class OutputFile {
	
	private static int width, height;
	private static int playerX, playerY;
	private static int exitX, exitY;
	
	private static List<Point> enemyPos;
	private static List<Point> blockPos;
	private static List<Point> lavaPos;
	
	private static List<ISlidable> blockSlidables;
	private static List<ISlidable> lavaSlidables;
	
	public static void writeFile(String path) {
		PrintWriter out = null;
		
		try {
			out = new PrintWriter(new File(path));
			
			out.println(width + " " + height);
			out.println(playerX + " " + playerY);
			out.println(exitX + " " + exitY + "\n");
			
			// prints enemy position
			for (int i = 0; i < enemyPos.size(); i++) {
				out.println(enemyPos.get(i).x + " " +
						enemyPos.get(i).y);
			}
			out.println("-1\n");
			
			// prints block position and if slidable
			printSlidableObjects(out, blockPos, blockSlidables);
			out.println("-1\n");
			
			// prints lava position and if slidable
			printSlidableObjects(out, lavaPos, lavaSlidables);
			out.println("-1");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
	
	private static void printSlidableObjects(PrintWriter out,
			List<Point> pos, List<ISlidable> slide) {
		for (int i = 0; i < pos.size(); i++) {
			out.println(pos.get(i).x + " " + pos.get(i).y
					+ getSlidableAsString(slide.get(i)));
			
		}
	}
	
	private static String getSlidableAsString(ISlidable slide) {
		if (slide instanceof SlideHorizontal)
			return (" h " + slide.getDistance() + " "
					+ slide.getSpeed());
		
		if (slide instanceof SlideVertical)
			return (" v " + slide.getDistance() + " "
					+ slide.getSpeed());
		
		return " n";
	}
	
	public static void setUp(int width, int height, int playerX,
			int playerY, int exitX, int exitY, List<Point> enemyPos,
			List<Point> blockPos, List<ISlidable> blockSlidables,
			List<Point> lavaPos, List<ISlidable> lavaSlidables) {
		
		OutputFile.width = width;
		OutputFile.height = height;
		OutputFile.playerX = playerX;
		OutputFile.playerY = playerY;
		OutputFile.exitX = exitX;
		OutputFile.exitY = exitY;
		OutputFile.enemyPos = enemyPos;
		OutputFile.blockPos = blockPos;
		OutputFile.lavaPos = lavaPos;
		OutputFile.blockSlidables = blockSlidables;
		OutputFile.lavaSlidables = lavaSlidables;
		
	}
}
