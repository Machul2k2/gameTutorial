package entities;

import test.GamePanel;

public class NPC_Ghost extends Entity {

	public NPC_Ghost(GamePanel gp)
	{
		super(gp);
		direction = "down";
		getNpcImage();
	}
	
	public void getNpcImage()
	{
		this.down = setImage("/npc/NPC_Ghost.png");
	}
}
