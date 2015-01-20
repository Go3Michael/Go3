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
		
		 return cpuPlayer.receiveCommand();		
	}

	@Override
	public void sendCommand(Command command) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close()
	{
		// TODO Auto-generated method stub
		
	}

	
}
