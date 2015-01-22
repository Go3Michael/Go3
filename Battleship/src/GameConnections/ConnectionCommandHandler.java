package GameConnections;

import Game.GlobalGameData;
import GameUtilities.Command;

/**
 * Handler for the connection commands
 * 
 * @author Schoenegger / Purkart / Koch
 */
public class ConnectionCommandHandler implements Runnable
{
    private ConnectionLogic connectionLogic;
    private Command commandSend;
    private Command commandReceive;
    private Connection connection = null;

    private static boolean abortConnection = false; // static.. you can call it from everywhere

    /**
     * Constructor
     */
    public ConnectionCommandHandler()
    {
	this.connection = new LocalConnection();
	this.connectionLogic = new ConnectionLogic(connection);
    }

    /**
     * Constructor Server Connection
     * 
     * @param port
     */
    public ConnectionCommandHandler(int port)
    {
	// GlobalGameData.setIsMyTurn(true);

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

    /**
     * Constructor Client Connection
     * 
     * @param port
     * @param ipAdress
     */
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

    /**
     * Aborts all connections of this type
     */
    public static void abortConnection()
    {
	self: abortConnection = true;
    }

    /**
     * Runs the connection, send an receive TCP commands
     */
    @Override
    public void run()
    {
	abortConnection = false;
	System.out.println("Thread begin");

	do
	{
	    commandSend = getNextCommandFromDataBox();
	    // if (commandSend == null)
	    // {
	    // continue;
	    // }
	    // System.out.println("---------Thread --command to player:" + commandSend.toString());
	    connectionLogic.sendCommandToPlayer(commandSend);
	    wait(1000);

	    this.commandReceive = connectionLogic.getCommandFromPlayer();
	    // System.out.println("---------Thread --command to DataBox:" + commandReceive.toString());
	    if (this.commandReceive != null && this.commandReceive.isValid())
	    {
		System.out.println("send recieved command to DataBox: " + this.commandReceive.toString());
		sendCommandToDataBox(commandReceive);
	    }
	    wait(1000);
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