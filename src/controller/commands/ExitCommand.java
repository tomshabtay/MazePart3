package controller.commands;

import java.io.IOException;

import controller.Controller;

public class ExitCommand extends CommandClass {

	public ExitCommand(Controller c) {
		super(c);
	}

	@Override
	public void doCommand(String args) {
		try {
			c.exit();
		} catch (IOException e) {
		
			e.printStackTrace();
		}

	}

}
