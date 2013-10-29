package org.java.queueproject.util;

import java.util.Vector;

public class Queue<E> {
	
	private final Object mutex = new Object();

	private Vector<E> queue = new Vector<E>();
	
	private final int size;
	
	public Queue(int s) {
		size = s;
	}
	
	public Object getMutex() {
		return mutex;
	}

	public boolean add(E e) {
		Boolean res = false;
		synchronized (mutex) {
			while(queue.size() > 0.75 * size) {
				try {
					mutex.wait();
				} catch (Exception e1) {
					
				}
			}
			res = queue.add(e);
		}
		return res;
	}
	
	public E element() {
		return queue.elementAt(0);
	}
	
	public E poll() {
		synchronized (mutex) {
			if(!queue.isEmpty()) {
				return remove();
			}
		}
		return null;
	}
	
	public E remove() {
		E e = queue.remove(0);
		if(queue.size() < 0.5 * size) {
			try {
				mutex.notify();
			} catch (Exception exc) {
			}
		}
		return e;
	}
	
	public int size() {
		return queue.size();
	}
}
