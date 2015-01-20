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
		String type = "";
		switch (this.shipType) 
		{
			case AIRCARRIER:
				type = "AIRCARRIER";
				break;
			case DESTROYER:
				type = "DESTROYER";
				break;
			case YELLOW_SUBMARINE:
				type = "YELLOW_SUBMARINE";
				break;
			default:
				break;
		}

		return "-" + Integer.toString(number) + "," + type + "," + shipPosition.toString();
	}

	public boolean isAlive()
	{
		return isAlive;
	}
	
	public int getCountSector(){
	    return this.countSector;
    	}
    
}
