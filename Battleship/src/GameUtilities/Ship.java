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
	
		switch (this.shipType)
		{
		    case AIRCARRIER: countSector = 5;
			break;
		    case DESTROYER: countSector = 4;
				break;
		    default:	countSector = 3;
			break;
		}
	
	}
	
	public String toTransferString()
	{
		return "-" + Integer.toString(number) + ";" + shipType.toString() + ";" + shipPosition.toString() + ";" + align ;
	}

	public boolean isAlive()
	{
		return isAlive;
	}
	
	public int getCountSector(){
	    return this.countSector;
    	}
    
}
