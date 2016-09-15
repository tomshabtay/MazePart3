package demo;

import java.util.Scanner;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;
import algorithms.search.BFS;
import algorithms.search.DFS;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

public class SearchTest {

	public static void main(String[] args) {

		//Generating a Maze
		Maze3d maze;
		GrowingTreeGenerator g = new GrowingTreeGenerator();
		//SimpleMaze3dGenerator g = new SimpleMaze3dGenerator();
		maze = g.generate(5, 5, 5);
		System.out.println("");
		System.out.println("MAZE:");
		maze.printMaze();
		

		System.out.println("");
		System.out.println("BFS:");

		SearchTest.testBFS(maze);
		
		System.out.println("");
		System.out.println("DFS:");
		
		SearchTest.testDFS(maze);

		
		

	}

	public static void testBFS(Maze3d maze){
		BFS bfs = new BFS();
		SearchableMaze searchableMaze = new SearchableMaze(maze);
		Solution sol = bfs.search(searchableMaze);
		sol.printSolution();
	}

	public static void testDFS(Maze3d maze){
		DFS dfs = new DFS();
		SearchableMaze searchableMaze = new SearchableMaze(maze);
		Solution sol = dfs.search(searchableMaze);
		sol.printSolution();
	}


	public static void testPositions(Maze3d maze){
		while(true){

			int x,y,z;
			Position p = new Position();
			String[] moves;

			Scanner reader = new Scanner(System.in);
			System.out.print("x: ");
			x = reader.nextInt();
			System.out.print("y: ");
			y = reader.nextInt();
			System.out.print("z: ");
			z = reader.nextInt();

			p.set(x, y, z);
			moves = maze.getPossibleMoves(p);

			for (String string : moves) {
				System.out.println(string);
			}
			maze.printMaze();
		}
	}


}
