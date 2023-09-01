package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import test.GamePanel;
import test.UtilityTool;

public class OBJ_Key extends SuperObjects {

	GamePanel gp;
	
	public OBJ_Key(GamePanel gp)
	{
		collision = false;
		name = "Key";
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
			image = UtilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
