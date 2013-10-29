package org.java.queueproject.readers;

import java.io.PrintWriter;

import org.java.queueproject.util.Queue;

public class Reader extends Thread {

	private volatile Boolean stop = false;
	private Queue<String> queue;
	private PrintWriter out;
	
	public Reader(Queue<String> anqueue, PrintWriter anout) {
		queue = anqueue;
		out = anout;
	}
	
	@Override
	public void run() {

		while (!stop) {
			out.println(queue.poll() + " reader " + getName());
			out.flush();
		}
	}
	
	public void shutDown() {
		stop = true;
	}

}
