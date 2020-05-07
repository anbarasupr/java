package parallelism;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import common.Gender;
import common.Person;

/*
 * Completable Feature - Asynchronous execution with different pool
 * 
 */
public class Part6_2 {

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
		//testSupplyAsyncWithDifferentPool();
	}

	public static void testSupplyAsync() throws InterruptedException, ExecutionException {
		CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(Part6_2::generateSupplyAsync);
		Thread.sleep(3000);
		cf.thenAccept(Part6_2::printIt);
		System.out.println("DONE.. " + Thread.currentThread());
	}

	public static void testSupplyAsyncWithDifferentPool() throws InterruptedException, ExecutionException {
		ForkJoinPool pool = new ForkJoinPool(10);
		CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(Part6_2::generateSupplyAsync, pool);
		//Thread.sleep(3000);
		cf.thenAccept(Part6_2::printIt);
		//Thread.sleep(3000);
		System.out.println("DONE.. " + Thread.currentThread());
	}

}
