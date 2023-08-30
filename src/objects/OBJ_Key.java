package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import test.GamePanel;

public class OBJ_Key extends SuperObjects {

	GamePanel gp;
	
	public OBJ_Key(GamePanel gp)
	{
		collision = false;
		name = "Key";
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
