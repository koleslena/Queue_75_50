package org.java.queueproject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.java.queueproject.readers.Reader;
import org.java.queueproject.util.Queue;
import org.java.queueproject.writers.Writer;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Queue<String> queue = new Queue<String>(100);
		
		File flt = new File("reader.txt");
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(flt)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Writer w1 = new Writer(queue);
		Writer w2 = new Writer(queue);
		Writer w3 = new Writer(queue);
		
		Reader r1 = new Reader(queue, out);
		Reader r2 = new Reader(queue, out);
		
		w1.start();
		w2.start();
		w3.start();
		
		r1.start();
		r2.start();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		r1.shutDown();
		r2.shutDown();
		
		w1.shutDown();
		w2.shutDown();
		w3.shutDown();
		
	}

}
