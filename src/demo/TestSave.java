package demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

public class TestSave {

	public static void main(String[] args) throws IOException {
		Maze3d maze;
		GrowingTreeGenerator g = new GrowingTreeGenerator();
		//SimpleMaze3dGenerator g = new SimpleMaze3dGenerator();
		maze = g.generate(50, 5, 5);
		int sizeInt = maze.toByteArray().length;
		System.out.println(sizeInt);

		// save it to a file
		OutputStream out = new MyCompressorOutputStream(new FileOutputStream("1.maz"));
		
		((MyCompressorOutputStream) out).writeSize(sizeInt);
		out.write(maze.toByteArray());
		out.flush();
		out.close();

		
		FileInputStream intest=new FileInputStream("1.maz");
		 
		int c; while ((c = intest.read()) != -1) { System.out.print(c+","); }
		 
		
		InputStream in = new MyDecompressorInputStream(new FileInputStream("1.maz"));
		int aint = in.read();
		int bint = in.read();
		int sizeint = aint*100 + bint;
		System.out.println();
		System.out.println(aint);
		System.out.println(bint);
		System.out.println(sizeint);
		byte b[] = new byte[sizeint];
		in.read(b);
		in.close();

		Maze3d loaded = new Maze3d(b);

		// loaded.printMaze();
		System.out.println(loaded.equals(maze));


	}

}
