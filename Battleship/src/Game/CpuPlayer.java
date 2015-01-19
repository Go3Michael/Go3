package Game;

import java.awt.Point;
import java.util.Random;
import java.util.Vector;

import GameUtilities.Command;
import GameUtilities.Ship;
import GameUtilities.ShipPosition;
import GameUtilities.ShipType;
import GameUtilities.Field.Field;

public class CpuPlayer
{
    CpuPlayerLogic cpuPlayerLogic = new CpuPlayerLogic();

    public CpuPlayer()
    {
    	
    }
   
    public Command receiveCommand()
    {
    	return cpuPlayerLogic.getNextCommand();
    }
    
    public void sendCommand(Command command)
    {
    	cpuPlayerLogic.sendCommand(command);
    }
    
	
  
	

}
