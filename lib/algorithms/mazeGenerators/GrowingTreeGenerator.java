package algorithms.mazeGenerators;
//Adapter from Growing Tree Algorithm to a Maze

public class GrowingTreeGenerator extends Maze3dGeneratorClass implements Updateable {

	private Maze3d m;
	private Position start;
	private Position end;

	@Override
	public Maze3d generate(int x, int y, int z) {
		m = new Maze3d();
		// Fixing the size for a correct maze

		if (x < 4 || y < 4) {
			System.out.println("Maze size is too small.");
			return null;
		}

		x = x / 2;
		y = y / 2;
		z = z / 2;

		m.setX(x * 2 + 1);
		m.setY(y * 2 + 1);
		m.setZ(z * 2 + 1);

		// Init all-1's maze
		int[][][] maze = new int[m.getX()][m.getY()][m.getZ()];
		for (int i = 0; i < m.getX(); i++) {
			for (int j = 0; j < m.getY(); j++) {
				for (int j2 = 0; j2 < m.getZ(); j2++) {
					maze[i][j][j2] = 1;
				}
			}
		}
		m.setMaze3d(maze);
		GrowingTreeGeneratorAlgorithm3D tg = new GrowingTreeGeneratorAlgorithm3D();

		// Condition 1 for stack condition 2 for vector
		tg.generate(x, y, z, this, 2);

		// Setting start and exit positions
		m.setRandomStart();
		m.setRandomExit();
		return m;
	}

	@Override
	public void update(Cell3D temp, Cell3D c2) {
		// Starting cell
		Cell3D c1 = new Cell3D(temp.getX() * 2 + 1, temp.getY() * 2 + 1, temp.getZ() * 2 + 1);
		int lastPosX = temp.getX() * 2 + 1;
		int lastPosY = temp.getY() * 2 + 1;
		int lastPosZ = temp.getZ() * 2 + 1;

		// Direction
		int x = c2.getX() - temp.getX();
		int y = c2.getY() - temp.getY();
		int z = c2.getZ() - temp.getZ();

		// Setting the starting point
		if (start == null) {
			this.start = new Position(c1.getX(), c1.getY(), c1.getZ());

		}

		// Painting the maze route
		this.m.paintMaze(c1.getX(), c1.getY(), c1.getZ(), 0);
		this.m.paintMaze(c1.getX() + x, c1.getY() + y, c1.getZ() + z, 0);
		this.m.paintMaze(c1.getX() + x * 2, c1.getY() + y * 2, c1.getZ() + z * 2, 0);

	}
}
