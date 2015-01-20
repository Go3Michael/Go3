package GameConnections;

import Game.GlobalGameData;
import GameUtilities.Command;

public class ConnectionCommandHandler implements Runnable
{
	private ConnectionLogic connectionLogic;
	private Command commandSend;
	private Command commandRecieve;
	private Connection connection = null;

	private static boolean abortConnection = false; // static.. you can call it from everywhere

	public ConnectionCommandHandler()
	{
		this.connection = new LocalConnection();
	}

	// Constructor Server Connection
	public ConnectionCommandHandler(int port)
	{
		GlobalGameData.setIsMyTurn(true);

		try
		{
			this.connection = new TCPConnectionServer(port);
			this.connectionLogic = new ConnectionLogic(connection);
		}
		catch (Exception exception)
		{
			System.out.println("Can not create connection. Please restart the Game !!!\n Exception:"
					+ exception.toString());
		}
	}

	// Constructor Client Connection
	public ConnectionCommandHandler(int port, String ipAdress)
	{
		GlobalGameData.setIsMyTurn(false);

		try
		{
			this.connection = new TCPConnectionClient(port, ipAdress);
			this.connectionLogic = new ConnectionLogic(connection);
		}
		catch (Exception exception)
		{
			System.out.println("Can not create connection. Please restart the Game !!!\n Exception:"
					+ exception.toString());
		}
	}

	// Aborts all connections of this type
	public static void abortConnection()
	{
		self: abortConnection = true;
	}

	@Override
	public void run()
	{
		abortConnection = false;
		System.out.println("Thread begin");

		do
		{
			commandSend = getNextCommandFromDataBox();
			if (commandSend == null)
			{
				continue;
			}

			connectionLogic.sendCommandToPlayer(commandSend);

			this.commandRecieve = connectionLogic.getCommandFromPlayer();
			sendCommandToDataBox(commandRecieve);
			wait(500);
		}
		while (!abortConnection);

		System.out.println("Thread end");

		connectionLogic.closeConnection();
	}

	// ************private functions for Data Box communication***********

	private Command getNextCommandFromDataBox()
	{
		return DataBox.popSendCommand();
	}

	private void sendCommandToDataBox(Command command)
	{
		// System.out.print(arg0);
		DataBox.pushReceiveCommand(command);
	}

	private void wait(int ms)
	{
		try
		{
			Thread.sleep(ms);

		}
		catch (Exception e)
		{
			// mir doch wurst
		}
	}

}