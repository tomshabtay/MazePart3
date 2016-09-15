package controller;

import java.io.IOException;
import java.util.HashMap;

import controller.commands.Command;
import controller.commands.DirCommand;
import controller.commands.DisplayCrossCommand;
import controller.commands.DisplayMazeCommand;
import controller.commands.DisplaySolutionCommand;
import controller.commands.ExitCommand;
import controller.commands.GenerateMazeCommand;
import controller.commands.LoadMazeCommand;
import controller.commands.SaveMazeCommand;
import controller.commands.SolveMazeCommand;
import model.MyModel;
import view.MyView;

/**
 * The Class MyController.
 */
public class MyController implements Controller {

	/** The view. */
	private MyView view;

	/** The model. */
	private MyModel model;

	/** The commands. */
	private HashMap<String, Command> commands;

	/**
	 * Instantiates a new my controller.
	 *
	 * @param view
	 *            the view
	 * @param model
	 *            the model
	 */
	public MyController(MyView view, MyModel model) {
		// Protocol Commands initiating
		this.commands = new HashMap<String, Command>();
		commands.put("dir", new DirCommand(this));
		commands.put("generate_3d_maze", new GenerateMazeCommand(this));
		commands.put("display", new DisplayMazeCommand(this));
		commands.put("display_cross_section", new DisplayCrossCommand(this));
		commands.put("save_maze", new SaveMazeCommand(this));
		commands.put("load_maze", new LoadMazeCommand(this));
		commands.put("solve", new SolveMazeCommand(this));
		commands.put("display_solution", new DisplaySolutionCommand(this));
		commands.put("exit", new ExitCommand(this));

		// Initiating view and model
		this.view = view;
		this.model = model;

		// Updating the Protocol Commands for the view
		view.setCommands(commands);

		// Updating the controller in view and model to this
		view.setController(this);
		model.setController(this);
	}

	/**
	 * Start the program via the controller
	 */
	public void start() {
		// Starting the CLI loop in view
		view.start();
	}

	/*
	 * 
	 * 
	 * @see controller interface for explanations
	 */
	public void printToOut(String str) {
		view.printToOut(str);
	}

	public void GenerateMaze(String name, int x, int y, int z) {
		model.genrateMaze(name, x, y, z);
	}

	public void displayMaze(String name) {
		model.displayMaze(name);
	}


	public void displayCross(String args) {
		model.displayCross(args);
	}


	public void displaySolution(String args) {
		model.displaySolution(args);
	}


	public void solveMaze(String args) {
		model.solveMaze(args);
	}

	public void saveMaze(String args) {
		model.saveMaze(args);
	}

	public void loadMaze(String args) {
		model.loadMaze(args);
	}

	@Override
	public void exit() throws IOException {
		model.exit();
		view.exit();

	}
}
