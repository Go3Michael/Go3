package Game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import GameUtilities.Ship;
import GameUtilities.ShipPosition;
import GameUtilities.ShipType;
import GameUtilities.ShipAlignment;
import GameUtilities.Field.Field;

public class CpuPlayer
{
    private Field field = new Field();

    private Field fieldWithSettings;
    private List<Point> enemyShot = new ArrayList<Point>();
    private List<Point> cpuShot = new ArrayList<Point>();
    private int sizeShipTypeEnum = ShipType.values().length;
    private int sizeShipAlignmentEnum = ShipAlignment.values().length;
    private Point actShot;

    public void test()
    {
	Point point = new Point();
	System.out.println(this.cpuShot.toString());
	System.out.println("Schuss: " + shotAtEnemie());
	System.out.println(this.cpuShot.toString());
	System.out.println("ende");
    }

    /**
     * Ermittelt, ob bereits eine Schuss auf die Koordinaten abgefeuert wurde, wenn ja, wird werden neue Koordinaten
     * ermittelt.
     * 
     * @return
     */
    public Point shotAtEnemie()
    {

	boolean[] shotFlag = new boolean[2];
	shotFlag[0] = true;
	shotFlag[1] = true;

	this.actShot = cpuRandomShot();

	while ((shotFlag[0] == true) && (shotFlag[1] == true))
	{
	    for (Point shot : this.cpuShot)
	    {
		if (shot.equals(this.actShot))
		{
		    shotFlag[1] = false;
		    break;
		}
	    }
	    this.cpuShot.add(this.actShot);
	}
	return this.actShot;
    }

    private void setShip()
    {
	int posX = this.getRandomValue(10);
	int posY = this.getRandomValue(10);
	
	int shipAlignmentRand = this.getRandomValue(sizeShipAlignmentEnum);
	String alignment = "HORIZONTAL";
	
	Point point = new Point(posX, posY);
	ShipPosition position1 = new ShipPosition(point, alignment);
	ShipPosition position2 = new ShipPosition(point, alignment);
	ShipPosition position3 = new ShipPosition(point, alignment);
	
	
	Ship ship1 = new Ship(position1, ShipType.AIRCARRER, 1);
	Ship ship2 = new Ship(position2, ShipType.DESTROYER, 2);
	Ship ship3= new Ship(position3, ShipType.YELLOW_SUBMARINE, 3);
	

	Field field = new Field();
	field.setShipOnField(ship1);
	
//        if(alignment == ShipAlignment.HORIZONTAL){
//            
//    	}
//    	else{
//    	    // länge des schiffes erweitern
//    	}
	
	

    }

 
    
    
    /**
     * Ermittelt einen Zufallszahl die größer als 0 ist und im Bereich vom übergebenen Wert.
     * 
     * @param integer
     *            valueRange
     * @return integer
     */
    private int getRandomValue(int valueRange)
    {

	if (valueRange == 0)
	{
	    ++valueRange;
	}
	int returnValue = 0;
	Random rand = new Random();
	returnValue = rand.nextInt(valueRange) + 1;

	return returnValue;
    }

//    /**
//     * Gibt die Tye des Schiffes zurück
//     * 
//     * @param integer
//     *            index
//     * @return string
//     */
//    private Enum getShipTypeAlignment(int index)
//    {
//	ShipType shipAlignmentString;
//	ShipAlignment shipAlignmentValue;
//	shipAlignmentValue = ShipAlignment.values()[index];
//
//	// shipAlignmentString = shipAlignmentValue.toString();
//
//	return shipAlignmentValue;
//    }

    /**
     * Setzt einen Schuss mit dem Zufallsgenerator ab
     * 
     * @return
     */
    private Point cpuRandomShot()
    {
	int posX = this.getRandomValue(10);
	int posY = this.getRandomValue(10);
	Point setShot = new Point(posX, posY);

	return setShot;
    }

}
