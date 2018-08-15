package travel.game;

public class Main {
	
	public static void main(String[] args) {
		// creates a loop and starts it
		try {
			Loop loop = new Loop("The Traveler", 700, 500);
			loop.start();
		} catch (Exception e) {
			System.err.println("Could Not Start Game");
			e.printStackTrace();
		}
	}
}
