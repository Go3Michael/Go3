package GameConnections.CommandBuilder;

import GameUtilities.Command;

public class CommandConverter 
{
	
	public CommandConverter()
	{
		
	}
	
	public String convertToTCPString(Command command)
	{
//		StringBuffer convertString.append(String.valueOf(command.getNummber()));
//		convertString += String.valueOf(command.getCommandData());
//		convertString = command.getType();
//		
//		return convertString;
		
		//String debugString = String.format(String.valueOf(command.getNummber()) + ";" + command.getCommandData().toString() + ";" + command.getType());
		
		StringBuilder sb = new StringBuilder();
		sb.append("");
		sb.append(5);
		sb.append("/");
		String strI = sb.toString();
		
		//String strI = Integer.toString(command.getNummber());
		
//		StringBuffer convertString = null;
//		convertString.append(String.valueOf(command.getNummber()));
		
		return strI;
	}
	
	public Command convertToGameCommand(String commandString)
	{
		String[] segments = commandString.split("/");
		
		int commandNr = Integer.parseInt(segments[0]);
		Object commandData = segments[1];
		String commandType = segments[2];
		
		Command convertCommand = new Command(commandNr, commandData, commandType);
		
		return convertCommand;
	}
	
}
