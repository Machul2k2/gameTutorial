package entities;

import java.awt.Rectangle;

public class Entity {
	
	public int worldX, worldY;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public int speed;
	
	public String direction;
	
	public boolean collisionOn = false;
	public Rectangle solidArea;
	
}
