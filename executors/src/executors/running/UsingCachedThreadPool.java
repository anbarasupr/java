package executors.running;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import common.LoopTaskA;

public class UsingCachedThreadPool {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Main Thread starts here...");
		ExecutorService execService = Executors.newCachedThreadPool();

		execService.execute(new LoopTaskA());
		execService.execute(new LoopTaskA());
		execService.execute(new LoopTaskA());

		execService.execute(new LoopTaskA());
		execService.execute(new LoopTaskA());
		
		execService.shutdown();
		//execService.awaitTermination(0, TimeUnit.MILLISECONDS);

//		List<Runnable> list = execService.shutdownNow();
//		System.out.println("Pending task list:" + list);

//		execService.execute(new LoopTaskA());

		System.out.println("Main Thread ends here...");
	}
}
