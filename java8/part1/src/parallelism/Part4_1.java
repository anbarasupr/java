package parallelism;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

// Parallel does not always fast
public class Part4_1 {

	static int MAX = 1000;

	// huge computation method
	public static double compute(double number) {
		double result = 0;
		for (int i = 0; i < MAX; i++) {
			for (int j = 0; j < MAX; j++) {
				result += Math.sqrt(i * j);
			}
		}
		return result;
	}

	public static void main(String a[]) throws InterruptedException {
		// sequential output
		double result = 0;
		long start = System.currentTimeMillis();
		result = IntStream.range(0, MAX).mapToDouble(Part4_1::compute).sum();
		System.out.println("Sequential total time: " + (System.currentTimeMillis() - start) + " ms, result: " + result);

		// parallel output
		start = System.currentTimeMillis();
		// result = IntStream.range(0, MAX).parallel().mapToDouble(IsParallelAlwaysFast::compute).sum();
		// System.out.println("Parallel total time: " + (System.currentTimeMillis() - start) + " ms, result: " + result);

		
		// parallel output using fork join pool
		ForkJoinPool pool = new ForkJoinPool(20);
		pool.submit(() -> {
			long strt = System.currentTimeMillis();
			double res = IntStream.range(0, MAX).parallel().mapToDouble(Part4_1::compute).sum();
			System.out.println("Parallel total time: " + (System.currentTimeMillis() - strt) + " ms, result: " + res);
		});
		
		pool.shutdown();
		pool.awaitTermination(30	, TimeUnit.SECONDS);
	}

}
