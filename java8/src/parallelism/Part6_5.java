package parallelism;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import common.Gender;
import common.Person;

/*
 * Completable Feature - Asynchronous execution
 * There is no timeout in java8 and we need to go for java9.
 * 
 */
public class Part6_5 {

	public static int generateSupplyAsync() {
		System.out.println("doing work " + Thread.currentThread());

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 2;
	}

	public static void printIt(int value) {
		System.out.println("value : " + value + ", " + Thread.currentThread());
	}

	public static int processError(Throwable th) {
		System.out.println("processError : " + th.getMessage() + ", " + Thread.currentThread());
		throw new RuntimeException("oops! i dont handle");
	}

	public static int processAnotherError(Throwable th) {
		System.out.println("processAnotherError : " + th.getMessage() + ", " + Thread.currentThread());
		return 0;
	}

	public static void main(String a[]) throws InterruptedException, ExecutionException {
		// testSupplyAsyncTimeoutComplete();
		testSupplyAsyncTimeoutException
	}

	// succeed on timeout
	public static void testSupplyAsyncTimeoutComplete() throws InterruptedException, ExecutionException {
		CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(Part6_5::generateSupplyAsync);
		cf.exceptionally(Part6_5::processError)
				.thenApply(data -> data * 2)
				.exceptionally(Part6_5::processAnotherError)
				.thenAccept(Part6_5::printIt);
		
		cf.completeOnTimeout(100, 2, TimeUnit.SECONDS); // finish after 2 seconds

		System.out.println("Future IsDone: " + cf.isDone() + ", " + Thread.currentThread());
		System.out.println("Future IsCancelled: " + cf.isCancelled() + ", " + Thread.currentThread());
	}
	
	// fail on timeout
	public static void testSupplyAsyncTimeoutException() throws InterruptedException, ExecutionException {
		CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(Part6_5::generateSupplyAsync);
		cf.exceptionally(Part6_5::processError)
				.orTomeout(2,TimeUnit.SECONDS) // blow this after 2 seconds with timeout exception
				.thenApply(data -> data * 2)
				.exceptionally(Part6_5::processAnotherError)
				.thenAccept(Part6_5::printIt);
		
		System.out.println("Future IsDone: " + cf.isDone() + ", " + Thread.currentThread());
		System.out.println("Future IsCancelled: " + cf.isCancelled() + ", " + Thread.currentThread());
	}
}
