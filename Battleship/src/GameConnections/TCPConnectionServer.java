package GameConnections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import GameConnections.CommandBuilder.CommandConverter;
import GameUtilities.Command;

public class TCPConnectionServer extends Connection
{
	BufferedReader inputReader;
	BufferedWriter outputStream;
	ServerSocket serverSocket;
	Socket connectionSocket;
	
	CommandConverter convert;
	
	public TCPConnectionServer(int port) throws UnknownHostException,IOException 
	{	
		this.serverSocket = new ServerSocket(port);
		System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
		
		connectionSocket = serverSocket.accept();
		System.out.println("Just connected to " + connectionSocket.getRemoteSocketAddress());
		
		inputReader =  new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

		this.outputStream = new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));
		
		convert = new CommandConverter();
	}

	@Override
	public Command receiveCommand() 
	{
		// TODO Auto-generated method stub
		String inputString = recieveStream();
		System.out.println(inputString);
		return convert.convertToGameCommand(inputString);
	}
	
	private String recieveStream()
	{
		String inputString = "";

		try 
		{
			System.out.println("Wait for Client...");
			inputString = inputReader.readLine(); 
			System.out.println("after readLine()");
		}
		catch(Exception exception)
		{
			//Todo
		}
		
		return inputString;
	}

	@Override
	public void sendCommand(Command command)
	{
		String tcpString = "";
		if (command == null) {
			//TODO
		}

		tcpString = convert.convertToTCPString(command);
		sendStream(tcpString);
	}

	private void sendStream(String tcpString)
	{
		try 
		{
			outputStream.write(tcpString);
			outputStream.newLine();
			outputStream.flush();
			
			System.out.println("write in Buffer:" + tcpString);
		}
		catch(Exception exception)
		{
			//Todo
		}
	}

	@Override
	public void close()
	{
		// TODO Auto-generated method stub
		try
		{
			System.out.println("Close Connection!");
			this.serverSocket.close();
		}
		catch (IOException exception)
		{
			// TODO Auto-generated catch block
			exception.printStackTrace();
		}	
	}	
}