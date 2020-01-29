package parallelism;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import common.Gender;
import common.Person;

/*
 * Completable Feature - Asynchronous execution with chains of data channel
 * 
 */
public class Part6_3 {

	public static int generateSupplyAsync() {
		System.out.println("doing work " + Thread.currentThread());

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 2;
	}

	public static void printIt(int value) {
		System.out.println("value : " + value + ", " + Thread.currentThread());
	}

	public static void main(String a[]) throws InterruptedException, ExecutionException {
		testSupplyAsync();
	}

	public static void testSupplyAsync() throws InterruptedException, ExecutionException {
		CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(Part6_3::generateSupplyAsync);
		cf.thenApply(data -> data * 2)
		.thenAccept(Part6_3::printIt);

		
		System.out.println("Get the value:"+cf.get());
		// cf.complete(100);

		System.out.println("Future IsDone: " + cf.isDone() + ", " + Thread.currentThread());
		System.out.println("Future IsCancelled: " + cf.isCancelled() + ", " + Thread.currentThread());
	}
}
