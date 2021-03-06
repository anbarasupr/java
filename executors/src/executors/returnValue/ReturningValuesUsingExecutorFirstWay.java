package executors.returnValue;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import common.CalculationTaskA;
import common.LoopTaskA;
import common.NamedThreadFactory;
//Process task results in the sequence which we retrieve them
public class ReturningValuesUsingExecutorFirstWay {
	public static void main(String[] args) {
		System.out.println("[" + Thread.currentThread().getName() + "] Main Thread starts here...");

		ExecutorService execService = Executors.newCachedThreadPool(new NamedThreadFactory());

		Future<Integer> result1 = execService.submit(new CalculationTaskA(2, 3, 7));
		Future<Integer> result2 = execService.submit(new CalculationTaskA(4, 3, 2));
		Future<Integer> result3 = execService.submit(new CalculationTaskA(5, 5, 3));

		// Runnable arguments
		Future<?> result4 = execService.submit(new LoopTaskA());
		Future<Double> result5 = execService.submit(new LoopTaskA(), 999.999);
		execService.shutdown();

		try {
			// process results in the order which we retrieve them rather than the results they arrive or complete
			System.out.println("@@@@@@ Result-1 = " + result1.get()); // the call to get method blocks and so it will printed in sequence only.
			System.out.println("@@@@@@ Result-2 = " + result2.get());
			System.out.println("@@@@@@ Result-3 = " + result3.get());
			System.out.println("@@@@@@ Result-4 = " + result4.get());
			System.out.println("@@@@@@ Result-5 = " + result5.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("[" + Thread.currentThread().getName() + "] Main Thread ends here...");
	}
}
