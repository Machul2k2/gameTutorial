package tiles;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {

	public BufferedImage image;
	public boolean collision = false;
	
	public Tile(String path)
	{
		try 
		{
			image = ImageIO.read(getClass().getResourceAsStream(path));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
