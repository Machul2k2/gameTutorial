package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

public class UI {

	GamePanel gp;
	Font arial_40;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	double playTime;
	
	public UI(GamePanel gp)
	{
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN, 20);
	}
	
	public void draw(Graphics2D g2)
	{
		playTime += (double)1/60;
		
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		g2.drawString("Time: " + dFormat.format(playTime), 12, 24);
		
		if(GamePanel.gameState == GamePanel.statePause)
		{
			drawPauseScreen(g2);
		}
		else if(GamePanel.gameState == GamePanel.statePlay)
		{
			
		}
	}

	public void drawPauseScreen(Graphics2D g2) {
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 70F));
		g2.setColor(Color.yellow);
		
		String text = "PAUSED";
		int x = getXfromCenteredText(text, g2);
		int y = gp.screenHeightSize/2;
		
		g2.drawString(text, x, y);
	}
	
	public int getXfromCenteredText(String text, Graphics2D g2)
	{
		int lenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidthSize/2 - lenght/2;
		return x;
	}
	
}
