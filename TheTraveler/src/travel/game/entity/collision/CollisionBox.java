package travel.game.entity.collision;

import travel.game.entity.Block;
import travel.game.entity.Enemy;
import travel.game.entity.Entity;
import travel.game.entity.ExitBlock;
import travel.game.entity.Player;

public class CollisionBox {
	
	private Entity mainEntity;
	private int pushBackDistance = 10;
	
	public CollisionBox(Entity entity) {
		mainEntity = entity;
	}
	
	private boolean touchTop(Entity entity) {
		if ((entity.getY() + entity.getHeight()) < (mainEntity.getY()
				+ pushBackDistance)) {
			return true;
		}
		
		return false;
	}
	
	private boolean touchBottom(Entity entity) {
		if (entity.getY() > (mainEntity.getY()
				+ mainEntity.getHeight() - pushBackDistance)) {
			return true;
		}
		
		return false;
	}
	
	private boolean touchLeft(Entity entity) {
		if ((entity.getX() + entity.getWidth()) < (mainEntity.getX()
				+ pushBackDistance)) {
			return true;
		}
		
		return false;
	}
	
	private boolean touchRight(Entity entity) {
		if (entity.getX() > (mainEntity.getX() + mainEntity.getWidth()
				- pushBackDistance)) {
			return true;
		}
		
		return false;
	}
	
	public void suckInAndShrink(Entity entity) {
		int entitiesMidX = (entity.getX() + entity.getWidth()) / 2;
		int entitiesMidY = (entity.getY() + entity.getHeight()) / 2;
		
		int ourMidX = (mainEntity.getX() + mainEntity.getWidth()) / 2;
		int ourMidY = (mainEntity.getY() + mainEntity.getHeight())
				/ 2;
		
		if (entitiesMidX > ourMidX) {
			entity.setX(entity.getX() - 4);
		}
		if (entitiesMidX < ourMidX) {
			entity.setX(entity.getX() + 4);
		}
		
		if (entitiesMidY > ourMidY) {
			entity.setY(entity.getY() - 4);
		}
		if (entitiesMidY < ourMidY) {
			entity.setY(entity.getY() + 4);
		}
		
		entity.setWidth((entity.getWidth() - 5));
		entity.setHeight((entity.getHeight() - 5));
	}
	
	public void pushBack(Entity entity) {
		if (touchTop(entity)) {
			entity.setY((mainEntity.getY() - entity.getHeight()));
		}
		
		if (touchBottom(entity)) {
			entity.setY((mainEntity.getY() + mainEntity.getHeight()));
		}
		
		if (touchLeft(entity)) {
			entity.setX((mainEntity.getX() - entity.getWidth()));
		}
		
		if (touchRight(entity)) {
			entity.setX((mainEntity.getX() + mainEntity.getWidth()));
		}
	}
	
	// loops threw all the entities that are passed threw
	// and if one of them are an instance of the Player class
	// return true else return false
	public boolean touchedByPlayer(Entity entity) {
		if (entity.getBounds().intersects(mainEntity.getBounds())) {
			if (entity instanceof Player)
				return true;
		}
		
		return false;
	}
	
	// loops threw all the entities that are passed threw
	// and if one of them are an instance of the Block class
	// return true else return false
	public boolean touchedByBlock(Entity entity) {
		if (entity.getBounds().intersects(mainEntity.getBounds())) {
			if (entity instanceof Block)
				return true;
		}
		
		return false;
	}
	
	public boolean touchedByEnemy(Entity entity) {
		if (entity.getBounds().intersects(mainEntity.getBounds())) {
			if (entity instanceof Enemy)
				return true;
		}
		
		return false;
	}
	
	public boolean touchedByExit(Entity entity) {
		if (entity.getBounds().intersects(mainEntity.getBounds())) {
			if (entity instanceof ExitBlock)
				return true;
		}
		
		return false;
	}
	
	public boolean touchedByAnything(Entity entity) {
		if (entity.getBounds().intersects(mainEntity.getBounds()))
			return true;
		
		return false;
	}
}
