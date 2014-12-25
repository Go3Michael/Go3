package GameUtilities;

public abstract class Ship 
{
	private ShipPosition shipPosition;
	private int countSector;
	
	public Ship()
	{
		
	}
	
	public Ship(ShipPosition shipPosition)
	{
		this.shipPosition = shipPosition;
	}

	public boolean isAlive()
	{
		return true;
	}
	
	
}
