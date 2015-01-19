package Game;

import GameConnections.DataBox;
import GameUtilities.Command;
import GameUtilities.Field.Field;

public class CommandHandler 
{
	private Logic referenceLogic;
	private int commandNo = 0;
	
	public CommandHandler(Logic refLogic)
	{
		this.referenceLogic = refLogic;
	}

	public void sendInitField(Field field)
	{
		sendCommandToBox((Object)field, "INIT_FIELD");
		receiveInitFieldFromEnemy();
	}
	
	public void sendAttacCommand(String attacCommand)
	{
		sendCommandToBox(attacCommand, "ATTAC_COMMAND");
	}

	private void sendCommandToBox(Object commandData, String commandType) 
	{
		Command command = new Command(getNewCommandNumber(), commandData, commandType);		
		DataBox.pushSendCommand(command);
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
			wait(300);
		}
		
		receiveCommandFromDataBox();
	}
	
	public void receiveCommandFromDataBox()
	{
		Command command = DataBox.popReceiveCommand();
		//check type of Commands
		switch(command.getType())
		{
			case "INIT_FIELD":
				setEnemyFieldInLogicByCommand(command);
			break;
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
