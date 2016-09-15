package algorithms.mazeGenerators;

import java.util.Random;

public abstract class Maze3dGeneratorClass implements Maze3dGenerator {

	public abstract Maze3d generate(int x,int y,int z);
	
	public Position randomPosition(int x,int y,int z)
	{
		Position p = new Position();
		Random r = new Random();
		
		p.setX(r.nextInt(x));
		p.setY(r.nextInt(y));
		p.setZ(r.nextInt(z));
		return p;
	}

	@Override
	public String measureAlgorithmTime(int x,int y,int z) {
		long start = System.currentTimeMillis();
		generate(x,y,z);
		long finish = System.currentTimeMillis();
		return "Time:"+String.valueOf(finish-start)+"ms";
	}

}
