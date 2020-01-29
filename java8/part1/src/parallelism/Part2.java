package parallelism;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Part2 {

	public static int transform(int number)  {
		System.out.println("transform: " + number + ", thread: " + Thread.currentThread());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return number;
	}

	public static void println(int number) {
		System.out.println("println: " + number + ", thread: " + Thread.currentThread());
	}
	
	public static boolean check(int number) {
		System.out.println("check: " + number + ", thread: " + Thread.currentThread());
		return true; // just for test
	}
	
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		// below will not give the ordering due to parallelism
		/* numbers.stream()
		.parallel()
		.map(Sample::transform)
		//.sequential() // we cannot run parts of stream parallel and some parts of stream as sequential. It always apply the last one given in the stream
		.forEach(System.out::println);
		*/
		
		// below will  give the ordering even though it is parallelism
		//control the ordering
			/*numbers.stream()
			.parallel()
			.map(Sample::transform)
			.forEachOrdered(Sample::println);*/

			//we can apply parallel to filter also
			/*numbers.stream()
			.parallel()
			.filter(Sample::check)
			.map(Sample::transform)
			.forEachOrdered(Sample::println);*/
		
		
		//reduce - reduce the collection to  a single value
		//System.out.println(numbers.stream().reduce(0, (total,elem)->add(total,elem)));

		// starts with 20 - parallel
		//System.out.println(numbers.stream().parallel().reduce(20, (total,elem)->add(total,elem))); // this gives wrong value in parallel case
		System.out.println(numbers.stream().parallel().reduce(0, (total,elem)->add(total,elem))+20);
	
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
		 
		// to find out the cores
		System.out.println(Runtime.getRuntime().availableProcessors());
		// find out the no of threads in fork join pool
		System.out.println(ForkJoinPool.commonPool());
		
		
		 numbers.stream()
			.parallel()
			.map(Part2::transform)
			.forEach(e->{});		 
		 System.out.println("Done "+ Thread.currentThread());
		
	}

	private static int add(int total, int elem) {
		int result=total+elem;
		System.out.println("add total: "+total+", elem: "+elem+", result: "+result);
		return result;
	}

}
