package executors.running;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import common.LoopTaskA;

public class UsingCachedThreadPool {

	public static void main(String[] args) {
		System.out.println("Main Thread starts here...");
		ExecutorService execService = Executors.newCachedThreadPool();

		execService.execute(new LoopTaskA());
		execService.execute(new LoopTaskA());
		execService.execute(new LoopTaskA());

		execService.execute(new LoopTaskA());
		execService.execute(new LoopTaskA());
		
		execService.shutdown();

//		List<Runnable> list = execService.shutdownNow();
//		System.out.println("Pending task list:" + list);

//		execService.execute(new LoopTaskA());

		System.out.println("Main Thread ends here...");
	}
}
