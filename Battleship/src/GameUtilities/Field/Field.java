package GameUtilities.Field;

import java.awt.Point;
import java.util.Vector;

import GameUtilities.Ship;
import GameUtilities.ShipPosition;

/**
 * 
 * @author Schoenegger / Purkart / Koch
 *
 */
public class Field
{
    private Vector<Ship> shipsOnField = new Vector<Ship>();
    private FieldElement[][] fieldElemtens;

    // Array of FieldElements 10x10--??
    /**
     * Consturctor
     */
    public Field()
    {
	fieldElemtens = new FieldElement[10][10];
	initNewField();
    }

    private void initNewField()
    {
	for (int i = 0; i <= 9; i++)
	{
	    for (int j = 0; j <= 9; j++)
	    {
		fieldElemtens[i][j] = new FieldElement();
		fieldElemtens[i][j].setFieldState(FieldState.UNKNOWN);
	    }
	}
    }

    /**
     * get List of active ships
     * 
     * @return Vector<Ship>
     */
    public Vector<Ship> getListOfActiveShips()
    {
	Vector<Ship> activeShipsInField = new Vector<Ship>();

	for (Ship ship : shipsOnField)
	{
	    if (ship.isAlive())
	    {
		activeShipsInField.addElement(ship);
	    }
	}
	return activeShipsInField;
    }

    /**
     * Set ship on field
     * 
     * @param ship
     * 
     */
    public void setShipOnField(Ship ship)
    {
	shipsOnField.addElement(ship);
    }

    /**
     * Check if all ships are countersunk
     * 
     * @return boolean
     */
    public boolean checkIfAllShipsAreCountersunk()
    {
	for (int i = 0; i <= 9; i++)
	{
	    for (int j = 0; j <= 9; j++)
	    {
		// TODO wird der Kommentar noch benötigt??
		// if(fieldElemtens[i][j].isTaken() && fieldElemtens[i][j].getFieldState() == FieldState.STRIKE_SHIP)
		// {
		// allSunk = true;
		// }
		if (fieldElemtens[i][j].isTaken())
		{
		    if (fieldElemtens[i][j].getFieldState() != FieldState.STRIKE_SHIP)
		    {
			return false;
		    }
		}
	    }
	}

	// return false;
	return true;
    }

    /**
     * get Random free Field Coordinate
     * 
     * @return Point
     */
    public Point getRandomfreeFieldCoordinate()
    {
	Point freePosition;

	for (int i = 0; i <= 9; i++)
	{
	    for (int j = 0; j <= 9; j++)
	    {
		if (fieldElemtens[i][j].getFieldState() == FieldState.UNKNOWN)
		{
		    freePosition = new Point(i + 1, j + 1);
		    return freePosition;
		}
	    }
	}
	return new Point(0, 0);
    }

    /**
     * check if the attack position is valide
     * 
     * @param posX
     * @param posY
     * @return boolean
     */
    public boolean IsValidAttacPosition(int posX, int posY)
    {

	if (this.fieldElemtens[posX][posY].getFieldState() == FieldState.UNKNOWN)
	{
	    return true;
	}
	else
	{
	    return false;
	}

    }

    /**
     * Fire to the Position
     * 
     * @param posX
     * @param posY
     * @return boolean
     */
    // TODO maybe second fireToPosition
    public boolean fireToPosition(int posX, int posY)
    {
	if (fieldElemtens[posX][posY].isTaken())
	{
	    fieldElemtens[posX][posY].setFieldState(FieldState.STRIKE_SHIP);
	    return true;
	}
	else
	{
	    fieldElemtens[posX][posY].setFieldState(FieldState.STRIKE_WATER);
	}

	return false;
    }

    /**
     * Generate the transferstring
     * 
     * @return String
     */
    @Override
    public String toString()
    {
	String transferDataString = "";

	for (Ship ship : shipsOnField)
	{
	    transferDataString += ship.toTransferString();
	}

	return transferDataString;
    }

    /**
     * Display the Field
     */
    public void display()
    {
	System.out.println(" 1 2 3 4 5 6 7 8 9 10");
	String printField = "_____________________";
	for (int i = 0; i <= 9; i++)
	{
	    printField += "\n";

	    for (int j = 0; j <= 9; j++)
	    {
		if (fieldElemtens[i][j].isTaken())
		{
		    printField += "|S";
		}
		else if (fieldElemtens[i][j].getFieldState() == FieldState.STRIKE_SHIP)
		{
		    printField += "|X";
		}
		else if (fieldElemtens[i][j].getFieldState() == FieldState.STRIKE_WATER)
		{
		    printField += "|x";
		}
		else
		{
		    printField += "| ";
		}
	    }
	    printField += "|";
	    printField += Integer.toString(i + 1);
	}
	System.out.println(printField);
    }
}
