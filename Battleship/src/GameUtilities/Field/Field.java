package GameUtilities.Field;

import java.awt.Point;
import java.util.Vector;

import GameUtilities.Ship;
import GameUtilities.ShipPosition;

public class Field 
{
	private Vector<Ship> shipsOnField = new Vector<Ship>();
	
	//Array of FieldElements 10x10--??
	
	public Vector<Ship> getListOfActiveShips()
	{	
		Vector<Ship> activeShipsInField = new Vector<Ship>();
		
		for(Ship ship : shipsOnField)
		{
			if(ship.isAlive())
			{
				activeShipsInField.addElement(ship);
			}
		}		
		return activeShipsInField;
	}
	
	public void setShipOnField(Ship ship)
	{
		shipsOnField.addElement(ship);
	}
	
	public String toTransferDataString()
	{
		String transferDataString = "";
		
		for(Ship ship : shipsOnField)
		{
			transferDataString += ship.toTransferString();
		}
		
		return transferDataString;
	}
}
