//The Cell class is used by the Growing Tree algorithem

package algorithms.mazeGenerators;

public class Cell3D {

	private int x, y, z;
	private boolean visited;
	private int value;

	private static int count = 1;

	public Cell3D(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.visited = false;
		this.value = 0;
	}

	public Cell3D() {
	}

	public Cell3D(Cell3D c) {
		this.x = c.x;
		this.y = c.y;
		this.z = c.z;
		this.visited = c.visited;
		this.value = c.value;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void updateValue() {
		this.value = count++;

	}
}
