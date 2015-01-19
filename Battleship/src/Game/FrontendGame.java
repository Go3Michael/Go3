package Game;

import java.util.Scanner;

public class FrontendGame 
{
	private Frontend referenceFrontend;
	
	public FrontendGame(Frontend referenceFrontend)
	{
		this.referenceFrontend = referenceFrontend;
	}
	
	public String getNextMove()
	{
		String nextMove = readCommandInputFromConsole() ;
		
		while(!referenceFrontend.askLogikIsAttacMoveValid(nextMove))
		{
			readCommandInputFromConsole();
		}
		
		return nextMove;
	}
	
	private String readCommandInputFromConsole()
	{
		System.out.println("Insert Command:");
		return readStringFromConsole();
	}
	
	
	private String readStringFromConsole()
	{
		return new Scanner( java.lang.System.in ).nextLine();
	}
}
