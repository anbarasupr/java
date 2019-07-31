
package executors.daemonThreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import common.DaemonThreadFactory;
import common.LoopTaskC;

public class DaemonThreadsUsingExecutors {
	public static void main(String[] args) {
		System.out.println("[" + Thread.currentThread().getName() + "] Main Thread starts here...");
		ExecutorService execService = Executors.newCachedThreadPool(new DaemonThreadFactory());
		execService.execute(new LoopTaskC(100));
		execService.execute(new LoopTaskC(200));
		execService.execute(new LoopTaskC(100));
		execService.execute(new LoopTaskC(200));

		System.out.println("[" + Thread.currentThread().getName() + "] Main Thread ends here...");

		execService.shutdown();
		// Note: It is not necessary to run daemon threads until all the user threads
		// need to be completed.
	}
}
