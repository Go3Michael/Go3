package GameUtilities;
import java.awt.*;

public class ShipPosition 
{
	private Point xyPosition;
	private String alignment;
	
	public ShipPosition(Point point, String alignment)
	{
		this.xyPosition = point;
		this.alignment = alignment;
	}
	
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
	
	public String toString()
	{
		return Integer.toString(xyPosition.x) + "," + Integer.toString(xyPosition.y) + "," + this.alignment;
	}
	
	
}
