package GameUtilities.Field;

public class FieldElement 
{
	private FieldState fieldState;
	private boolean isTaken;

	public FieldState getFieldState() 
	{
		return fieldState;
	}
	
	public boolean isTaken()
	{
		return this.isTaken;
	}

	public void setFieldState(FieldState fieldState) 
	{
		this.fieldState = fieldState;
	}
	
	public void setTaken()
	{
		this.isTaken = true;
	}
}
