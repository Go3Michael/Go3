package Game;

import GameUtilities.Field.Field;

public class Logic 
{
	private Frontend referenceFrontend;
	private CommandHandler commandHandler;
	private Field ownField;
	private Field enemyField;
	private boolean isMyTurn;
	
	public Logic(boolean isFirstPlayer)
	{
		this.isMyTurn = isFirstPlayer;
	}
	
	private void setIsMyTurn(boolean isMyTurn)
	{
		this.isMyTurn = isMyTurn;
	}
	
	public void setInitField(Field ownInitField)
	{
		this.ownField = ownField;
		sendFieldToOtherPlayer();
	}
	
	private void sendFieldToOtherPlayer() 
	{
		commandHandler = new CommandHandler(this); //Create commandHandler and send reference
		commandHandler.sendInitField(this.ownField);
		waitForSettingsOtherPlayer();
	}
	
	private void waitForSettingsOtherPlayer()
	{
		while(enemyField == null)
		{
			wait(300);
		}
		
		startGame();
	}
	
	private void startGame()
	{
		if(isMyTurn)
		{
			referenceFrontend.getNextCommand();
		}
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
