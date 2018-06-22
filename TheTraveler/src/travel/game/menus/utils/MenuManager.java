package travel.game.menus.utils;

import travel.game.menus.ChooseMenu;
import travel.game.menus.MainMenu;
import travel.game.util.ImageHandler;

public class MenuManager {
	
	private static MainMenu main = new MainMenu(ImageHandler.getImage("res/menu/textures/mainMenuBackground.jpg"));
	private static ChooseMenu choose = new ChooseMenu(ImageHandler.getImage("res/menu/textures/chooseBackground.jpg"));
	
	private static Menu currentMenu = main;

	public static Menu getCurrentMenu() {
		return currentMenu;
	}

	public static void setCurrentMenu(Menus menu) {
		
		switch(menu) {
		case main: currentMenu = main; break;
		case choose: currentMenu = choose; break;
		case NOT: currentMenu = null; break;
		default :
			currentMenu = main;
		}
	}
	
}
