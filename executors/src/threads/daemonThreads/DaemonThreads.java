
package threads.daemonThreads;

import common.LoopTaskC;

public class DaemonThreads {
	public static void main(String[] args) {
		System.out.println("[" + Thread.currentThread().getName() + "] Main Thread starts here...");

		Thread t1 = new Thread(new LoopTaskC(500), "Thread-1");
		Thread t2 = new Thread(new LoopTaskC(1000), "Thread-2");
		
		t2.setDaemon(true);
		
		t1.start();
		t2.start();
		System.out.println("[" + Thread.currentThread().getName() + "] Main Thread ends here...");
		
		//Note: It is not necessary to run daemon threads until all the user threads need to be completed. 
	}
}
