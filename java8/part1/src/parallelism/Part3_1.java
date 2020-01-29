package parallelism;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

// Find out no of threads in fork join common pool
public class Part3_1 {

	public static void main(String a[]) {

		
		/* 1. computation intensive operation
		 * # of Thread <= # of cores
		 * 
		 *  2. IO intensive operation like logging, serialization, send request to remote server, write to a database
		 *  0 < blocking factor < 1
		 *  
		 *  # of Thread <= # of cores/1 - blocking factor
		 *  
		 *   Note : if more IO operation, we can increase the threads count, if cpu intensive operation like computation, then no of threads should match with the no cores
		 * */
		 
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		// to find out the cores
		System.out.println("availableProcessors : "+Runtime.getRuntime().availableProcessors());
		// find out the no of threads in fork join pool
		System.out.println("ForkJoinPool : "+ForkJoinPool.commonPool());		// contains no of cores minus one thread
		
		/*
		 * Work Stealing: Here main thread is blocked.  If main thread is blocked, then it will take part of the common pool to steal the work and continue part of the common pool.
		 *So the main thread wont be idle and it is doing the common pool job when it is idle 
		 * */
		
		 numbers.stream()
			.parallel()
			.map(Part2::transform)
			.forEach(e->{});	
		 
		 System.out.println("DONE, thread: " + Thread.currentThread());		 
		 
	}

	public static int transform(int number) throws InterruptedException  {
		System.out.println("transform: " + number + ", thread: " + Thread.currentThread());
		Thread.sleep(1000);
		return number;
	}
}
