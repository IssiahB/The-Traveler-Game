package travel.game.entity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class EntityHandler {
	
	private List<Entity> objects = new ArrayList<Entity>();
	
	public void update() {
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).update(this);
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).render(g);
		}
	}
	
	public void addEntity(Entity entity) {
		objects.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		objects.remove(entity);
	}
	
	public void cleanUp() {
		objects.clear();
	}

	public List<Entity> getObjects() {
		return objects;
	}
}
