package GameConnections;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import GameUtilities.Command;

public class TCPConnectionClient extends Connection
{
	BufferedReader inputReader = new BufferedReader( new InputStreamReader(System.in));
	DataOutputStream outputStream;
	Socket clientSocket;
	
	public TCPConnectionClient(int port, String ipAdress) throws UnknownHostException, IOException
	{
		this.clientSocket = new Socket(ipAdress, port);
		outputStream = new DataOutputStream(clientSocket.getOutputStream());
		//xxxxxxx
	}
	
	@Override
	public Command receiveCommand() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendCommand() 
	{
		// TODO Auto-generated method stub
		
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