package travel.game.menus.utils;

import travel.game.gfx.ImageHandler;
import travel.game.menus.ChooseMenu;
import travel.game.menus.CreateMenu;
import travel.game.menus.MainMenu;
import travel.game.menus.PauseMenu;
import travel.game.menus.SettingMenu;

public class MenuManager {
	
	private static MainMenu main = new MainMenu(ImageHandler
			.getImage("res/menu/textures/mainMenuBackground.jpg"));
	
	private static ChooseMenu choose = new ChooseMenu(ImageHandler
			.getImage("res/menu/textures/chooseBackground.jpg"));
	
	private static SettingMenu setting = new SettingMenu();
	private static CreateMenu create = new CreateMenu();
	private static PauseMenu pause = new PauseMenu();
	
	private static Menu currentMenu = main;
	
	public static Menu getCurrentMenu() {
		return currentMenu;
	}
	
	public static void setCurrentMenu(Menus menu) {
		
		switch (menu) {
			case main:
				currentMenu = main;
				break;
			case choose:
				currentMenu = choose;
				break;
			case pause:
				currentMenu = pause;
				break;
			case setting:
				currentMenu = setting;
				break;
			case create:
				currentMenu = create;
				break;
			case NOT:
				currentMenu = null;
				break;
			default:
				currentMenu = main;
		}
	}
	
	public void cleanUp() {
		main = null;
		choose = null;
		pause = null;
		currentMenu = null;
	}
	
}
