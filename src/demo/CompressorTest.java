package demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

public class CompressorTest {

	public static void main(String[] args) throws IOException {
		Maze3d maze;
		// GrowingTreeGenerator g = new GrowingTreeGenerator();
		SimpleMaze3dGenerator g = new SimpleMaze3dGenerator();
		maze = g.generate(4, 4, 4);
		// maze.printMaze();

		// save it to a file
		OutputStream out = new MyCompressorOutputStream(new FileOutputStream("1.maz"));
		out.write(maze.toByteArray());
		out.flush();
		out.close();

		/*
		 * FileInputStream intest=new FileInputStream("1.maz");
		 * 
		 * int c; while ((c = intest.read()) != -1) { System.out.print(c+","); }
		 * 
		 */
		InputStream in = new MyDecompressorInputStream(new FileInputStream("1.maz"));
		byte b[] = new byte[maze.toByteArray().length];
		in.read(b);
		in.close();

		Maze3d loaded = new Maze3d(b);

		// loaded.printMaze();
		System.out.println(loaded.equals(maze));

	}

	public void func1() throws IOException {
		FileInputStream in = new FileInputStream("1.maz");

		int c;
		while ((c = in.read()) != -1) {
			System.out.print(c + ",");
		}

	}
}