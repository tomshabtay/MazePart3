
package controller;

import java.io.IOException;

import javax.swing.text.View;

import model.Model;


/**
 * The Interface Controller.
 */

public interface Controller {

	/**
	 * Prints to view.
	 *
	 * @param str the message
	 */
	public void printToOut(String str);
	
	/**
	 * Generate maze.
	 *
	 * @param name the name
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public void GenerateMaze(String name, int x, int y, int z);
	
	/**
	 * Display maze.
	 *
	 * @param name of the maze to display
	 */
	public void displayMaze(String name);
	
	/**
	 * Display cross section.
	 *
	 * @param args the args
	 */
	public void displayCross(String args);
	
	/**
	 * Save maze to a file.
	 *
	 * @param args
	 */
	public void saveMaze(String args);
	
	/**
	 * Load maze from a file.
	 *
	 * @param args
	 */
	public void loadMaze(String args);
	
	/**
	 * Solve maze.
	 *
	 * @param args
	 */
	public void solveMaze(String args);
	
	/**
	 * Display solution.
	 *
	 * @param args
	 */
	public void displaySolution(String args);
	
	/**
	 * Exit.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void exit() throws IOException;
}
