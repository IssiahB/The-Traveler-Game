package travel.game.entity.slide;

import travel.game.entity.Entity;

public class SlideNone implements ISlidable{

	@Override
	public void slide(Entity entity) {}

	@Override
	public int getDistance() {
		return 0;
	}

	@Override
	public int getSpeed() {
		return 0;
	}

}
