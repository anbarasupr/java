package parallelism;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

// To increase the no of threads in common pool using system config
public class Part3_2 {

	public static void main(String a[]) {
		//  // -> -Djava.util.concurrent.ForkJoinPool.common.parallelism=100
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "10");
		
		// find out the no of threads in fork join pool
		System.out.println("ForkJoinPool : "+ForkJoinPool.commonPool());
			
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
	
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
