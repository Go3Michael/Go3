package Game;

import java.awt.Point;

import GameUtilities.Command;
import GameUtilities.Ship;
import GameUtilities.ShipPosition;
import GameUtilities.ShipType;
import GameUtilities.AttackPosition.AttackPosition;
import GameUtilities.Field.Field;

/**
 * Contains the logic oft the CPU player
 * 
 * @author Schoenegger / Purkart / Koch
 */
public class CpuPlayerLogic
{

	Field ownField = new Field();
	Field enemyField = new Field();
	Command nextReturnCommand;

	public CpuPlayerLogic()
	{

	}

	/**
	 * Get the next Command
	 * 
	 * @return command
	 */
	public Command getNextCommand()
	{
		Command actualCommand = nextReturnCommand;
		nextReturnCommand = null;
		return actualCommand;
	}

	/**
	 * Send the command
	 * 
	 * @param command
	 */
	public void sendCommand(Command command)
	{
		System.out.println("command arrived at CPU Player Logic");
		if (command == null)
		{
			System.out.println("send command Cpu PlayerLogic null objectCPU");
		}

		if (isCommandInitField(command))
		{
			setEnemyField(command);
			setShipsOnOwnField();
			nextReturnCommand = buildFieldCommand();
		}
		else if (isCommandAttacCommand(command))
		{
			int points[] = getCoordinatesfromAttacCommand(command);
			ownField.fireToPosition(points[0], points[1]);
			nextReturnCommand = getAnswerCommand();
		}
	}

	private Command buildFieldCommand()
	{
		Command fieldCommand = new Command(1, ownField, "INIT_FIELD");

		return fieldCommand;
	}

	private Command getAnswerCommand()
	{
		Point point = enemyField.getRandomfreeFieldCoordinate();
		return buildAttacCommand(point);
	}

	private Command buildAttacCommand(Point point)
	{
		Command attacCommand = new Command(1, new AttackPosition(point), "ATTAC_COMMAND");

		return attacCommand;
	}

	private int[] getCoordinatesfromAttacCommand(Command command)
	{
		AttackPosition attackPos = (AttackPosition) command.getCommandData();
		Point point = attackPos.getXyPosition();
		int pointCoordinates[] = new int[2];
		pointCoordinates[0] = point.x;
		pointCoordinates[1] = point.y;

		return pointCoordinates;
	}

	private boolean isCommandInitField(Command command)
	{
		if (command.getType().equals("INIT_FIELD"))
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
		enemyField = (Field) command.getCommandData();
	}

	private boolean isCommandAttacCommand(Command command)
	{
		if (command.getType().equals("ATTAC_COMMAND"))
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
		// simple ship init for Alpha Version
		Ship ship1 = new Ship(new ShipPosition(new Point(2, 2), "HORIZONTAL"), ShipType.AIRCARRIER, 1);
		Ship ship2 = new Ship(new ShipPosition(new Point(4, 5), "HORIZONTAL"), ShipType.AIRCARRIER, 1);
		Ship ship3 = new Ship(new ShipPosition(new Point(1, 4), "HORIZONTAL"), ShipType.AIRCARRIER, 1);

		ownField.setShipOnField(ship1);
		ownField.setShipOnField(ship2);
		ownField.setShipOnField(ship3);
		ownField.setTaken();
	}

}
