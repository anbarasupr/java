package threads.naming;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import common.LoopTaskB;
import common.NamedThreadFactory;

// Naming conventon for executors: pool-N-thread-M where N refers to pool no and M- refers to thread no inside the pool
public class NamingExecutorThreadsSecondWay {
	public static void main(String[] args) {
		System.out.println("[" + Thread.currentThread().getName() + "] Main Thread starts here...");
		ExecutorService execService = Executors.newCachedThreadPool();
		execService.execute(new LoopTaskB());
		execService.shutdown();
		
		ExecutorService execService1 = Executors.newCachedThreadPool();
		execService1.execute(new LoopTaskB());
		execService1.shutdown();
		
		
		ExecutorService execService2 = Executors.newCachedThreadPool(new NamedThreadFactory());
		execService2.execute(new LoopTaskB());
		execService2.shutdown();
		System.out.println("[" + Thread.currentThread().getName() + "] Main Thread ends here...");
	}
}
