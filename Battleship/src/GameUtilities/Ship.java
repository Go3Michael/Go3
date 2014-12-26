package GameUtilities;

public class Ship 
{
	private ShipType shipType;
	private ShipPosition shipPosition;
	private String align;
	private int countSector;
	private int number;
	private boolean isAlive = true;
	
	public Ship(ShipPosition shipPosition, ShipType type, int ShipNumber)
	{
		this.shipPosition = shipPosition;
		this.shipType = type;
		this.number = ShipNumber;
	}

	public boolean isAlive()
	{
		return isAlive;
	}
	
	
}
