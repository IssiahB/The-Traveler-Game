package travel.game.entity.slide;

import travel.game.entity.Entity;

public class SlideHorizontal implements ISlidable {
	private int distance = 0, speed = 0;
	private int farLeftPosition = 0;
	private int farRightPosition = 0;
	
	private boolean up = true;
	private boolean down = false;
	
	private byte firstCall = 0;

	@Override
	public void slide(Entity entity) {
		if(firstCall == 0) {
			farLeftPosition = (entity.getX() - distance);
			farRightPosition = ((entity.getX() + entity.getWidth()) + distance);
			firstCall++;
		}
		
		if(entity.getX() > farLeftPosition && up == true) {
			entity.setX(entity.getX() - speed);
		} else {
			up = false;
			down = true;
		}
		
		if((entity.getX() + entity.getWidth()) < farRightPosition && down == true) {
			entity.setX(entity.getX() + speed);
		} else {
			up = true;
			down = false;
		}
	}

	public ISlidable setHorizontalDistanceAndSpeed(int distance, int speed) {
		this.distance = distance;
		this.speed = speed;
		return this;
	}

}
