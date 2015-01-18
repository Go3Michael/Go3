package GameConnections;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import GameConnections.CommandBuilder.CommandConverter;
import GameUtilities.Command;

public class TCPConnectionClient extends Connection
{
	BufferedReader inputReader;
	DataOutputStream outputStream;
	Socket clientSocket;
	CommandConverter convert;
	
	public TCPConnectionClient(int port, String ipAdress) throws UnknownHostException, IOException
	{
		this.clientSocket = new Socket(ipAdress, port);
		outputStream = new DataOutputStream(clientSocket.getOutputStream());
		inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		convert = new CommandConverter();
		//xxxxxxx
	}
	
	@Override
	public Command receiveCommand() 
	{
		// TODO Auto-generated method stub
		String inputString = recieveStream();
		
		return convert.convertToGameCommand(inputString);
	}
	
	private String recieveStream()
	{
		String inputString = "";
		try 
		{
			inputString = inputReader.readLine(); 
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
		String tcpString = convert.convertToTCPString(command);
		tcpString = "initialString:FromClient";
	
		sendStream(tcpString);
	}

	private void sendStream(String tcpString)
	{
		try 
		{
			outputStream.writeBytes(tcpString);
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
			this.clientSocket.close();
		}
		catch (IOException exception)
		{
			// TODO Auto-generated catch block
			exception.printStackTrace();
		}
		
	}

}

//public void tcpClient() throws Exception  
//{
//	String sentence;
//	String modifiedSentence;
//	
//	BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
//	Socket clientSocket = new Socket("localhost", 8010);
//	DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
//	BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

//	sentence = inFromUser.readLine();
//	outToServer.writeBytes(sentence + '\n');
//	modifiedSentence = inFromServer.readLine();
//	System.out.println("FROM SERVER: " + modifiedSentence);
//	clientSocket.close();
//} 