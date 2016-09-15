package view;

import java.io.IOException;

/**
 * The Interface View.
 */
public interface View {
	
	/**
	 * Start.
	 */
	public void start();
	
	/**
	 * Exit.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void exit() throws IOException;
}
