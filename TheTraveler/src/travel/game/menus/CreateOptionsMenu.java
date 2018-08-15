package travel.game.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import travel.game.GameState;
import travel.game.Loop;
import travel.game.filemanagers.InputFile;
import travel.game.menus.utils.Menu;
import travel.game.menus.utils.MenuManager;
import travel.game.menus.utils.MenuTemplate;
import travel.game.menus.utils.Menus;
import travel.game.worlds.WorldHandler;
import travel.game.worlds.Worlds;

public class CreateOptionsMenu extends MenuTemplate implements Menu {
	
	private Rectangle back = new Rectangle(50, 430, 100, 40);
	private Rectangle edit = new Rectangle(120, 250, 200, 50);
	private Rectangle play = new Rectangle(370, 250, 200, 50);
	private Rectangle reset = new Rectangle(225, 330, 250, 50);
	
	private Color backColor = Color.GRAY;
	private Color editColor = Color.GRAY;
	private Color playColor = Color.GRAY;
	private Color resetColor = Color.GRAY;
	
	private static Worlds currentWorld;
	
	public static void passInValue(Worlds world) {
		currentWorld = world;
	}
	
	private String getTitle() {
		switch (currentWorld) {
			case create1:
				return "World 1";
			case create2:
				return "World 2";
			case create3:
				return "World 3";
			default:
				return "Unknown World";
		}
	}
	
	@Override
	public void update(int x, int y, boolean pressed,
			boolean released) {
		
		// back actions
		if (inRect(x, y, back) && pressed)
			backColor = Color.GRAY.darker();
		else if (inRect(x, y, back) && released) {
			backColor = Color.GRAY;
			MenuManager.setCurrentMenu(Menus.create);
		}
		
		// edit actions
		if (inRect(x, y, edit) && pressed)
			editColor = Color.GRAY.darker();
		else if (inRect(x, y, edit) && released) {
			editColor = Color.GRAY;
			InputFile.readFile(
					"res/worlds/create/" + currentWorld + ".lvl");
			if (InputFile.getWidth() == 900) {
				InputFile.cleanUp();
				newWorld();
			} else {
				int restart = JOptionPane.showConfirmDialog(null,
						"Restart World?", "Restart",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (restart == 0) {
					InputFile.cleanUp();
					newWorld();
				} else {
					InputFile.cleanUp();
					CreatorMenu.loadWorld(currentWorld);
					MenuManager.setCurrentMenu(Menus.creator);
				}
			}
		}
		
		// play actions
		if (inRect(x, y, play) && pressed)
			playColor = Color.GRAY.darker();
		else if (inRect(x, y, play) && released) {
			playColor = Color.GRAY;
			WorldHandler.loadWorld(currentWorld);
			Loop.setCurrentState(GameState.game);
		}
		
		// reset actions
		if (inRect(x, y, reset) && pressed)
			resetColor = Color.GRAY.darker();
		else if (inRect(x, y, reset) && released) {
			resetColor = Color.GRAY;
			resetWorld();
			MenuManager.setCurrentMenu(Menus.create);
		}
	}
	
	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.black);
		g2.fillRect(0, 0, Loop.getWidth(), Loop.getHeight());
		
		// fills buttons
		drawButton(g2, edit, editColor);
		drawButton(g2, play, playColor);
		drawButton(g2, reset, resetColor);
		
		// button labels
		g2.setColor(Color.white);
		g2.setFont(new Font("Arial Bold", 30, 30));
		g2.drawString("Edit", (edit.x + 65), (edit.y + 35));
		g2.drawString("Play", (play.x + 65), (play.y + 35));
		g2.drawString("Restore Default", (reset.x + 10),
				(reset.y + 35));
		
		// Title
		g2.setFont(new Font("Arial Bold", 80, 80));
		g2.drawString(getTitle(), 200, 100);
		
		// Back Button
		drawButton(g2, back, backColor);
		g2.setFont(new Font("Arial Bold", 20, 20));
		g2.setColor(Color.white);
		g2.drawString("Back", (back.x + 30), (back.y + 25));
	}
	
	private void resetWorld() {
		PrintWriter out = null;
		
		try {
			out = new PrintWriter(new File(
					"res/worlds/create/" + currentWorld + ".lvl"));
			
			out.println("900 900");
			out.println("500 500");
			out.println("0 0");
			out.println("-1 \n-1 \n-1");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
	
	private void newWorld() {
		int width = getWidth();
		if (width == -1)
			return;
		
		int height = getHeight();
		if (height == -1)
			return;
		
		CreatorMenu.passValuesOfNewWorld(width, height, currentWorld);
		MenuManager.setCurrentMenu(Menus.creator);
	}
	
	private int getWidth() {
		int width = 0;
		String stringWidth = JOptionPane.showInputDialog(null,
				"Enter Width | (MAX = 700)", "Width",
				JOptionPane.QUESTION_MESSAGE);
		
		if (stringWidth == null)
			return -1;
		
		try {
			width = Integer.parseInt(stringWidth);
			if (width > 700)
				throw new IndexOutOfBoundsException("value too high");
			
			if (width < 100)
				throw new IndexOutOfBoundsException("value too low");
			
		} catch (Exception e) {
			System.err.println("Wrong Value");
			e.printStackTrace();
			return getWidth();
		}
		
		return width;
	}
	
	private int getHeight() {
		int height = 0;
		String stringHeight = JOptionPane.showInputDialog(null,
				"Enter Height | (MAX = 500)", "Height",
				JOptionPane.QUESTION_MESSAGE);
		
		if (stringHeight == null)
			return -1;
		
		try {
			height = Integer.parseInt(stringHeight);
			if (height > 500)
				throw new IndexOutOfBoundsException("Value too high");
			
			if (height < 100)
				throw new IndexOutOfBoundsException("value too low");
			
		} catch (Exception e) {
			System.err.println("Wrong Value");
			e.printStackTrace();
			return getHeight();
		}
		
		return height;
	}
	
}
