package travel.game.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import travel.game.GameState;
import travel.game.Loop;
import travel.game.menus.utils.MenuManager;
import travel.game.worlds.WorldHandler;

public class MouseClicker implements MouseListener {
	
	private WorldHandler world;
	private MenuManager menu;
	
	public MouseClicker(WorldHandler world, MenuManager menu) {
		this.world = world;
		this.menu = menu;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = (e.getY() - 25);

		if(Loop.getCurrentState() == GameState.menu) {
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = (e.getY() - 25);
		
		if(Loop.getCurrentState() == GameState.menu) {
			menu.getCurrentMenu().update(x, y, true, false);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = (e.getY() - 25);
		
		if(Loop.getCurrentState() == GameState.menu) {
			menu.getCurrentMenu().update(x, y, false, true);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}
