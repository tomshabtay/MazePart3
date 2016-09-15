package io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {

	private OutputStream out;
	private Byte lastByte;
	private int count = 1;

	public MyCompressorOutputStream(FileOutputStream fileOutputStream) {
		this.out = fileOutputStream;
	}

	public void write(byte[] arr) throws IOException {

		lastByte = arr[0];


		for (int i = 0; i < (arr.length-1); i++) {

			if (lastByte == arr[i + 1]) {
				count++;
			}

			else {
				write(count);
				lastByte = arr[i + 1];
				count = 1;
			}
		}

		write(count);	
	}


	@Override
	public void write(int b) throws IOException {
		if(b<=255){
			out.write(b);
			out.write(lastByte);
		}
		else {
			out.write(255);
			out.write(lastByte);
			write(b-255);
		}

	}

	public void writeSize(int size) throws IOException{
		out.write(size/100);
		out.write(size%100);
	}
}
