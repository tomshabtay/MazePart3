package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

// TODO: Auto-generated Javadoc
/**
 * The Class DFS using the dfs algorithem for solving the search problem.
 */
public class DFS extends CommonSearcher implements Searcher {
	
	/** The stack. */
	private Stack<State<Object>> S = new Stack<State<Object>>();
	
	/** The Visited stack. */
	private Stack<State<Object>> Visited = new Stack<State<Object>>();

	
	
	/* (non-Javadoc)
	 * @see algorithms.search.Searcher#search(algorithms.search.Searchable)
	 */
	@Override
	public Solution search(Searchable searchable) {
		State startState = searchable.getStartState();
		State goalState = searchable.getGoalState();
		
		State<Object> u = new State<Object>();
		
		S.push(startState);
		while(!S.isEmpty())
		{
			u = S.pop();
			if(u.equals(goalState))
			{
				return backTrace(u,evaluatedNodes);
			}
			if(!Visited.contains(u))
			{
				Visited.push(u);
				ArrayList<State<Object>> neighbors = searchable.getAllPossibleMoves(u);
				for (State<Object> neighbor : neighbors)
				{
					neighbor.setCameFrom(u);
					S.push(neighbor);
					this.evaluatedNodes++;
				}
			}
			
		}
		return null;

	}

}
