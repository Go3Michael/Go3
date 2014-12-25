package GameUtilities.Field;

import java.util.Vector;

import GameUtilities.Ship;
import GameUtilities.ShipPosition;

public class Field 
{
	private Vector<Ship> shipsInField;
	private ShipPosition shipPosition;
	//Array of FieldStates 10x10--??
	
	public Vector<Ship> getListOfActiveShips()
	{	
		Vector<Ship> activeShipsInField = new Vector<Ship>();
		
		for(Ship ship : shipsInField)
		{
			if(ship.isAlive())
			{
				activeShipsInField.addElement(ship);
			}
		}
		
		return activeShipsInField;
	}
	
	
	
}
