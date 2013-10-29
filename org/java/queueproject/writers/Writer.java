package org.java.queueproject.writers;

import org.java.queueproject.util.Queue;


public class Writer extends Thread {

	private volatile Boolean stop = false;
	private Queue<String> queue;
	
	public Writer(Queue<String> anqueue) {
		queue = anqueue;
	}
	
	@Override
	public void run() {
		
		int i = 0;
		while (!stop) {
			queue.add("size " + queue.size() + " - " + " message " + i + " writer " + getName());
			i++;
		}
	}
	
	public void shutDown() {
		stop = true;
	}
}
