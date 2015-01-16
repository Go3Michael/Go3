package Game;

import GameConnections.TestEnemy;
import GameUtilities.Field.Field;

public class Logic 
{
	private Frontend referenceFrontend;
	private CommandHandler commandHandler;
	private Field ownField;
	private Field enemyField;
	private boolean isMyTurn;
	
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
				testEnemy.sendFieldInitCommand();
		//**********************************-------------------
				
		commandHandler.sendInitField(this.ownField);
		
		waitForSettingsOtherPlayer();
	}
	
	private void waitForSettingsOtherPlayer()
	{
		while(enemyField == null)
		{
			wait(300);
		}
		referenceFrontend.sendFeedbackThatEnemyHasInitHisField();
		startNextMove();
	}
	
	private void startNextMove()
	{
		if(isMyTurn)
		{
			commandHandler.sendAttacCommand(referenceFrontend.getNextCommand());
			isMyTurn = false;
		}
		else
		{
			//wait for enemy move
		}
	}
	
	private void waitForEnemyMove()
	{
		
	}
	
	public boolean isMoveValid()
	{
		//todo implement Valid Check
		return true;
	}
	
	public void setFrontendReference(Frontend refFrontend)
	{
		this.referenceFrontend = refFrontend;
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
