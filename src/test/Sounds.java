package test;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sounds {

	Clip clip;
	URL soundURL[] = new URL[20];
	
	public Sounds()
	{
		soundURL[0] = getClass().getResource("/sounds/");
	}
	
	public void setFile(int i)
	{
		try
		{
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void play()
	{
		clip.start();
	}
	
	public void loop()
	{
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop()
	{
		clip.stop();
	}
}
