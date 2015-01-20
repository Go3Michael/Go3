package Game;

import javax.naming.InitialContext;

import GameConnections.TestEnemy;
import GameUtilities.Command;
import GameUtilities.Field.Field;

public class Logic 
{
	private Frontend referenceFrontend;
	private CommandHandler commandHandler;
	private Field ownField;
	private Field enemyField;
	private boolean isMyTurn;
	private boolean isHost;
	
	//********only for tests---------------------
	private TestEnemy testEnemy = new TestEnemy();
	//*****************************************
	
 	public Logic(boolean isFirstPlayer)
	{
		this.isMyTurn = isFirstPlayer;
	}
	
	private void setIsMyTurn(boolean isMyTurn)
	{
		this.isMyTurn = isMyTurn;
	}
	
	public void setEnemyField(Field enemyField)
	{
		this.enemyField =  enemyField;
	}

	public void setInitField(Field ownInitField)
	{
		this.ownField = ownInitField;
		sendFieldToOtherPlayer();
	}
	
	private void sendFieldToOtherPlayer() 
	{
		commandHandler = new CommandHandler(this); //Create commandHandler and send reference
		
		//************only for Tests----------
				//HtestEnemy.sendFieldInitCommand();
		//**********************************-------------------
				
		commandHandler.sendInitField(buildInitCommand(this.ownField));
		
		waitForSettingsOtherPlayer();
	}
	
	private Command buildInitCommand(Field field)
	{
		Command initCommand = new Command(1, field, "INIT_FIELD");
		
		return initCommand;
	}
	
	private void waitForSettingsOtherPlayer()
	{
		while(enemyField == null)
		{
			wait(300);
			System.out.println("wait for settings other player");
		}
		referenceFrontend.sendFeedbackThatEnemyHasInitHisField();
		//startNextMove();
	}
	
	private void startNextMove()
	{
		if(isMyTurn)
		{
			fireToFieldPosition(referenceFrontend.getNextCommand());
			isMyTurn = false;
		}
		else
		{
			//wait for enemy move
		}
	}
	
	private boolean fireToFieldPosition(String fireMove)
	{
		int[] attacCoordinates = buildCoordinatesByString(fireMove);
		
		commandHandler.sendAttacCommand(buildAttacCommand(fireMove));
		return enemyField.fireToPosition(attacCoordinates[0], attacCoordinates[1]);
	}
	
	private Command buildAttacCommand(String fireMove)
	{
		return null;
	}
	
	private void waitForEnemyMove()
	{
		
	}
	
	public boolean isAttacMoveValid(String nextMove)
	{
		int[] attacCoordinates = buildCoordinatesByString(nextMove);
 		
 		return enemyField.IsValidAttacPosition(attacCoordinates[0], attacCoordinates[1]);
 	}

	
	
	public void setFrontendReference(Frontend refFrontend)
	{
		this.referenceFrontend = refFrontend;
	}

	private int[] buildCoordinatesByString(String nextMove) 
	{
		int[] attacCoordinates = new int[2];
		String[] coordinatesAsString = nextMove.split(",");
 		attacCoordinates[0] = Integer.parseInt(coordinatesAsString[0]);
 		attacCoordinates[1] = Integer.parseInt(coordinatesAsString[1]);
		return attacCoordinates;
	}
	
	
	
	private void wait(int ms)
	{
		try
		{
			Thread.sleep(ms);
		}
		catch(Exception e)
		{
			//Fuck off
		}
	}
}
