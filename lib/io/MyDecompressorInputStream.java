package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {

	private InputStream in;
	private int count;
	private int data;
	private int arrLength;


	public MyDecompressorInputStream(FileInputStream fileInputStream) {
		this.in = fileInputStream;
	}

	@Override
	public int read(byte [] arr) throws IOException {
		for (int i = 0; i < arr.length; i++) {
			if(i%2 == 0) {
				count = in.read();
			}
			else {
				data = in.read();
				
				//writing
				for (int j = 0; j < count; j++) {
					arr[arrLength] = (byte) data;
					arrLength++;
				}
			}
			
		}
		
		
		return 0;
	}

	@Override
	public int read() throws IOException {
		
		return in.read();
	}

}
