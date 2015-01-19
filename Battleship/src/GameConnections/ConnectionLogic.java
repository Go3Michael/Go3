package GameConnections;

import GameUtilities.Command;

public class ConnectionLogic
{
	Connection connection;

	public ConnectionLogic(Connection connection)
	{
		this.connection = connection;		
	}
	
	public void sendCommandToPlayer(Command commandSend)
	{
		this.connection.sendCommand(commandSend);
	}
	
	public Command getCommandFromPlayer()
	{
		Command commandRecieve = this.connection.receiveCommand();
		
		return commandRecieve;
	}
	
	public void closeConnection()
	{
		this.connection.close();
	}
	
}
