package GameUtilities.Field;

import java.awt.Point;
import java.util.Vector;

import GameUtilities.Ship;
import GameUtilities.ShipPosition;

public class Field 
{
	private Vector<Ship> shipsOnField = new Vector<Ship>();
	private FieldElement[][] fieldElemtens;
	
	//Array of FieldElements 10x10--??
	
	public Field()
	{
		fieldElemtens = new FieldElement[10][10];
		initNewField();
	}
	
	private void initNewField()
	{
		for(int i = 0; i<=9; i++)
		{
			for(int j = 0; j<=9; j++)
			{
				fieldElemtens[i][j] = new FieldElement();
				fieldElemtens[i][j].setFieldState(FieldState.UNKNOWN); 
			}
		}
	}
	
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
	
	public boolean checkIfAllShipsAreCountersunk()
	{
		for(int i = 0; i<=9; i++)
		{
			for(int j = 0; j<=9; j++)
			{
				if(fieldElemtens[i][j].isTaken() && fieldElemtens[i][j].getFieldState() != FieldState.STRIKE_SHIP)
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	public Point getRandomfreeFieldCoordinate()
	{
		Point freePosition;
		
		for(int i = 0; i<=9; i++)
		{
			for(int j = 0; j<=9; j++)
			{
				if (fieldElemtens[i][j].getFieldState() == FieldState.STRIKE_SHIP) {
					//TODO
				}
				if(fieldElemtens[i][j].getFieldState() == FieldState.UNKNOWN)
				{
					freePosition = new Point(i+1,j+1);
					return freePosition;
				}
			}
		}
		return new Point(0,0);
	}
	
	public boolean IsValidAttacPosition(int posX,int posY)
	{
		
 		if(this.fieldElemtens[posX][posY].getFieldState() == FieldState.UNKNOWN)
 		{
 			return true;
 		}
 		else
 		{
 			return false;
 		}
		
	}
	
	//TODO maybe second fireToPosition
	public boolean fireToPosition(int posX, int posY)
	{
		if(fieldElemtens[posX][posY].isTaken())
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
	
	@Override
	public String toString()
	{
		String transferDataString = "";
		
		for(Ship ship : shipsOnField)
		{
			transferDataString += ship.toTransferString();
		}
		
		return transferDataString;
	}
	
	public void display()
	{
		System.out.println(" 1 2 3 4 5 6 7 8 9 10");
		String printField = "_____________________";
		for(int i = 0; i<=9; i++)
		{
			printField += "\n";
			
			for(int j = 0; j<=9; j++)
			{
				if (fieldElemtens[i][j].isTaken())
				{
					printField += "|S";
				}
				else if (fieldElemtens[i][j].getFieldState() == FieldState.STRIKE_SHIP) {
					printField += "|X";
				}
				else if (fieldElemtens[i][j].getFieldState() == FieldState.STRIKE_WATER) {
					printField += "|x";
				}
				else {
					printField += "| ";
				}
			}
			printField += "|";
			printField += Integer.toString(i+1);
		}
		System.out.println(printField);
	}
}
