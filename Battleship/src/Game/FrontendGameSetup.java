package Game;

import java.awt.Point;

import GameUtilities.Ship;
import GameUtilities.ShipPosition;
import GameUtilities.ShipType;
import GameUtilities.Field.Field;

public class FrontendGameSetup 
{
	private Field fieldToSet;
	
	public boolean callMenue()
	{
		return true;
	}
	
	public Field getFinischedField()
	{
		createTestObjectField();
		return this.fieldToSet;
	}
	
	private void createTestObjectField()
	{
		Ship ship = new Ship(new ShipPosition(new Point(5,5),"vertical"), ShipType.AIRCARRER, 1);
		fieldToSet.setShipOnField(ship);
		ship = new Ship(new ShipPosition(new Point(3,3),"vertical"), ShipType.AIRCARRER, 2);
		fieldToSet.setShipOnField(ship);
		ship = new Ship(new ShipPosition(new Point(7,7),"vertical"), ShipType.AIRCARRER, 3);
		fieldToSet.setShipOnField(ship);
	}

}
