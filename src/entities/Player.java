package entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import enums.EnumEntitySpeed;
import test.GamePanel;
import test.KeyHandler;
import test.UtilityTool;

public class Player extends Entity{

	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH)
	{
		super(gp);
		this.worldX = gp.tileSize;
		this.worldY = gp.tileSize;
		
		entitySpeed = EnumEntitySpeed.NORMAL;
		speed = entitySpeed.speed;
		
		this.keyH = keyH;
		
		this.screenX = gp.screenWidthSize/2 - (gp.tileSize/2);
		this.screenY = gp.screenHeightSize/2 - (gp.tileSize/2);
		
		getPlayerImage();
		
		solidArea = new Rectangle(8, 16, 32, 32);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
	
	public void getPlayerImage()
	{
		this.up = setImage("/player/2.png");
		this.down = setImage("/player/1.png");
		this.left = setImage("/player/3.png");
		this.right = setImage("/player/4.png");
	}
	
	public void update()
	{
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true)
		{
			if(keyH.upPressed == true) {
				direction = "up";
			}
			if(keyH.downPressed == true) {
				direction = "down";
			}
			if(keyH.leftPressed == true) {
				direction = "left";
			}
			if(keyH.rightPressed == true) {
				direction = "right";
			}
			
			//CHECK TILE COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			//CHECK OBJECT COLLISION
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			//IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false)
			{
				switch(direction) 
				{
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
				}
			}
		}
	}
	
	public void pickUpObject(int indexObject)
	{
		if(indexObject != 999)
		{
			gp.sObjects[indexObject] = null;
		}
		if(indexObject == 1)
		{
			speed += 4;
		}
	}
	
	@Override
	public void draw(Graphics2D g2)
	{
		BufferedImage img = null;
		
		switch(direction)
		{
			case "up":
				img = up;
				break;
			case "down":
				img = down;
				break;
			case "left":
				img = left;
				break;
			case "right":
				img = right;
				break;
		}
		
		g2.drawImage(img, screenX, screenY, null);
		g2.draw(solidArea);
	}
}
