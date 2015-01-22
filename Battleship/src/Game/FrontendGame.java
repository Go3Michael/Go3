package Game;

import java.util.Scanner;

/**
 * Frontend Command responsible attac command
 * 
 * @author Schoenegger / Purkart / Koch
 *
 */
public class FrontendGame
{
    private Frontend referenceFrontend;

    /**
     * set frontend variable
     * 
     * @param referenceFrontend
     */
    public FrontendGame(Frontend referenceFrontend)
    {
	this.referenceFrontend = referenceFrontend;
    }

    /**
     * get next move
     * 
     * @return String
     */
    public String getNextMove()
    {
	String nextMove = readCommandInputFromConsole();

	while (!referenceFrontend.askLogikIsAttacMoveValid(nextMove))
	{
	    readCommandInputFromConsole();
	}

	return nextMove;
    }

    /**
     * Read Command input from console
     * 
     * @return Scanner object
     */
    private String readCommandInputFromConsole()
    {
	System.out.println("Insert Command(x,y):");
	return readStringFromConsole();
    }

    /**
     * Read the string from console.
     * @return Scanner object
     */
    private String readStringFromConsole()
    {
	return new Scanner(java.lang.System.in).nextLine();
    }
}
