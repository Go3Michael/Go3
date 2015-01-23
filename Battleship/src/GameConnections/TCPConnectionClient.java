package GameConnections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

// import org.apache.log4j.Logger;

import GameConnections.CommandBuilder.CommandConverter;
import GameUtilities.Command;

/**
 * TCP Connection Client
 * 
 * @author Schoenegger / Purkart / Koch
 *
 */

/**
 * Constructor
 */
public class TCPConnectionClient extends Connection
{
    // private static Logger logger = Logger.getLogger(Connection.class);

    BufferedReader inputReader;
    BufferedWriter outputStream;

    Socket clientSocket;
    CommandConverter convert;

    /**
     * Constructor
     * 
     * @param port
     * @param ipAdress
     * @throws UnknownHostException
     * @throws IOException
     */
    public TCPConnectionClient(int port, String ipAdress) throws UnknownHostException, IOException
    {
	this.clientSocket = new Socket(ipAdress, port);
	System.out.println("Just connected to " + clientSocket.getRemoteSocketAddress());
	// logger.debug("DEBUG: Hallo");
	this.outputStream = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

	this.inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

	convert = new CommandConverter();
    }

    /**
     * receive command
     * 
     * @return command
     */
    @Override
    public Command receiveCommand()
    {
	// TODO Auto-generated method stub
	String inputString = recieveStream();
	// System.out.println(inputString);
	return convert.convertToGameCommand(inputString);
    }

    private String recieveStream()
    {
	String inputString = "";
	try
	{
	    // System.out.println("Wait for Server...");
	    inputString = inputReader.readLine();
	    // System.out.println("after readLine()");
	}
	catch (Exception exception)
	{
	    // Todo
	}

	return inputString;
    }

    /**
     * Send Command
     */
    @Override
    public void sendCommand(Command command)
    {
	String tcpString = "";
	if (command == null)
	{
	    tcpString = "99;";
	}
	else
	{
	    tcpString = convert.convertToTCPString(command);
	}

	sendStream(tcpString);
    }

    private void sendStream(String tcpString)
    {
	try
	{
	    outputStream.write(tcpString);
	    outputStream.newLine();
	    outputStream.flush();
	}
	catch (Exception exception)
	{
	    // Todo
	}
    }

    /**
     * close connection
     */
    @Override
    public void close()
    {
	// TODO Auto-generated method stub
	try
	{
	    System.out.println("Close Connection!");
	    this.clientSocket.close();
	}
	catch (IOException exception)
	{
	    // TODO Auto-generated catch block
	    exception.printStackTrace();
	}
    }

    /**
     * is connection available
     * 
     * @return boolean
     */
    @Override
    public boolean isConnectionAvailable()
    {
	// TODO Auto-generated method stub, only false ??
	return false;
    }
}