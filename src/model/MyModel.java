package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.search.BFS;
import algorithms.search.DFS;
import algorithms.search.SearchableMaze;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import controller.Controller;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

/**
 * The Class MyModel.
 */
public class MyModel implements Model {

	/** The mazes. */
	private HashMap<String, Maze3d> mazes;

	/** The solutions. */
	private HashMap<String, Solution> solutions;

	/** The Controller. */
	private Controller c;

	/**
	 * Instantiates a new my model.
	 */
	public MyModel() {
		mazes = new HashMap<String, Maze3d>();
		solutions = new HashMap<String, Solution>();
	}

	/**
	 * Sets the controller.
	 *
	 * @param c
	 *            the new controller
	 */
	public void setController(Controller c) {
		this.c = c;
	}

	/**
	 * Genrate maze.
	 *
	 * @param name
	 *            the name
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param z
	 *            the z
	 */
	public void genrateMaze(String name, int x, int y, int z) {
		Maze3d maze = new Maze3d();
		GrowingTreeGenerator g = new GrowingTreeGenerator();
		maze = g.generate(x, y, z);
		mazes.put(name, maze);
		c.printToOut("a new maze '" + name + "' have been added successfuly.\n");
	}

	/**
	 * Display the maze.
	 *
	 * @param name
	 *            the name
	 */
	public void displayMaze(String name) {
		mazes.get(name).printMaze();
	}

	/**
	 * Display cross section.
	 *
	 * @param args
	 *            the args
	 */
	public void displayCross(String args) {
		String[] argsArray = args.split(" ");
		Maze3d m = mazes.get(argsArray[2]);
		int[][] m2d;

		if (argsArray[0].equalsIgnoreCase("x")) {
			m2d = m.getCrossSectionByX(Integer.valueOf(argsArray[1]));
			m.printCrossSectionByX(m2d);

		}
		if (argsArray[0].equalsIgnoreCase("y")) {
			m2d = m.getCrossSectionByY(Integer.valueOf(argsArray[1]));
			m.printCrossSectionByY(m2d);
		}
		if (argsArray[0].equalsIgnoreCase("z")) {
			m2d = m.getCrossSectionByZ(Integer.valueOf(argsArray[1]));
			m.printCrossSectionByZ(m2d);
		}

	}

	/**
	 * Save maze to a file.
	 *
	 * @param args
	 *            the args
	 */
	public void saveMaze(String args) {

		try {
			String[] argsArray = args.split(" ");
			Maze3d m = mazes.get(argsArray[0]);
			String filename = argsArray[1] + ".maz";
			int sizeInt = m.toByteArray().length;

			OutputStream out = new MyCompressorOutputStream(new FileOutputStream(filename));

			// Saving the maze size
			((MyCompressorOutputStream) out).writeSize(sizeInt);

			// Saving the maze
			out.write(m.toByteArray());
			out.flush();
			out.close();
			c.printToOut("Maze saved.");
		} catch (Exception e) {
			c.printToOut("Problem saving the maze.");
			// e.printStackTrace();
		}
	}

	/**
	 * Load maze.
	 *
	 * @param args
	 *            the args
	 */
	public void loadMaze(String args) {
		try {
			String[] argsArray = args.split(" ");
			String mazename = argsArray[0];
			String filename = argsArray[1] + ".maz";
			int sizeA, sizeB, size;

			InputStream in = new MyDecompressorInputStream(new FileInputStream(filename));
			int aint = in.read();
			int bint = in.read();
			int sizeint = aint * 100 + bint;

			byte b[] = new byte[sizeint];
			in.read(b);
			in.close();
			Maze3d m = new Maze3d(b);
			mazes.put(mazename, m);
			c.printToOut("Maze loaded.");
		} catch (Exception e) {
			// c.printToOut("Problem loading the maze.");
			e.printStackTrace();
		}
	}

	/**
	 * Solve maze.
	 *
	 * @param args
	 *            the args
	 */
	public void solveMaze(String args) {
		String[] argsArray = args.split(" ");
		Maze3d maze = mazes.get(argsArray[0]);
		SearchableMaze searchableMaze = new SearchableMaze(maze);
		Searcher searcher;
		Solution solution = null;

		// Creating solution
		if (argsArray[1].equalsIgnoreCase("bfs")) {
			searcher = new BFS();
			solution = searcher.search(searchableMaze);
		} else if (argsArray[1].equalsIgnoreCase("dfs")) {
			searcher = new DFS();
			solution = searcher.search(searchableMaze);
		}

		this.solutions.put(argsArray[0], solution);
		c.printToOut("Solution for '" + argsArray[0] + "' is ready.\n");

	}

	/**
	 * Display the solution.
	 *
	 * @param args
	 *            the args
	 */
	public void displaySolution(String args) {
		String[] argsArray = args.split(" ");
		solutions.get(argsArray[0]).printSolution();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Model#exit()
	 */
	@Override
	public void exit() {

	}
}
