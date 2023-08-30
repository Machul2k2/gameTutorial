package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import test.GamePanel;
import test.UtilityTool;

public class OBJ_SpeedBoost extends SuperObjects {

	GamePanel gp;
	
	public OBJ_SpeedBoost(GamePanel gp)
	{
		collision = false;
		name = "SpeedBoost";
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream("/objects/speedBoost.png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
}
