package GameConnections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

//import org.apache.log4j.Logger;

import GameConnections.CommandBuilder.CommandConverter;
import GameUtilities.Command;

public class TCPConnectionClient extends Connection
{
	//private static Logger logger = Logger.getLogger(Connection.class);
	
	BufferedReader inputReader;
	//DataOutputStream outputStream;
	DataOutputStream outputStream;
	Socket clientSocket;
	CommandConverter convert;
	
	public TCPConnectionClient(int port, String ipAdress) throws UnknownHostException, IOException
	{
		this.clientSocket = new Socket(ipAdress, port);
		System.out.println("Just connected to " + clientSocket.getRemoteSocketAddress());
		//logger.debug("DEBUG: Hallo");
		//this.outputStream = new DataOutputStream(clientSocket.getOutputStream());
		this.outputStream = new DataOutputStream(clientSocket.getOutputStream());;
		this.inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		//BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
       // BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		convert = new CommandConverter();
		//xxxxxxx
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
//		try {
//			inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try 
		{
			//System.out.println("try to receive... press enter");
//			BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
//			System.out.println("Read message from server press enter");
//			inFromUser.readLine();
			System.out.println("Wait for Server...");
			//inputString = inputReader.readLine(); 
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
		String tcpString;
		if (command == null) {
			//Send keep alive
			tcpString = "1/Keep alive!/KEEP_ALIVE";
		} else {
			tcpString = convert.convertToTCPString(command);
			tcpString = "1/Fuck you too!/TEST_FROM_CLIENT";
		}
		sendStream(tcpString);
	}

	private void sendStream(String tcpString)
	{
//		try {
//			outputStream = new DataOutputStream(clientSocket.getOutputStream());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try 
		{
			//outputStream.writeBytes(tcpString);
			outputStream.writeBytes(tcpString);
			//outputStream.newLine();
			//outputStream.flush();
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