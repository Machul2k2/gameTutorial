package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entities.Player;
import objects.SuperObjects;
import tiles.TileManager;

public class GamePanel extends JPanel implements Runnable {
	
	//SCREEN SETTINGS
	public final int originalTileSize = 16;
	public final int scale = 3;
	public final int tileSize = originalTileSize * scale;
	
	public final int maxScreenCol = 10;
	public final int maxScreenRow = 6;
	public final int screenHeightSize = maxScreenRow * tileSize;
	public final int screenWidthSize = maxScreenCol * tileSize;
	
	//WORLD SETTINGS
	public final int maxWorldCol = 20;
	public final int maxWorldRow = 12;
	
	//GAME STATE
	public static final int statePlay = 0;
	public static final int statePause = 1;
	public static int gameState;
	
	int FPS = 60;
	
	public double delta;
	
	Thread gameThread;
	UI ui = new UI(this);
	KeyHandler keyH = new KeyHandler();
	AssetSetter aSetter = new AssetSetter(this);
	Sounds music = new Sounds();
	Sounds soundEffect = new Sounds();
	public SuperObjects[] sObjects = new SuperObjects[10];
	public CollisionChecker cChecker = new CollisionChecker(this);
	public TileManager tileM = new TileManager(this);
	public Player player = new Player(this, keyH);
	
	public GamePanel()
	{
		this.setPreferredSize(new Dimension(screenWidthSize, screenHeightSize));
		this.setBackground(Color.black);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		//this.setDoubleBuffered(false);
	}
	
	public void setupGame()
	{
		aSetter.setAssets();
		gameState = statePlay;
	}
	
	public void startGameThread()
	{
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null)
		{
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			
			lastTime = currentTime;
			
			if(delta >= 1)
			{
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000)
			{
				System.out.println("FPS:" + drawCount);
				timer = 0;
				drawCount = 0;
			}
		}
	}

	public void update() {
		
		if(gameState == statePlay)
		{
			player.update();
		}
		else if(gameState == statePause)
		{
			//NOTHING
		}
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		long drawStart = System.nanoTime();
		
		//TILES
		tileM.draw(g2);
		
		//OBJECTS
		for(int i = 0; i < sObjects.length; i++)
		{
			if(sObjects[i] != null)
			{
				sObjects[i].draw(g2, this);
			}
		}
		
		//PLAYER
		player.draw(g2);
		
		//UI
		ui.draw(g2);
		
		if(keyH.debugMode == true)
		{
			long drawEnd = System.nanoTime();
			long interval = drawEnd - drawStart;
			System.out.println(interval);
		}
		
		g2.dispose();
	}
	
	public void playMusic(int i)
	{
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic()
	{
		music.stop();
	}
	
	public void playSE(int i)
	{
		soundEffect.setFile(i);
		soundEffect.play();
	}
}
