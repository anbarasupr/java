package common;

import java.util.concurrent.TimeUnit;

public class ValueReturningTaskB implements Runnable {
	private static int count = 0;
	private String taskId;
	private int instanceNumber;

	private int a;
	private int b;
	private long sleepingTime;
	private int sum;

	private  ResultListener<Integer> listener;
	public ValueReturningTaskB(int a, int b, long sleepingTime, ResultListener<Integer> listener) {
		this.a = a;
		this.b = b;
		this.sleepingTime = sleepingTime;
		this.instanceNumber = ++count;
		this.taskId = this.getClass().getSimpleName() + "-" + instanceNumber;
		this.listener=listener;
	}

	@Override
	public void run() {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("#####  ####### [" + currentThreadName + "] <TASK-" + taskId + " > STARTING ############");
		System.out
				.println("[" + currentThreadName + "] <TASK-" + taskId + " > SLEEPING for " + sleepingTime + " seconds");

		try {
			TimeUnit.SECONDS.sleep(sleepingTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		sum = a + b;
		System.out.println("********* [" + currentThreadName + "] <TASK-" + taskId + " > DONE *********");
		listener.notifyResult(sum);
	}

}
