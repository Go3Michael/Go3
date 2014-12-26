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
		sendCommandToBox(field, "INIT_FIELD");
	}

	private void sendCommandToBox(Object commandData, String commandType) 
	{
		Command command = new Command(getNewCommandNumber(), commandData, commandType);		
		DataBox.pushSendCommand(command);
	}
	
	
	
	private int getNewCommandNumber()
	{
		return ++this.commandNo;
	}
}
