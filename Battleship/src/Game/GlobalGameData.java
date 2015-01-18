package Game;

public class GlobalGameData 
{
	private static boolean isMyTurn;
	
	public static void setIsMyTurn(boolean turn)
	{
		isMyTurn = turn;
	}

	public static boolean isMyTurn()
	{
		return isMyTurn;
	}
}
