package tiles;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import test.GamePanel;
import test.UtilityTool;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp)
	{
		this.gp = gp;
		tile = new Tile[10];
		mapTileNum = new int[gp.maxWorldRow][gp.maxWorldCol];
		
		getTileImage();
		importMap();
	}
	
	public void importMap() {
		try
		{
			InputStream iStream = getClass().getResourceAsStream("/maps/map01.txt");
			BufferedReader bRead = new BufferedReader(new InputStreamReader(iStream));
			
			int row = 0;
			int col = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow)
			{
				String line = bRead.readLine();
				while(col < gp.maxWorldCol)
				{
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[row][col] = num;
					
					col++;
				}
				if(col == gp.maxWorldCol)
				{
					col = 0;
					row++;
				}
			}
			bRead.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void getTileImage()
	{
		setup(0, "background", false);
		setup(1, "wall", true);
		setup(2, "wall2", false);
	}
	
	public void setup(int index, String imagePath, boolean collision)
	{
		UtilityTool uTool = new UtilityTool();
		
		tile[index] = new Tile("/tiles/" + imagePath + ".png");
		tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
		tile[index].collision = collision;
	}
	
	public void draw(Graphics2D g2)
	{	
		for(int worldRow = 0; worldRow < mapTileNum.length; worldRow++)
		{
			for(int worldCol = 0; worldCol < mapTileNum[worldRow].length; worldCol++)
			{
				int worldX = worldCol * gp.tileSize;
				int worldY = worldRow * gp.tileSize;
				int screenX = worldX - gp.player.worldX + gp.player.screenX;
				int screenY = worldY - gp.player.worldY + gp.player.screenY;
				
				if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
				   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
				   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY &&
				   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY)
				{
					g2.drawImage(tile[mapTileNum[worldRow][worldCol]].image, screenX, screenY, null);
				}
			}
		}
	}
}
