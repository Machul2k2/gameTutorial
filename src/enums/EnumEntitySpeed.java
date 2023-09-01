package enums;

public enum EnumEntitySpeed {

	SLOW(2),
	NORMAL(4),
	FAST(6);
	
	public int speed;
	
	EnumEntitySpeed(int speed)
	{
		this.speed = speed;
	}
	
}
