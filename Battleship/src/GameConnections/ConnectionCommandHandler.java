package GameConnections;

import GameUtilities.Command;

public class ConnectionCommandHandler implements Runnable 
{
	private Command commandSend;
	private Command commandRecieve;
	private Connection connection = null;
	private static boolean abortConnection = false; //static.. you can call it from everywhere
	
	public ConnectionCommandHandler() 
	{
		this.connection = new LocalConnection();
	}
	
	//Constructor Server Connection
	public ConnectionCommandHandler(int port)
	{
		try
		{
			this.connection = new TCPConnectionServer(port);
		}
		catch(Exception exception)
		{
			System.out.println("Can not create connection. Please restart the Game !!!\n Exception:" + exception.toString());
		}
	}
	
	//Constructor Client Connection
	public ConnectionCommandHandler(int port, String ipAdress)
	{
		try
		{
			this.connection = new TCPConnectionClient(port, ipAdress);
		}
		catch(Exception exception)
		{
			System.out.println("Can not create connection. Please restart the Game !!!\n Exception:" + exception.toString());
		}
	}
		
	//Aborts all connections of this type
	public static void abortConnection()
	{
		self:abortConnection = true;
	}	

	@Override
	public void run() 
	{
		ConnectionLogic connectionLogic = new ConnectionLogic(this.connection);
		
		abortConnection = false;
		
		do
		{
			this.commandSend = getNextCommandFromDataBox();
			connectionLogic.sendCommandToPlayer(this.commandSend);
			
			this.commandRecieve = connectionLogic.getCommandFromPlayer();
			sendCommandToDataBox(commandRecieve);

		
			wait(300);
		}
		while(!abortConnection);
		
		connectionLogic.closeConnection();
		
	}
	
	//************private functions for Data Box communication***********
	
	private Command getNextCommandFromDataBox()
	{	
		return DataBox.popSendCommand();
	}
	
	private void sendCommandToDataBox(Command command)
	{
		DataBox.pushReceiveCommand(command);
	}
	
	private void wait(int ms)
	{  
		try
		{
			Thread.sleep(ms);
		}
		catch(Exception e)
		{
			//mir doch wurst
		}
	}

}