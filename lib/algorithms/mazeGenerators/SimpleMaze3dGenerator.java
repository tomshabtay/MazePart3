package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMaze3dGenerator extends Maze3dGeneratorClass {

	@Override
	public Maze3d generate(int x, int y, int z) {
		Maze3d m = new Maze3d();

		// init size
		m.setX(x);
		m.setY(y);
		m.setZ(z);

		// init maze
		int[][][] maze = new int[x][y][z];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				for (int j2 = 0; j2 < z; j2++) {
					maze[i][j][j2] = 1;
				}
			}
		}

		Random r = new Random();
		Position p1 = this.randomPosition(x, y, z);
		Position p2 = this.randomPosition(x, y, z);

		p1.setZ(0);
		p2.setZ(0);

		m.setStartPosition(p1);

		Position.setLimits(x - 1, y - 1, z - 1);

		for (int level = 0; level < z; level++) {

			while (!p1.isEqualX(p2)) {
				maze[p1.getX()][p1.getY()][p1.getZ()] = 0;
				if (p1.getX() < p2.getX())
					p1.incX();
				if (p1.getX() > p2.getX())
					p1.decX();
				maze[p1.getX()][p1.getY()][p1.getZ()] = 0;
			}

			while (!p1.isEqualY(p2)) {

				if (p1.getY() < p2.getY())
					p1.incY();
				if (p1.getY() > p2.getY())
					p1.decY();
				maze[p1.getX()][p1.getY()][p1.getZ()] = 0;
			}

			p2 = this.randomPosition(x, y, z);
			p1.incZ();
			p2.setZ(p1.getZ());
		}

		m.setExitPosition(p2);

		// set maze
		m.setMaze3d(maze);
		return m;
	}

}
