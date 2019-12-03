package parallelism;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import common.Gender;
import common.Person;

/*
 * Completable Feature - Asynchronous execution:
 * 
 * Drawbacks of future:
 * get() - blocked
 * 
 * Javascript Promises:
 * a promise have data and error channel. Also from the data channel, we can go to error channel and vice versa.
 * Example: 
 * 		func.then(f1).catch(e1).then(f2).catch(e2)
 * The same promise concept is nothing but completable feature and we can go back and forth between the chain/stage
 * 					 
 * 
 */
public class Part6_1 {

	public static int generateRunAsync() {
		System.out.println("doing work " + Thread.currentThread());

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 2;
	}

	public static int generateSupplyAsync() {
		System.out.println("doing work " + Thread.currentThread());

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 2;
	}

	public static void next() {
		System.out.println("next stage");
	}

	public static void main(String a[]) throws InterruptedException, ExecutionException {
		// testRunAsync();
		testSupplyAsync();
	}

	public static void testSupplyAsync() throws InterruptedException, ExecutionException {
		CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(Part6_1::generateSupplyAsync);
		System.out.println(
				"Get now the value without waiting but not blocked:" + cf.getNow(-1) + ", " + Thread.currentThread());
		System.out.println("Get the value but blocked:" + cf.get() + ", " + Thread.currentThread());
		System.out.println("DONE.. " + Thread.currentThread());
	}

	public static void testRunAsync() throws InterruptedException, ExecutionException {
		CompletableFuture<Void> cf = CompletableFuture.runAsync(Part6_1::generateRunAsync);
		Void valueIfAbsent = null;
		System.out.println("Get now the value without waiting but not blocked:" + cf.getNow(valueIfAbsent) + ", "
				+ Thread.currentThread());
		System.out.println("Get the value but blocked:" + cf.get() + ", " + Thread.currentThread());
		System.out.println("DONE.. " + Thread.currentThread());
	}

}
