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
	// TODO check and delete if not needed
    }

    /**
     * Get the next Command
     * 
     * @return command
     */
    public Command getNextCommand()
    {
	// System.out.println("return Command from Cpu Logic: " + nextReturnCommand.toString());
	return nextReturnCommand;
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

    /**
     * Build the Field command
     * 
     * @return command
     */
    private Command buildFieldCommand()
    {
	Command fieldCommand = new Command(1, ownField, "INIT_FIELD");

	return fieldCommand;

    }

    /**
     * get the answer command
     * 
     * @return command
     */
    private Command getAnswerCommand()
    {
	Point point = enemyField.getRandomfreeFieldCoordinate();
	// TODO return null?
	return null;
    }

    /**
     * build attac command
     * 
     * @param point
     * @return command
     */
    private Command buildAttacCommand(Point point)
    {
	Command attacCommand = new Command(1, new AttackPosition(point), "INIT_FIELD");

	return attacCommand;
    }

    /**
     * get coordinates from attac command
     * 
     * @param command
     * @return integer
     */
    private int[] getCoordinatesfromAttacCommand(Command command)
    {
	AttackPosition attackPos = (AttackPosition) command.getCommandData();
	Point point = attackPos.getXyPosition();
	int pointCoordinates[] = new int[2];
	pointCoordinates[0] = point.x;
	pointCoordinates[1] = point.y;

	return pointCoordinates;
    }

    /**
     * Check the command whether it is a init field command
     * 
     * @param command
     * @return boolean
     */
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

    /**
     * Set enemy field
     * 
     * @param command
     */
    private void setEnemyField(Command command)
    {
	enemyField = (Field) command.getCommandData();
    }

    /**
     * Check the command whether it is a init field command
     * 
     * @param command
     * @return
     */
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

    /**
     * set the ships on the own field
     */
    private void setShipsOnOwnField()
    {
	// simple ship init for Alpha Version
	Ship ship1 = new Ship(new ShipPosition(new Point(2, 2), "HORIZONTAL"), ShipType.AIRCARRIER, 1);
	Ship ship2 = new Ship(new ShipPosition(new Point(2, 5), "HORIZONTAL"), ShipType.AIRCARRIER, 1);
	Ship ship3 = new Ship(new ShipPosition(new Point(5, 8), "HORIZONTAL"), ShipType.AIRCARRIER, 1);

	ownField.setShipOnField(ship1);
	ownField.setShipOnField(ship2);
	ownField.setShipOnField(ship3);
	ownField.displayField();
    }

}
