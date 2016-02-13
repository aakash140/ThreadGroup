package com.java.thread.threadgroup;

public class CustomThread extends Thread {
	private boolean pause;

	CustomThread(ThreadGroup customThreadGroup, String threadName) {
		super(customThreadGroup, threadName);
		System.out.println(
				this.getName() + " from thread group " + this.getThreadGroup().getName() + " is being created.");
		pause = false;
		this.start();
	}

	@Override
	public void run() {
		try {
			for (int i = 5; i > 0; i--) {
				System.out.println(this.getName() + ": " + i);
				Thread.sleep(1000);
				synchronized (this) {
					while (pause)
						wait();
				}
			}
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
		System.out.println(this.getName() + " finished its execution.");
	}

	synchronized void pause() {
		pause = true;
	}

	synchronized void play() {
		pause = false;
		notify();
	}
}