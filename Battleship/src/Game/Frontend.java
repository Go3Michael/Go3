package Game;

import java.util.Scanner;

import GameConnections.ConnectionCommandHandler;
import GameConnections.DataBox;
import GameUtilities.Field.Field;

public class Frontend 
{
	private Field fieldWithSettings;
	private Thread connectionCommandHandler;
	private FrontendGame frontendGame = new FrontendGame(this); //Send referenz to frontendGame
	private Player player = new Player(true);
	private Logic logic = new Logic(true);
		
	public void callMenue()
	{
		String connectionType;
		
		System.out.println("************Welcome at Battleship Commander****************\n");
		System.out.println("Select Game \n");
		
		System.out.println("vs Host Game = 'H'");
		System.out.println("vs Client Game = 'CL'");
		System.out.println("vs CPU = 'CPU'");
		
		System.out.println("Please select: ");
		
		player.sendFrontendReferenceToLogic(this);
		logic.setFrontendReference(this);
		connectionType = readMenueInput();
		
		//System.out.println("----" + connectionType);
	}

	private String readMenueInput()
	{
		String connectionType;
		
		connectionType = readStringFromConsole();
		
		while(!createConnectionByInput(connectionType))
		{
			System.out.println("Please Insert the Correct Text ");
			connectionType = readStringFromConsole();
		}
		
		return connectionType;
	}
		
	private boolean createConnectionByInput(String connectionType) 
	{
		switch(connectionType)
		{
			case "H":
				readHostSettings();
				return true;
				
			case "CL":
				readClientSettings();
				return true;
				
			case "CPU":
				
				return true;
				
			default:				
				return false;
		}
	}
	
	private void hostSettingMenue()
	{
		
	}
	
	private void readHostSettings()
	{
		String hostPort;
		
		System.out.println("Select Host Port 8000 - 8500 ");
		hostPort = readStringFromConsole();
		
		while(!createHostConnectionByInput(hostPort))
		{
			System.out.println("Please Insert the Correct Text ");
			System.out.println("Select Host Port 8000 - 8500 ");
			hostPort = readStringFromConsole();
		}				
	}
	
	private boolean createHostConnectionByInput(String hostPort)
	{
		int portNo;
		
		try
		{
			portNo = Integer.parseInt(hostPort);
			
		}
		catch(Exception e)
		{
			return false;
		}
		
		if(checkPortNumber(portNo))
		{
			connectionCommandHandler = new Thread(new ConnectionCommandHandler(portNo));
			connectionCommandHandler.start();
			executeGameSetupMenue();
			return true;
		}
		
		return false;
	}
	
	private void readClientSettings()
	{
		String clientPort;
		String ipAddress;
		
		System.out.println("Select Host Port 8000 - 8500 ");
		clientPort = readStringFromConsole();
		
		System.out.println("Insert Valid ip address ");
		ipAddress = readStringFromConsole();
		
		while(!createClientConnectionByInput(clientPort, ipAddress))
		{
			System.out.println("Please Insert the Correct Text ");
			System.out.println("Select Host Port 8000 - 8500 ");
			clientPort = readStringFromConsole();
			
			System.out.println("Insert Valid ip address ");
			ipAddress = readStringFromConsole();
		}	
		
	}
	
	private boolean createClientConnectionByInput(String clientPort, String ipAddress)
	{
		int portNo;
		
		try
		{
			portNo = Integer.parseInt(clientPort);
			
		}
		catch(Exception e)
		{
			return false;
		}
		
		if(checkPortNumber(portNo) && checkIpAddress(ipAddress))
		{
			//this.player = new Player(false);
			this.logic = new Logic(false);
			connectionCommandHandler = new Thread(new ConnectionCommandHandler(portNo, ipAddress));
			connectionCommandHandler.start();
			executeGameSetupMenue();
			return true;
		}
		
		return false;
	}
		
	private boolean checkIpAddress(String ipAddress) 
	{
		
		return true;
	}

	private void startConnection(String connectionType)
	{
		
	}
	
	private String readStringFromConsole()
	{
		return new Scanner( java.lang.System.in ).nextLine();
	}
	
	private boolean checkPortNumber(int portNo)
	{
		if(portNo >= 8000 && portNo <= 8500)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private void executeGameSetupMenue()
	{
		FrontendGameSetup gameSetup = new FrontendGameSetup();
		if(gameSetup.callMenue())
		{
			this.fieldWithSettings = gameSetup.getFinischedField();
		}
		
		sendInitFieldToPlayer();
	}
	
	private void sendInitFieldToPlayer()
	{
		player.createOwnField(fieldWithSettings);
	}

//*****************************************************************************************************
//		CALL BY REF
	
	public String getNextCommand()
	{
		return frontendGame.getNextMove();
	}
	
	public boolean askLogikIsMoveValid()
	{
		return logic.isMoveValid();
				//player.askLogikIsMoveValid();		
	}
}
