package travel.test;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import travel.game.entity.slide.ISlidable;
import travel.game.entity.slide.SlideHorizontal;
import travel.game.entity.slide.SlideNone;
import travel.game.entity.slide.SlideVertical;
import travel.game.filemanagers.OutputFile;

public class Test {
	
	public static void main(String[] args) {
		
		 List<Point> enemyPos = createPointList();
		 List<Point> blockPos = createPointList();
		 List<Point> lavaPos = createPointList();
		 List<ISlidable> blockSlidables = createSlidableList();
		 List<ISlidable> lavaSlidables = createSlidableList();
		
		
		 OutputFile.setUp(500, 500, 50, 50, 450, 450, enemyPos, blockPos,
		 blockSlidables, lavaPos, lavaSlidables);
		 //OutputFile.writeFile("C:/GitHub/TheTraveler/TheTraveler/src/travel/test/Writable.txt");
	
	}
	
	private static List<Point> createPointList() {
		List<Point> list = new ArrayList<Point>();
		
		list.add(new Point(200, 200));
		list.add(new Point(400, 400));
		list.add(new Point(100, 100));
		
		return list;
	}
	
	private static List<ISlidable> createSlidableList() {
		List<ISlidable> list = new ArrayList<ISlidable>();
		
		list.add(new SlideHorizontal()
				.setHorizontalDistanceAndSpeed(100, 2));
		list.add(new SlideVertical().setVerticalDistanceAndSpeed(50,
				1));
		list.add(new SlideNone());
		
		return list;
	}
}
