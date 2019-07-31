
package executors.alive;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import common.CalculationTaskA;
import common.LoopTaskB;
import common.NamedThreadFactory;

public class ExecutorThreadsAliveCheck {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		String currentThreadname = Thread.currentThread().getName();
		System.out.println("[" + currentThreadname + "] Main Thread starts here...");

		ExecutorService execService = Executors.newCachedThreadPool(new NamedThreadFactory());
		Future<?> f1 = execService.submit(new LoopTaskB());
		Future<Integer> f2 = execService.submit(new CalculationTaskA(3, 4, 2));

		FutureTask<?> f3 = new FutureTask<Void>(new LoopTaskB(), null);
		FutureTask<Integer> f4 = new FutureTask<Integer>(new LoopTaskB(), 999);
		FutureTask<Integer> f5 = new FutureTask<Integer>(new CalculationTaskA(5, 5, 5));

		execService.execute(f3);
		execService.execute(f4);
		execService.execute(f5);

		execService.shutdown();

		for (int i = 0; i < 5; i++) {
			TimeUnit.SECONDS.sleep(1);
			System.out.println("[" + currentThreadname + "] ITR - " + i + " -> 'LoopTaskB-1' done = " + f1.isDone());
			System.out.println("[" + currentThreadname + "] ITR - " + i + " -> 'CalcTaskA-1' done = " + f2.isDone());
			System.out.println("[" + currentThreadname + "] ITR - " + i + " -> 'LoopTaskB-2' done = " + f3.isDone());
			System.out.println("[" + currentThreadname + "] ITR - " + i + " -> 'LoopTaskB-3' done = " + f4.isDone());
			System.out.println("[" + currentThreadname + "] ITR - " + i + " -> 'CalcTaskA-2' done = " + f5.isDone());
		}

		System.out.println("[" + currentThreadname + "] Retrieving Results now. . .  $$$$$");
		System.out.println("[" + currentThreadname + "] LoopTaskB-1 result = "+f1.get());
		System.out.println("[" + currentThreadname + "] CalcTaskA-1 result = "+f2.get());
		System.out.println("[" + currentThreadname + "] LoopTaskB-2 result = "+f3.get());
		System.out.println("[" + currentThreadname + "] LoopTaskB-3 result = "+f4.get());
		System.out.println("[" + currentThreadname + "] CalcTaskA-2 result = "+f5.get());
		
		System.out.println("[" + currentThreadname + "] Main Thread ends here...");
	}
}
