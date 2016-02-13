package com.java.thread.threadgroup;

public class CustomThreadFactory {

	public static void main(String[] args) {
		ThreadGroup groupA = new ThreadGroup("Group-A");
		ThreadGroup groupB = new ThreadGroup("Group-B");
		CustomThread thread1 = new CustomThread(groupA, "Thread1");
		CustomThread thread2 = new CustomThread(groupA, "Thread2");
		CustomThread thread3 = new CustomThread(groupA, "Thread3");
		CustomThread thread4 = new CustomThread(groupA, "Thread4");
		CustomThread thread5 = new CustomThread(groupB, "Thread5");
		CustomThread thread6 = new CustomThread(groupB, "Thread6");

		System.out.println("\n\n---------Thread Details----------");
		System.out.println("\n\n----Group-A Threads-----");
		groupA.list();
		System.out.println("\n\n----Group-B Threads-----");
		groupB.list();

		CustomThread[] threadArray = new CustomThread[groupA.activeCount()];
		groupA.enumerate(threadArray);

		for (int i = 0; i < threadArray.length; i++)
			threadArray[i].pause();
		System.out.println("\n\n----GroupA threads paused-----\n\n");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}

		System.out.println("\n\nResuming GroupA threads....\n\n");
		for (int i = 0; i < threadArray.length; i++)
			threadArray[i].play();

		System.out.println("\n\n------Waiting for threads to finish------");
		try {
			thread1.join();
			thread2.join();
			thread3.join();
			thread4.join();
			thread5.join();
			thread6.join();
		} catch (InterruptedException exception) {
			System.out.println("Main thread interrupted");
			exception.printStackTrace();
		}
		System.out.println("\n\nMain Thread Exiting.");
	}
}
