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

    /**
     * Constructor
     */
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
	// sendCommandToBox((Object)field, "INIT_FIELD");
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
	// sendCommandToBox(attacCommand, "ATTAC_COMMAND");
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

    /**
     * send the command to the Databox
     * 
     * @param commandData
     *            object
     * @param commandType
     *            string
     */
    private void sendCommandToBox(Object commandData, String commandType)
    {
	// Command command = new Command(getNewCommandNumber(), commandData, commandType);
	// DataBox.pushSendCommand(command);
    }

    // **************receive Command********************
    // TODO
    // create AList of sendet commandos
    // If commando was not done locate Command per number and send it again!!

    /**
     * received the intitalised field from enemy
     * 
     */
    public void receiveInitFieldFromEnemy()
    {
	while (DataBox.isReceiveListEmpty())
	{
	    // Send Frontend Wait State
	    // System.out.println("Wait for receive Data Box --- Command Handler---");
	    wait(300);
	}
	receiveCommandFromDataBox();
    }

    /**
     * received the command form the databox
     */
    public void receiveCommandFromDataBox()
    {
	Command command = DataBox.popReceiveCommand();
	while (command == null)
	{

	    // WAIT
	    wait(300);
	}
	System.out.println("received from databox for Logic" + command.toString());
	// check type of Commands

	switch (command.getType())
	{
	    case "INIT_FIELD":
		setEnemyFieldInLogicByCommand(command);
		System.out.println("Income Init Field");
		break;
	    case "ATTAC_COMMAND":
		setAttacCommandInLogic(command);
		System.out.println("Income attacCommand");
		break;
	}
    }

    /**
     * Set the attac commmand in the Logic
     * 
     * @param command
     *            command
     */
    private void setAttacCommandInLogic(Command command)
    {
	if (command.getCommandData() instanceof AttackPosition)
	{
	    // referenceLogic.setEnemyField((Field)command.getCommandData());
	    referenceLogic.setEnemyAttacCommand((AttackPosition) command.getCommandData());
	}
	else
	{
	    // TODO check if it's needed
	}
    }

    /**
     * Set the enemy field in the Logic by command.
     * 
     * @param command
     *            command
     */
    private void setEnemyFieldInLogicByCommand(Command command)
    {
	if (command.getCommandData() instanceof Field)
	{
	    referenceLogic.setEnemyField((Field) command.getCommandData());
	}
	else
	{
	    // Send Error Message to Frontend!!!
	    System.out.println("Error in EnemyField ---Command Handler--");
	}
    }

    /*
     * helpers functions for
     */
    /**
     * generate the next command number
     * 
     * @return integer
     */
    private int getNewCommandNumber()
    {
	return ++this.commandNo;
    }

    /**
     * waits for the delivered time
     * 
     * @param ms
     */
    private void wait(int ms)
    {
	try
	{
	    Thread.sleep(ms);
	}
	catch (Exception e)
	{
	    // TODO exception handling
	}
    }
}
