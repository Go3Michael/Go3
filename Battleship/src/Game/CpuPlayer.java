package Game;

import java.awt.Point;

import java.util.Random;
import java.util.Vector;

import GameUtilities.Command;
import GameUtilities.Ship;
import GameUtilities.ShipPosition;
import GameUtilities.ShipType;
import GameUtilities.Field.Field;

/**
 * logic of the CPU player
 * 
 * @author Schoenegger / Purkart / Koch
 *
 */
public class CpuPlayer
{
    CpuPlayerLogic cpuPlayerLogic = new CpuPlayerLogic();

    public CpuPlayer()
    {
    	
    }
   
    /**
     * Receive the next command
     * 
     * @return command
     */
    public Command receiveCommand()
    {
    	return cpuPlayerLogic.getNextCommand();
    }
    
    /**
     * Send the command from CPU player
     * 
     * @param command
     */
    public void sendCommand(Command command)
    {
    	cpuPlayerLogic.sendCommand(command);
    }
    


   
}
