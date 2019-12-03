package parallelism;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// To increase the no of threads in common pool using programmatically
public class Part3_3 {

	public static void main(String a[]) throws InterruptedException {
		
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
	
		
		/*
		 * It is not about where we are creating the stream. 
		 * It is about where are running the terminal operation
		 * forEach - is the output terminal operation here
		 * 
		 * 
		 * if we run below stream 1 which has the terminal operation (forEach) in main thread itself, 
		 * the main thread will use the common pool since there is no pool mentioned for the parallel stream
		 * 
		 *  If we run stream 2, even though the stream is created in main, but the terminal operation is running in fork join pool
		 *  by explicitly mentioning in the process().
		 */
		
		// stream 1
		 /* numbers.stream()
			.parallel()
			.map(Sample::transform)
			.forEach(e->{});	*/
	
		 // stream 2
		Stream stream= numbers.stream()
			.parallel()
			.map(Part2::transform);
		 process(stream);
		 System.out.println("DONE, thread: " + Thread.currentThread());	 
		 
	}

	private static void process(Stream stream) throws InterruptedException {
		// stream.forEach(e->{})
		ForkJoinPool pool=new ForkJoinPool(20);
		pool.submit(()->stream.forEach(e->{}));
		pool.shutdown();
		pool.awaitTermination(30, TimeUnit.SECONDS);
	}

	public static int transform(int number) throws InterruptedException  {
		System.out.println("transform: " + number + ", thread: " + Thread.currentThread());
		Thread.sleep(1000);
		return number;
	}
}
