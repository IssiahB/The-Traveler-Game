package travel.game.entity.slide;

import travel.game.entity.Entity;

public interface ISlidable {
	public void slide(Entity entity);
	public int getDistance();
	public int getSpeed();
}