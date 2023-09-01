package entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import enums.EnumEntitySpeed;
import test.GamePanel;
import test.UtilityTool;

public class Entity {
	
	GamePanel gp;
	BufferedImage up, down, left, right;
	EnumEntitySpeed entitySpeed;
	public int worldX, worldY;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public int speed;
	public String direction = "down";
	
	public boolean collisionOn = false;
	public Rectangle solidArea;
	
	public Entity(GamePanel gp)
	{
		this.gp = gp;
	}
	
	public BufferedImage setImage(String imagePath)
	{
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath));
			image = UtilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}

	public void draw(Graphics2D g2) {
		
		BufferedImage img = down;
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
		   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
		   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY &&
		   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY)
		{	
			g2.drawImage(img, screenX, screenY, null);
		}
	}
	
}
