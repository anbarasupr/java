package threads.naming;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import common.LoopTaskB;
import common.NamedThreadFactory;

public class UsingCachedThreadPool {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("[" + Thread.currentThread().getName() + "] Main Thread starts here...");
		ExecutorService execService = Executors.newCachedThreadPool(new NamedThreadFactory());

		execService.execute(new LoopTaskB());
		execService.execute(new LoopTaskB());
		execService.execute(new LoopTaskB());
		// for above 3 tasks, it create 3 threads in the pool and assign the task and all will be completed within 10 seconds
		TimeUnit.SECONDS.sleep(12);
		
		// after 12 seconds sleep, the executor re use the above 3 threads for the below first 3 tasks and create a new 2 threads for other last 2 task since we have tasks below.
		// so there is no concept of queue tasks here means no waiting.
		execService.execute(new LoopTaskB());
		execService.execute(new LoopTaskB());
		execService.execute(new LoopTaskB());
		execService.execute(new LoopTaskB());
		execService.execute(new LoopTaskB());
		
		execService.shutdown();
		System.out.println("[" + Thread.currentThread().getName() + "] Main Thread starts here...");
	}
}
