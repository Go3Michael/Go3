package GameUtilities;

public class Command 
{
	private Object commandData;
	private String commandType; //INIT_FIELD, ATTAC_COMMAND
	private int commandNr;
	
	public Command(int commandNr,Object data, String type)
	{
		this.commandData = (Object)data;
		this.commandType = type;
		this.commandNr = commandNr; 
	}
	
	public Object getCommandData()
	{
		return this.commandData;
	}
	
	public String getType()
	{
		return this.commandType;
	}
	
	public int getNummber()
	{
		return this.commandNr;
	}
	
	public String toString()
	{
		return "";
	}

}
