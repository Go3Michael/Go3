package Game;

/**
 * @author Schoenegger / Purkart / Koch
 */
public class GlobalGameData
{
    private static boolean isMyTurn;

    /**
     * Set my turn
     * 
     * @param turn
     */
    public static void setIsMyTurn(boolean turn)
    {
	isMyTurn = turn;
    }

    /**
     * Its my Turn
     * 
     * @return boolean
     */
    public static boolean isMyTurn()
    {
	return isMyTurn;
    }
}
