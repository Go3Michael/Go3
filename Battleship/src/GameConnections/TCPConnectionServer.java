package GameConnections;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import GameConnections.CommandBuilder.CommandConverter;
import GameUtilities.Command;

public class TCPConnectionServer extends Connection
{
	BufferedReader inputReader;
	DataOutputStream outputStream;
	
	ServerSocket serverSocket;
	Socket connectionSocket;
	
	CommandConverter convert;
	
	public TCPConnectionServer(int port) throws UnknownHostException,IOException 
	{	
		this.serverSocket = new ServerSocket(port);
		connectionSocket = serverSocket.accept();
		System.out.println("connected");
		inputReader =  new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
		outputStream = new DataOutputStream(connectionSocket.getOutputStream());
		
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
		try {
			inputReader =  new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try 
		{
			System.out.println("try to receive");
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
		tcpString = "fuck you";
	
		sendStream(tcpString);
	}

	private void sendStream(String tcpString)
	{
		try {
			outputStream = new DataOutputStream(connectionSocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try 
		{
			outputStream.writeBytes(tcpString);
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
			this.serverSocket.close();
		}
		catch (IOException exception)
		{
			// TODO Auto-generated catch block
			exception.printStackTrace();
		}
		
	}
	
	
		
}


//public void tcpServer() throws Exception      
//{ 
//	String clientSentence;          
//	String capitalizedSentence; 
//	ServerSocket welcomeSocket = new ServerSocket(8010); 
//	
//	while(true)          
//	{  
//		Socket connectionSocket = welcomeSocket.accept();
//		BufferedReader inFromClient =  new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
//		DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
//		
//		clientSentence = inFromClient.readLine(); 
//		System.out.println("Received: " + clientSentence); 
//		capitalizedSentence = clientSentence.toUpperCase() + '\n'; 
//		outToClient.writeBytes(capitalizedSentence);   
//		Thread.sleep(100);
//		}       
//	} 
//}