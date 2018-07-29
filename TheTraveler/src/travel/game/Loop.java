package travel.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import travel.game.entity.ExitBlock;
import travel.game.entity.Player;
import travel.game.gfx.Display;
import travel.game.input.Keyboard;
import travel.game.input.MouseClicker;
import travel.game.menus.PauseMenu;
import travel.game.menus.utils.Menu;
import travel.game.menus.utils.MenuManager;
import travel.game.menus.utils.Menus;
import travel.game.worlds.WorldHandler;

public class Loop implements Runnable {
	// game variables
	private String title;
	private static int width, height;
	private boolean running = false;
	
	// back end variables
	private Thread thread;
	private BufferStrategy bs;
	private Graphics g;
	
	// other classes
	private Display display;
	private WorldHandler world;
	private MenuManager menu;
	private Keyboard keyboard;
	private MouseClicker mouse;
	
	private static GameState currentState = GameState.menu;
	
	public Loop(String title, int width, int height) {
		Loop.width = width;
		Loop.height = height;
		this.title = title;
	}
	
	// starts the thread
	public synchronized void start() {
		if (running) // if game already running can't start
			return;
		
		// sets running to true
		running = true;
		// starts thread
		thread = new Thread(this);
		thread.start();
	}
	
	// stops the thread
	public synchronized void stop() {
		if (!running) // if not running cant stop
			return;
		
		// sets running to false
		running = false;
		cleanUp();
		try {
			// safely ends the thread
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void cleanUp() {
		if(display.getFrame().isVisible())
			display.getFrame().dispose();
		
		WorldHandler.clearWorld();
		menu.cleanUp();
	}
	
	// called before loop begins
	private void init() {
		
		world = new WorldHandler(this);
		menu = new MenuManager();
		keyboard = new Keyboard();
		mouse = new MouseClicker(world);
		display = new Display(title, width, height);
		MenuManager.setCurrentMenu(Menus.main);
		
		display.getCanvas().addMouseListener(mouse);
		display.getFrame().addKeyListener(keyboard);
		display.getFrame().validate();
	}
	
	public void run() {
		init();
		
		double maxFrameTime = 1000000000 / 60;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int update = 0;
		
		// the main loop
		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / maxFrameTime;
			timer += now - lastTime;
			lastTime = now;
			
			if (delta >= 1) {
				// every second update and render 60 times
				update();
				render();
				update++;
				delta--;
			}
			
			if (timer >= 1000000000) {
				System.out.println("FPS : " + update);
				update = 0;
				timer = 0;
			}
			
			if (running)
				running = display.getFrame().isVisible();
		}
		
		stop();
	}
	
	private void update() {
		
		if (currentState == GameState.game) {
			keyboard.update();
			world.getEntities().update();
			Player.updateInput(keyboard.up, keyboard.down,
					keyboard.left, keyboard.right);
			
			if (keyboard.escape) {
				MenuManager.setCurrentMenu(Menus.pause);
				currentState = GameState.pause;
			}
			
			if (Player.isDead()) {
				MenuManager.setCurrentMenu(Menus.pause);
				currentState = GameState.pause;
				Player.resetDead();
			}
			
			if (ExitBlock.isExit()) {
				MenuManager.setCurrentMenu(Menus.pause);
				currentState = GameState.pause;
				ExitBlock.resetExit();
			}
		}
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
		boolean isGameState = (currentState == GameState.game) ? true
				: false;
		
		if (isGameState) {
			g.setColor(Color.black);
			g.fillRect(0, 0, width, height);
			
			world.getTranslate().translate(world.getPlayer(), g);
			world.getEntities().render(g);
		} else {
			MenuManager.getCurrentMenu().render(g);
		}
		
		bs.show();
		g.dispose();
	}
	
	public String getTitle() {
		return title;
	}
	
	public static int getWidth() {
		return width;
	}
	
	public static int getHeight() {
		return height;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public Display getDisplay() {
		return display;
	}
	
	public WorldHandler getWorld() {
		return world;
	}
	
	public static GameState getCurrentState() {
		return currentState;
	}
	
	public static void setCurrentState(GameState currentState) {
		Loop.currentState = currentState;
	}
	
	public Keyboard getKeyboard() {
		return keyboard;
	}
	
	public MouseClicker getMouse() {
		return mouse;
	}
	
}
