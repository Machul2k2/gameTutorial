package test;

import entities.NPC_Ghost;
import objects.OBJ_Key;
import objects.OBJ_SpeedBoost;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp)
	{
		this.gp = gp;
	}
	
	public void setAssets()
	{
		gp.sObjects[0] = new OBJ_Key(gp);
		gp.sObjects[0].worldX = 3 * gp.tileSize;
		gp.sObjects[0].worldY = 4 * gp.tileSize;
		
		gp.sObjects[1] = new OBJ_SpeedBoost(gp);
		gp.sObjects[1].worldX = 3 * gp.tileSize;
		gp.sObjects[1].worldY = 6 * gp.tileSize;
	}
	
	public void setnpc()
	{
		gp.npc[0] = new NPC_Ghost(gp);
		gp.npc[0].worldX = 4 * gp.tileSize;
		gp.npc[0].worldY = 4 * gp.tileSize;
	}
}
