package algorithms.search;

import java.util.ArrayList;


/**
 * The Interface Searchable.
 */
public interface Searchable {
	
	/**
	 * Gets the start state.
	 *
	 * @return the start state
	 */
	State<Object> getStartState();
	
	/**
	 * Gets the goal state.
	 *
	 * @return the goal state
	 */
	State<Object> getGoalState();
	
	/**
	 * Gets the all possible moves.
	 *
	 * @param s the s
	 * @return the all possible moves
	 */
	ArrayList<State<Object>> getAllPossibleMoves(State<Object> s);
}
