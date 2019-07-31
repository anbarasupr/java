package threads.terminating;

import java.util.concurrent.TimeUnit;

import common.LoopTaskF;

public class TerminatingThreadsSecondWay {
	public static void main(String[] args) throws InterruptedException {
		String currentThreadname = Thread.currentThread().getName();
		System.out.println("[" + currentThreadname + "] Main Thread starts here...");

		LoopTaskF task1 = new LoopTaskF();
		LoopTaskF task2 = new LoopTaskF();
		LoopTaskF task3 = new LoopTaskF();

		Thread t1 = new Thread(task1, "MyThread-1");
		Thread t2 = new Thread(task2, "MyThread-2");
		Thread t3 = new Thread(task3, "MyThread-3");

		t1.start();
		t2.start();
		t3.start();

		TimeUnit.MILLISECONDS.sleep(300);

		System.out.println("[" + currentThreadname + "] Interrupting " + t1.getName() + ". . .");
		t1.interrupt();
		System.out.println("[" + currentThreadname + "] Interrupting " + t2.getName() + ". . .");
		t2.interrupt();
		System.out.println("[" + currentThreadname + "] Interrupting " + t3.getName() + ". . .");
		t3.interrupt();
		System.out.println("[" + currentThreadname + "] Main Thread ends here...");
	}
}
