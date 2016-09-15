package algorithms.mazeGenerators;

import java.util.Random;
import java.util.Stack;
import java.util.Vector;

public class GrowingTreeGeneratorAlgorithm3D {

	// Board size
	private int x_size;
	private int y_size;
	private int z_size;

	// Board size
	private Cell3D[][][] cells;

	// Data Structures
	Stack<Cell3D> stack;
	Vector<Cell3D> vector;

	public void generate(int x, int y, int z, Updateable obj, int condition) {
		// Choosing data structure
		if (condition == 1)
			stack = new Stack<Cell3D>();
		if (condition == 2)
			vector = new Vector<Cell3D>();

		// Init cells
		cells = new Cell3D[x][y][z];

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				for (int j2 = 0; j2 < z; j2++) {
					cells[i][j][j2] = new Cell3D(i, j, j2);
				}
			}
		}

		// Setting size
		this.x_size = x - 1;
		this.y_size = y - 1;
		this.z_size = z - 1;

		// Add one random cell
		Cell3D c = getRandomCell();
		if (condition == 1)
			stack.push(c);
		if (condition == 2)
			vector.add(c);

		// Vars
		Cell3D temp = null;
		Cell3D n = null;
		do {
			// Choose temp cell
			if (condition == 1)
				temp = stack.pop();
			if (condition == 2)
				temp = this.randomFromVector(vector);

			// If c has unvisited neighbors:
			if (hasUnvisitedNeighbors(temp)) {
				// Choose an unvisited neighbor of cell temp. Call it n
				n = getUnvisitedNeighbors(temp);
				// Create a passage from c to n

				obj.update(temp, n);

				// Add n
				if (condition == 1) {
					stack.push(temp);
					stack.push(n);
				}
				if (condition == 2) {
					vector.add(n);
				}
				temp.setVisited(true);
				n.setVisited(true);
			}
			// else remove the cell
			else {
				if (condition == 2) {
					vector.remove(temp);
				}
			}
		} while ((condition == 1 && !stack.isEmpty()) || (condition == 2 && !vector.isEmpty()));

	}

	private Cell3D randomFromVector(Vector<Cell3D> vector) {
		Random r = new Random();
		int index = r.nextInt(vector.size());
		return vector.elementAt(index);
	}

	public Cell3D getCell(int x, int y, int z) {
		return cells[x][y][z];
	}

	public Cell3D getRandomCell() {
		Random r = new Random();
		return getCell(r.nextInt(x_size), r.nextInt(y_size), r.nextInt(z_size));
	}

	public Cell3D getUnvisitedNeighbors(Cell3D c1) {
		// Vars
		int option = 0;
		Random r = new Random();
		Cell3D c2 = null;

		// Options array
		int[] array = new int[6];
		for (int i = 0; i < array.length; i++) {
			array[i] = 0;
		}

		while (c2 == null) {
			// Lottery
			do {
				option = r.nextInt(6) + 1;
			} while (array[option - 1] == 1);// do while the number haven't been
												// choosen

			// Check if visited
			array[option - 1] = 1;// flag the number as checked;

			// Get
			switch (option) {
			case 1:// Right
				if (!rightCellVisited(c1)) {
					c2 = cells[c1.getX() + 1][c1.getY()][c1.getZ()];
				}
				break;
			case 2:// Left
				if (!leftCellVisited(c1)) {
					c2 = cells[c1.getX() - 1][c1.getY()][c1.getZ()];
				}
				break;
			case 3:// Up
				if (!upCellVisited(c1)) {
					c2 = cells[c1.getX()][c1.getY() - 1][c1.getZ()];
				}
				break;
			case 4:// Up
				if (!downCellVisited(c1)) {
					c2 = cells[c1.getX()][c1.getY() + 1][c1.getZ()];
				}
				break;
			case 5:// upLevel
				if (!upLevelCellVisited(c1)) {
					c2 = cells[c1.getX()][c1.getY()][c1.getZ() + 1];
				}
				break;

			case 6:// downLevel
				if (!downLevelCellVisited(c1)) {
					c2 = cells[c1.getX()][c1.getY()][c1.getZ() - 1];
				}
				break;

			}// endSwitch
		} // endWhile
		return c2;
	}

	public boolean hasUnvisitedNeighbors(Cell3D c1) {
		if (!rightCellVisited(c1))
			return true;
		if (!leftCellVisited(c1))
			return true;
		if (!upCellVisited(c1))
			return true;
		if (!downCellVisited(c1))
			return true;
		if (!downLevelCellVisited(c1))
			return true;
		if (!upLevelCellVisited(c1))
			return true;
		return false;

	}

	public boolean rightCellVisited(Cell3D c1) {
		if (c1.getX() + 1 > x_size)
			return true;
		if (!cells[c1.getX() + 1][c1.getY()][c1.getZ()].isVisited())
			return false;
		else
			return true;
	}

	public boolean leftCellVisited(Cell3D c1) {
		if (c1.getX() - 1 < 0)
			return true;
		if (!cells[c1.getX() - 1][c1.getY()][c1.getZ()].isVisited())
			return false;
		else
			return true;
	}

	public boolean upCellVisited(Cell3D c1) {
		if (c1.getY() - 1 < 0)
			return true;
		if (!cells[c1.getX()][c1.getY() - 1][c1.getZ()].isVisited())
			return false;
		else
			return true;
	}

	public boolean downCellVisited(Cell3D c1) {
		if (c1.getY() + 1 > y_size)
			return true;
		if (!cells[c1.getX()][c1.getY() + 1][c1.getZ()].isVisited())
			return false;
		else
			return true;
	}

	public boolean upLevelCellVisited(Cell3D c1) {
		if (c1.getZ() + 1 > z_size)
			return true;
		if (!cells[c1.getX()][c1.getY()][c1.getZ() + 1].isVisited())
			return false;
		else
			return true;
	}

	public boolean downLevelCellVisited(Cell3D c1) {
		if (c1.getZ() - 1 < 0)
			return true;
		if (!cells[c1.getX()][c1.getY()][c1.getZ() - 1].isVisited())
			return false;
		else
			return true;
	}

}
