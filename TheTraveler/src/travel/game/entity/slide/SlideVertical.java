package travel.game.entity.slide;

import travel.game.entity.Entity;

public class SlideVertical implements ISlidable {
	private int distance = 0, speed = 0;
	private int farUpPosition = 0;
	private int farDownPosition = 0;
	
	private boolean up = true;
	private boolean down = false;
	
	private byte firstCall = 0;

	@Override
	public void slide(Entity entity) {
		if(firstCall == 0) {
			farUpPosition = (entity.getY() - distance);
			farDownPosition = ((entity.getY() + entity.getHeight()) + distance);
			firstCall++;
		}
		
		if(entity.getY() > farUpPosition && up == true) {
			entity.setY(entity.getY() - speed);
		} else {
			up = false;
			down = true;
		}
		
		if((entity.getY() + entity.getHeight()) < farDownPosition && down == true) {
			entity.setY(entity.getY() + speed);
		} else {
			up = true;
			down = false;
		}
	}

	public ISlidable setVerticalDistanceAndSpeed(int distance, int speed) {
		this.distance = distance;
		this.speed = speed;
		return this;
	}

}
