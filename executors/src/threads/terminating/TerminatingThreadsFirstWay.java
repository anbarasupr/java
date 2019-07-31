package threads.terminating;

import java.util.concurrent.TimeUnit;

import common.LoopTaskE;

public class TerminatingThreadsFirstWay {
	public static void main(String[] args) throws InterruptedException {
		String currentThreadname = Thread.currentThread().getName();
		System.out.println("[" + currentThreadname + "] Main Thread starts here...");

		LoopTaskE task1=new LoopTaskE();
		LoopTaskE task2=new LoopTaskE();
		LoopTaskE task3=new LoopTaskE();
		
		Thread t1=new Thread(task1);
		Thread t2=new Thread(task2);
		Thread t3=new Thread(task3);
		
		t1.start();
		t2.start();
		t3.start();		
		
		TimeUnit.MILLISECONDS.sleep(1000);
		
		task1.cancel();
		task2.cancel();
		task3.cancel();
		System.out.println("[" + currentThreadname + "] Main Thread ends here...");
	}
}
