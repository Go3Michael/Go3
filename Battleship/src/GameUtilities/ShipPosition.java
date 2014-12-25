package GameUtilities;
import java.awt.*;

public class ShipPosition 
{
	private Point xyPosition;
	private String alignment;
	private String direction;
	
	public Point getXyPosition()
	{		
		return xyPosition;
	}
	
	public void setXyPosition(Point xyPosition)
	{
		this.xyPosition = xyPosition;
	}
	
	public String getAlignment()
	{
		return alignment;
	}
	
	public void setAlignment(String alignment) 
	{
		this.alignment = alignment;
	}
	
	public String getDirection()
	{
		return direction;
	}
	
	public void setDirection(String direction)
	{
		this.direction = direction;
	}
	
	
}
