package controller.commands;

import controller.Controller;

/**
 * The Class Command.
 */

public abstract class CommandClass implements Command{
	
	/** The controler. */
	Controller c;
	
	/**
	 * Instantiates a new command class.
	 *
	 * @param c the c
	 */
	public CommandClass(Controller c)
	{
		this.c = c;
	}
}
