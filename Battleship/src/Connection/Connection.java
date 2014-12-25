package Connection;
import java.io.*;
import java.net.*; 

public class Connection 
{

	
//	//client
//	class TCPServer
//	{ 
//		public void tcpServer() throws Exception      
//		{ 
//			String clientSentence;          
//			String capitalizedSentence; 
//			ServerSocket welcomeSocket = new ServerSocket(8010); 
//			
//			while(true)          
//			{  
//				Socket connectionSocket = welcomeSocket.accept();
//				BufferedReader inFromClient =  new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
//				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
//				
//				clientSentence = inFromClient.readLine(); 
//				System.out.println("Received: " + clientSentence); 
//				capitalizedSentence = clientSentence.toUpperCase() + '\n'; 
//				outToClient.writeBytes(capitalizedSentence);   
//				Thread.sleep(100);
//				}       
//			} 
//		}
//	
//	public void tcpClient() throws Exception  
//	{
//		String sentence;
//		String modifiedSentence;
//		
//		BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
//		Socket clientSocket = new Socket("localhost", 8010);
//		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
//		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//		sentence = inFromUser.readLine();
//		outToServer.writeBytes(sentence + '\n');
//		modifiedSentence = inFromServer.readLine();
//		System.out.println("FROM SERVER: " + modifiedSentence);
//		clientSocket.close();
//	} 
	
	
}
