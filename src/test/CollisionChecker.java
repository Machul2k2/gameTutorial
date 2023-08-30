package test;

import entities.Entity;

public class CollisionChecker {

	GamePanel gp;
	
	public CollisionChecker(GamePanel gp)
	{
		this.gp = gp;
	}
	
	public void checkTile(Entity entity)
	{
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(entity.direction)
		{
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
			tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true)
			{
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
			tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true)
			{
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
			tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true)
			{
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
			tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true)
			{
				entity.collisionOn = true;
			}
			break;
		
		}
	}
	
	public int checkObject(Entity entity, boolean player)
	{
		int index = 999;
		
		for(int i = 0; i < gp.sObjects.length; i++)
		{
			if(gp.sObjects[i] != null)
			{
				//get entity's solid area position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				//get the object's solid area position
				gp.sObjects[i].solidArea.x = gp.sObjects[i].worldX + gp.sObjects[i].solidArea.x;
				gp.sObjects[i].solidArea.y = gp.sObjects[i].worldY + gp.sObjects[i].solidArea.y;
				
				switch(entity.direction)
				{
				case "up":
					entity.solidArea.y -= entity.speed;
					if(entity.solidArea.intersects(gp.sObjects[i].solidArea))
					{
						if(gp.sObjects[i].collision == true)
						{
							entity.collisionOn = true;
						}
						if(player == true)
						{
							index = i;
						}
					}
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					if(entity.solidArea.intersects(gp.sObjects[i].solidArea))
					{
						if(gp.sObjects[i].collision == true)
						{
							entity.collisionOn = true;
						}
						if(player == true)
						{
							index = i;
						}
					}
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					if(entity.solidArea.intersects(gp.sObjects[i].solidArea))
					{
						if(gp.sObjects[i].collision == true)
						{
							entity.collisionOn = true;
						}
						if(player == true)
						{
							index = i;
						}
					}
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					if(entity.solidArea.intersects(gp.sObjects[i].solidArea))
					{
						if(gp.sObjects[i].collision == true)
						{
							entity.collisionOn = true;
						}
						if(player == true)
						{
							index = i;
						}
					}
					break;
				}
				
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.sObjects[i].solidArea.x = gp.sObjects[i].solidAreaDefaultX;
				gp.sObjects[i].solidArea.y = gp.sObjects[i].solidAreaDefaultY;
				
			}
		}
		
		return index;
	}
	
}
