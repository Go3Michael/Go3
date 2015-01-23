package Game;

import GameConnections.DataBox;
import GameUtilities.Command;
import GameUtilities.AttackPosition.AttackPosition;
import GameUtilities.Field.Field;

public class CommandHandler 
{
	private Logic referenceLogic;
	private int commandNo = 0;
	
	public CommandHandler(Logic refLogic)
	{
		this.referenceLogic = refLogic;
	}

	public void sendInitField(Command initCommand)
	{
		
		DataBox.pushSendCommand(initCommand);
		System.out.println("pushInitToSendCommand");

		receiveInitFieldFromEnemy();
	}
	
	public void sendAttacCommand(Command attacCommand)
	{
		System.out.println("send Attack Command");
		DataBox.pushSendCommand(attacCommand);
//		sendCommandToBox(attacCommand, "ATTAC_COMMAND");
		receiveAttacCommandFromEnemy();
	}

	private void receiveAttacCommandFromEnemy() 
	{
		System.out.println("receive command from Enemy");
		receiveCommandFromDataBox();
		
	}

	private void sendCommandToBox(Object commandData, String commandType) 
	{
//		Command command = new Command(getNewCommandNumber(), commandData, commandType);		
//		DataBox.pushSendCommand(command);
	}
	
	
	//**************receive Command********************
	//TODO
	//create AList of sendet commandos
	//If commando was not done locate Command per number and send it again!!
	
	public void receiveInitFieldFromEnemy()
	{
		while(DataBox.isReceiveListEmpty())
		{
			//Send Frontend Wait State
//			System.out.println("Wait for receive Data Box --- Command Handler---");
			wait(300);
		}		
		receiveCommandFromDataBox();
	}
	
	public void receiveCommandFromDataBox()
	{
		Command command = null;
		while (command == null) 
		{
			command = DataBox.popReceiveCommand();
			//WAIT
			wait(300);
		}
		System.out.println("\n received from ...databox for Logic" + command.toString() + command.getType());
		//check type of Commands
		
		
		switch(command.getType())
		{
			case "INIT_FIELD":
				setEnemyFieldInLogicByCommand(command);
				System.out.println("Income Init Field");
				break;
			case "ATTAC_COMMAND":
				sendValidAttacCommandToLogic(command);
				System.out.println("Income attacCommand");
			break;
		}
	}
	
	private void sendValidAttacCommandToLogic(Command command) 
	{
		if(command.getCommandData() instanceof AttackPosition)
		{
//			referenceLogic.setEnemyField((Field)command.getCommandData());
			referenceLogic.setEnemyAttacCommand((AttackPosition)command.getCommandData());
		}
		else
		{
			System.out.println("No valid Attac command received");
		}
	}

	private void setEnemyFieldInLogicByCommand(Command command)
	{
		if(command.getCommandData() instanceof Field)
		{
			referenceLogic.setEnemyField((Field)command.getCommandData());
		}
		else
		{
			//Send Error Message to Frontend!!!
			System.out.println("Error in EnemyField ---Command Handler--");
		}
	}
	
	//helpers
	private int getNewCommandNumber()
	{
		return ++this.commandNo;
	}
	
	private void wait(int ms)
	{  
		try
		{
			Thread.sleep(ms);
		}
		catch(Exception e)
		{
			//mir doch wurst
		}
	}
}
