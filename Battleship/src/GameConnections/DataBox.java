package GameConnections;

import java.util.LinkedList;
import java.util.Queue;

import GameUtilities.Command;

public class DataBox 
{
	private static Queue<Command> receiveCommands = new LinkedList<Command>();
	private static Queue<Command> sendCommands = new LinkedList<Command>();
	//Semaphor
	private static boolean accessForReceiveCommands = true;
	private static boolean accessForSendCommands = true;
	
	private static boolean getAccessSendCommands()
	{
		while(!accessForSendCommands)
		{
			try
			{
				System.out.println("wait send access");
				Thread.sleep((int)(Math.random()*1000));
			}
			catch(Exception e)
			{
				System.out.println(e.toString() + "Error getAccessSendCommands");
			}
		}
		
		accessForSendCommands = false; //lock access for other calls
		
		return true;
	}
	
	private static void freeAccessSendCommands()
	{
		accessForSendCommands = true;
	}
	
	private static boolean getAccessReceiveCommands()
	{
		while(!accessForReceiveCommands)
		{
			try
			{
				System.out.println("wait receive access");
				Thread.sleep((int)(Math.random()*1000));
			}
			catch(Exception e)
			{
				System.out.println(e.toString() + "Error getAccessreceiveCommands");
			}
		}
		
		accessForReceiveCommands = false; //lock acces for other calls
		
		return true;
	}
	
	private static void freeAccessReceiveCommands()
	{
		accessForReceiveCommands = true;
	}
	
	public static boolean isReceiveListEmpty()
	{
		if(receiveCommands.isEmpty())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean isSendListEmpty()
	{
		if(sendCommands.isEmpty())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static Command popSendCommand()
	{
		Command returnCommand;
		
		getAccessSendCommands();
		
		if(sendCommands.isEmpty())
		{
			returnCommand = null;
		}
		else
		{
			returnCommand = sendCommands.poll();
		}
		
		freeAccessSendCommands();
		return returnCommand;
	}
	
	public static Command popReceiveCommand()
	{
		Command returnCommand;
		
		getAccessReceiveCommands(); 
		
		if(receiveCommands.isEmpty())
		{
			returnCommand =  null;
		}
		else
		{
			returnCommand = receiveCommands.poll();
		}
		
		freeAccessReceiveCommands();
		return returnCommand;
	}
	
	public static void pushSendCommand(Command command)
	{
		getAccessSendCommands();
		sendCommands.add(command);
		freeAccessSendCommands();
	}
	
	public static void pushReceiveCommand(Command command)
	{
		getAccessReceiveCommands();
		
		receiveCommands.add(command);
		freeAccessReceiveCommands();
	}
	
}
