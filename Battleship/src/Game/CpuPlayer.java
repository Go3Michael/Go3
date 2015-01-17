package Game;

import java.util.Random;

public class CpuPlayer 
{


    protected int cpuMove(){
	int move = 0;
	int hightPos = 0;
	int widthPos = 0;
	
	Random posRandom  = new Random();
	
	
	hightPos = posRandom.nextInt(10);
	
	
	return move;
    }
}
