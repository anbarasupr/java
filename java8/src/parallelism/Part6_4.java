package parallelism;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import common.Gender;
import common.Person;

/*
 * Completable Feature - Asynchronous execution with chains of data channel & error channel
 * 
 */
public class Part6_4 {

	public static int generateSupplyAsync() {
		System.out.println("doing work " + Thread.currentThread());	
		 return 2;
	}
	
	public static int generateSupplyAsyncError() {
		System.out.println("doing work " + Thread.currentThread());	
		throw new RuntimeException("oops! something went wrong");
		// return 2;
	}

	public static void printIt(int value) {
		System.out.println("value : " + value + ", " + Thread.currentThread());
	}

	public static int processError(Throwable th) {
		System.out.println("processError : " + th.getMessage() + ", " + Thread.currentThread());
		throw new RuntimeException("oops! i dont handle");
// 		return 0;
	}

	public static int processAnotherError(Throwable th) {
		System.out.println("processAnotherError : " + th.getMessage() + ", " + Thread.currentThread());
 		return 0;
	}
	
	public static void main(String a[]) throws InterruptedException, ExecutionException {
		// testSupplyAsync();
		testSupplyAsyncError();
	}

	public static void testSupplyAsync() throws InterruptedException, ExecutionException {
		CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(Part6_4::generateSupplyAsync);
		cf.exceptionally(Part6_4::processError) // this will handle the error and return 0 which means it is again goes into data channel and execute next data channel with returned value
				.thenApply(data -> data * 2)
				.exceptionally(Part6_4::processAnotherError)
				.thenAccept(Part6_4::printIt);

		System.out.println("Future IsDone: " + cf.isDone() + ", " + Thread.currentThread());
		System.out.println("Future IsCancelled: " + cf.isCancelled() + ", " + Thread.currentThread());
	}
	
	public static void testSupplyAsyncError() throws InterruptedException, ExecutionException {
		CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(Part6_4::generateSupplyAsyncError);
		cf.exceptionally(Part6_4::processError) // this will handle the error and return 0 which means it is again goes into data channel and execute next data channel with returned value
				.thenApply(data -> data * 2)
				.exceptionally(Part6_4::processAnotherError)
				.thenAccept(Part6_4::printIt);

		System.out.println("Future IsDone: " + cf.isDone() + ", " + Thread.currentThread());
		System.out.println("Future IsCancelled: " + cf.isCancelled() + ", " + Thread.currentThread());
	}
}
