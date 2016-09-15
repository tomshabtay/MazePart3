package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.commands.Command;

public class CLI implements Runnable {
	private BufferedReader in;
	private PrintWriter out;
	private HashMap<String, Command> commands;

	public CLI(BufferedReader in, PrintWriter out, HashMap<String, Command> commands) {
		this.in = in;
		this.out = out;
		this.commands = commands;
	}

	/**
	 * Start the loop.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void start() throws IOException {
		String str = null;
		Command command = null;

		do {
			// Ask user for a command
			out.println("Enter Command:");
			out.flush();

			// Read user input
			str = in.readLine();

			command = commands.get(str.split(" ")[0]);

			// Execute the command

			if (command != null) {
				if (str.split(" ").length > 1) {

					command.doCommand(str.substring(str.split(" ")[0].length() + 1));
				} else {
					if (!str.equalsIgnoreCase("exit"))
						out.println("Args needed, try again.");
				}
			} else {
				out.println("Invalid Input.");
			}

		} while (!str.equalsIgnoreCase("exit"));
		command.doCommand(null);

	}

	@Override
	public void run() {
		try {
			this.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void setCommands(HashMap<String, Command> commands) {
		this.commands = commands;
	}

	public void printToOut(String str) {
		out.print(str);
	}

	public void exit() throws IOException {
		in.close();
		out.close();

	}

}
