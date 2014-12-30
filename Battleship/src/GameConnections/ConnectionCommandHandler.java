package GameConnections;

public class ConnectionCommandHandler implements Runnable 
{
	private Connection connection = null;
	private static boolean abortConnection = false; //static.. you can call it from everywhere
	
	public ConnectionCommandHandler() 
	{
		this.connection = new LocalConnection();
	}
	
	public ConnectionCommandHandler(int port)
	{
		this.connection = new TCPConnection(port);
	}
	
	// Client Connection
	public ConnectionCommandHandler(int port, String ipAdress)
	{
		this.connection = new TCPConnection(port, ipAdress);
	}
		
	//Aborts all connections of this type
	public static void abortConnection()
	{
		self:abortConnection = true;
	}
	

	@Override
	public void run() 
	{
		abortConnection = false;
		
		
		while(!abortConnection)
		{
			
		}
		
	}

}
