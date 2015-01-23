package GameConnections;


import java.awt.Point;

import Game.CpuPlayer;
import GameUtilities.Command;
import GameUtilities.Ship;
import GameUtilities.ShipPosition;
import GameUtilities.ShipType;

public class LocalConnection extends Connection
{
	private CpuPlayer cpuPlayer;

	public LocalConnection() 
	{
		this.cpuPlayer = new CpuPlayer();
	}
	
	@Override
	public boolean isConnectionAvailable() 
	{
		
		return true;
	}

	
	@Override
	public Command receiveCommand()
	{
		Command testCommand = cpuPlayer.receiveCommand();
		
		if (cpuPlayer.receiveCommand() != null)
		{
			
			System.out.println("Command from CPU: [" + testCommand.toString() + "]");
		}
//		return cpuPlayer.receiveCommand();
		return testCommand;
	}

	@Override
	public void sendCommand(Command command) 
	{
		if (command != null && command.isValid()) {
			System.out.println("in local connection send");
			cpuPlayer.sendCommand(command);
//			cpuPlayer.clearCommand();
		}
	}

	@Override
	public void close()
	{
		// TODO Auto-generated method stub
		
	}

	
}
