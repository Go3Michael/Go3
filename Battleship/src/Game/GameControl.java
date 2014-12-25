package Game;
import Connection.*;

public class GameControl 
{
	private boolean shipsInPosition = false;
	private Connection connection;
	
	public boolean checkConnectionState()
	{
		return false;
	}
	
	public boolean startConnection(ConnectionType connType)
	{
		switch(connType)
		{
			case HOST:
				this.connection = new Connection(); //port, type
				break;
			case CLIENT:
				this.connection = new Connection(); //ipAdress, type
				break;
			case CPU_LOGIC:
				this.connection = new Connection(); //??
				break;				
		}
		return true;
	}
	
	
	
}
