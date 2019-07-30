package executors.running;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import common.LoopTaskA;

public class UsingSingleThreadPool {

	public static void main(String[] args) {
		System.out.println("Main Thread starts here...");
		ExecutorService execService = Executors.newSingleThreadExecutor();

		execService.execute(new LoopTaskA());
		execService.execute(new LoopTaskA());
		execService.execute(new LoopTaskA());
		
		execService.shutdown();
		System.out.println("Main Thread ends here...");
	}
}
