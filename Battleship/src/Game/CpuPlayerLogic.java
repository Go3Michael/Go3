package Game;

import java.awt.Point;

import GameUtilities.Command;
import GameUtilities.Ship;
import GameUtilities.ShipPosition;
import GameUtilities.ShipType;
import GameUtilities.Field.Field;

public class CpuPlayerLogic 
{

	Field ownField = new Field();
	Field enemyField = new Field();
	
	public CpuPlayerLogic()
	{
		
	}
	
	public Command getNextCommand()
	{
		return null;
	}
	
	public void sendCommand(Command command)
	{
		if(isCommandInitField(command))
		{
			setEnemyField(command);
			setShipsOnOwnField();
		}
		else if(isCommandAttacCommand(command))
		{
			
		}
	}

	private boolean isCommandInitField(Command command)
	{
		if(command.getType().equals("INIT_FIELD"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private void setEnemyField(Command command) 
	{
		enemyField = (Field)command.getCommandData();		
	}
	
	private boolean isCommandAttacCommand(Command command)
	{
		if(command.getType().equals("ATTAC_COMMAND"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	private void setShipsOnOwnField()
	{
		//simple ship init for Alpha Version
		Ship ship1 = new Ship(new ShipPosition(new Point(2,2),"HORIZONTAL"),ShipType.AIRCARRIER,1);
		Ship ship2 = new Ship(new ShipPosition(new Point(2,5),"HORIZONTAL"),ShipType.AIRCARRIER,1);
		Ship ship3 = new Ship(new ShipPosition(new Point(5,8),"HORIZONTAL"),ShipType.AIRCARRIER,1);
		
		ownField.setShipOnField(ship1);
		ownField.setShipOnField(ship2);
		ownField.setShipOnField(ship3);
	}
}
