package Game;

import GameConnections.DataBox;
import GameUtilities.Command;
import GameUtilities.AttackPosition.AttackPosition;
import GameUtilities.Field.Field;

/**
 * @author Team Schoenegger / Purkart / Koch
 *
 *
 *         Handler for the commands between the component
 */

public class CommandHandler
{
	private Logic referenceLogic;
	private int commandNo = 0;

	public CommandHandler(Logic refLogic)
	{
		this.referenceLogic = refLogic;
	}

	/**
	 * Send the inititalised field
	 * 
	 * @param initCommand
	 *            command
	 */
	public void sendInitField(Command initCommand)
	{

		DataBox.pushSendCommand(initCommand);
		System.out.println("pushInitToSendCommand");

		receiveInitFieldFromEnemy();
	}

	/**
	 * Send the Attac Command
	 * 
	 * @param attacCommand
	 *            command
	 */
	public void sendAttacCommand(Command attacCommand)
	{
		System.out.println("send Attack Command");
		DataBox.pushSendCommand(attacCommand);
		receiveAttacCommandFromEnemy();
	}

	/**
	 * Received the attact command from the emeny player
	 */
	private void receiveAttacCommandFromEnemy()
	{
		System.out.println("receive command from Enemy");
		receiveCommandFromDataBox();

	}

	// **************receive Command********************
	// create a List of sendet commandos
	// If commando was not done locate Command per number and send it again!!

	/**
	 * received the initialised field from enemy
	 * 
	 */
	public void receiveInitFieldFromEnemy()
	{
		while (DataBox.isReceiveListEmpty())
		{
			// Wait for receive Data Box --- Command Handler---
			wait(300);
		}
		receiveCommandFromDataBox();
	}

	/**
	 * received the command form the databox
	 */
	public void receiveCommandFromDataBox()
	{
		Command command = null;
		while (command == null)
		{
			command = DataBox.popReceiveCommand();
			// WAIT
			wait(300);
		}
		System.out.println("\n received from ...databox for Logic" + command.toString() + command.getType());

		// check type of Commands
		switch (command.getType())
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
		if (command.getCommandData() instanceof AttackPosition)
		{
			referenceLogic.setEnemyAttacCommand((AttackPosition) command.getCommandData());
		}
		else
		{
			System.out.println("No valid Attac command received");
		}
	}

	private void setEnemyFieldInLogicByCommand(Command command)
	{
		if (command.getCommandData() instanceof Field)
		{
			referenceLogic.setEnemyField((Field) command.getCommandData());
		}
		else
		{
			System.out.println("Error in EnemyField ---Command Handler--");
		}
	}

	// helpers
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
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
