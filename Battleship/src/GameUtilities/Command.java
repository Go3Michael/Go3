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
	
	public boolean isValid()
	{
		if (this.commandNr == 99) {
			return false;
		}
		
		return true;
	}
	
	public String toString()
	{
		return Integer.toString(this.commandNr) + ";" + this.commandType + ";" + this.commandData.toString();
	}

}
