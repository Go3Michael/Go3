package GameUtilities;

public class Command 
{
	private Object commandData;
	private String commandType;
	private int commandNr;
	
	public Command(int commandNr,Object data, String type)
	{
		this.commandData = data;
		this.commandType = type;
		this.commandNr = commandNr; 
	}
	
	public String toString()
	{
		return "";
	}

}
