package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Controller;
import controller.commands.Command;


/**
 * The Class MyView.
 */
public class MyView implements View {
	
	/** The cli. */
	private CLI cli;
	
	/** The commands. */
	private HashMap<String, Command> commands;
	
	/** The c. */
	private Controller c;

	/**
	 * Instantiates a new my view.
	 *
	 * @param in the in
	 * @param out the out
	 */
	public MyView(BufferedReader in, PrintWriter out) {
		cli = new CLI(in, out, null);
	}

	/**
	 * Sets the controller.
	 *
	 * @param c the new controller
	 */
	public void setController(Controller c) {
		this.c = c;
	}

	/* (non-Javadoc)
	 * @see view.View#start()
	 */
	@Override
	public void start() {
		// creating a thread and starting it
		Thread cli = new Thread(this.cli);
		cli.start();
	}

	/**
	 * Sets the commands.
	 *
	 * @param commands the commands
	 */
	public void setCommands(HashMap<String, Command> commands) {
		this.commands = commands;
		cli.setCommands(commands);
	}

	/**
	 * Prints the to out.
	 *
	 * @param str the str
	 */
	public void printToOut(String str) {
		cli.printToOut(str);
	}

	/* (non-Javadoc)
	 * @see view.View#exit()
	 */
	@Override
	public void exit() throws IOException {
		cli.exit();

	}

}
