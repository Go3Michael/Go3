package GameUtilities.AttackPosition;

import java.awt.Point;

public class AttackPosition
{
	private Point xyPosition;

	public AttackPosition(Point point)
	{
		this.setXyPosition(point);
	}

	public Point getXyPosition()
	{
		return xyPosition;
	}

	public void setXyPosition(Point xyPosition)
	{
		this.xyPosition = xyPosition;
	}
	
	public String toString()
	{
		return Integer.toString(xyPosition.x) + "," + Integer.toString(xyPosition.y);
	}
}
