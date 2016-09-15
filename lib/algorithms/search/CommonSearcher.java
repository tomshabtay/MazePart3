package algorithms.search;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonSearcher.
 */
public abstract class CommonSearcher implements Searcher {
	
	/** The evaluated nodes. */
	protected int evaluatedNodes;
	
	/**
	 * Back trace.
	 *
	 * @param goalState the goal state
	 * @param evaluatedNodes the evaluated nodes
	 * @return the solution
	 */
	protected Solution backTrace(State goalState,int evaluatedNodes) {
		Solution sol = new Solution();
		sol.setEvaluatedNodes(evaluatedNodes);
		ArrayList<State> states = new ArrayList<State>();
		State tempState = goalState;
		
		while(tempState.getCameFrom()!=null)
		{
			states.add(tempState);
			tempState = tempState.getCameFrom();
		}
		
		sol.setStates(states);
		return sol;
	}

}
