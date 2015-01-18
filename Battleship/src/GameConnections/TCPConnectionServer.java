package GameConnections;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;

import GameUtilities.Command;

public class TCPConnectionServer extends Connection
{
	ServerSocket serverSocket;
	
	public TCPConnectionServer(int port) throws UnknownHostException,IOException 
	{	
		 serverSocket = new ServerSocket(port);
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