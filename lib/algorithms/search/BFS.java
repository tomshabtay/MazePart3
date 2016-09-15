package algorithms.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;


/**
 * The Class BFS using the bfs algorithem for solving the search problem.
 */
public class BFS extends CommonSearcher {

	/** The open list. */
	private PriorityQueue<State<Object>> openList = new PriorityQueue<State<Object>>();
	
	/** The closed list. */
	private PriorityQueue<State<Object>> closedList = new PriorityQueue<State<Object>>();
	
	/** The iter. */
	private Iterator<State<Object>> iter;
	
	/* (non-Javadoc)
	 * @see algorithms.search.Searcher#search(algorithms.search.Searchable)
	 */
	@Override
	public Solution search(Searchable searchable) {
		State<Object> startState = searchable.getStartState();
		State<Object> goalState = searchable.getGoalState();

		openList.add(startState);

		while (!openList.isEmpty()) {
			State<Object> s = openList.poll();
			closedList.add(s);

			if (s.equals(goalState)) {
				return backTrace(s,evaluatedNodes);
			}

			ArrayList<State<Object>> neighbors = searchable.getAllPossibleMoves(s);
			for (State<Object> neighbor : neighbors) {

				neighbor.setCost(s.getCost()+1);
				neighbor.setCameFrom(s);

				if(!openList.contains(neighbor) && !closedList.contains(neighbor))
				{
					openList.add(neighbor);
					this.evaluatedNodes++;
				}
				else
				{
					iter = openList.iterator();
					while(iter.hasNext())
					{
						Object element = iter.next();
						if(neighbor.equals(element) && neighbor.compareTo((State) element)<0)
						{
							((State) element).setCameFrom(s);
						}
					}
				}


			}
		}
		return null;
	}

}
