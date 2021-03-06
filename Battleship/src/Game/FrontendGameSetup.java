package Game;

import java.awt.Point;

import GameUtilities.Ship;
import GameUtilities.ShipPosition;
import GameUtilities.ShipType;
import GameUtilities.Field.Field;

/**
 * Initialise the Game
 * 
 * @author Schoenegger / Purkart / Koch
 *
 */
public class FrontendGameSetup
{
	private Field fieldToSet = new Field();

	/**
	 * call Menu
	 * 
	 * @return boolean
	 */
	public boolean callMenue()
	{
		return true;
	}

	/**
	 * Get the finished Field
	 * 
	 * @return Field
	 */
	public Field getFinischedField()
	{
		createTestObjectField();
		return this.fieldToSet;
	}

	private void createTestObjectField()
	{
		System.out.println("Field automatic init done");
		Ship ship = new Ship(new ShipPosition(new Point(5, 5), "VERTICAL"), ShipType.AIRCARRIER, 1);
		fieldToSet.setShipOnField(ship);
		ship = new Ship(new ShipPosition(new Point(3, 3), "VERTICAL"), ShipType.DESTROYER, 2);
		fieldToSet.setShipOnField(ship);
		ship = new Ship(new ShipPosition(new Point(7, 7), "VERTICAL"), ShipType.YELLOW_SUBMARINE, 3);
		fieldToSet.setShipOnField(ship);
		fieldToSet.setTaken();
	}

}
