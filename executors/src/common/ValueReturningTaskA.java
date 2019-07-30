package common;

import java.util.concurrent.TimeUnit;

public class ValueReturningTaskA implements Runnable {
	private static int count = 0;
	private String taskId;
	private int instanceNumber;

	private int a;
	private int b;
	private long sleepingTime;
	private int sum;
	private volatile boolean done = false;

	public ValueReturningTaskA(int a, int b, long sleepingTime) {
		this.a = a;
		this.b = b;
		this.sleepingTime = sleepingTime;
		this.instanceNumber = ++count;
		this.taskId = this.getClass().getSimpleName() + "-" + instanceNumber;
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

		done = true;
		synchronized (this) {
			System.out.println("^^^^^^^ [" + currentThreadName + "] <TASK-" + taskId + " > NOTIFYING ^^^^^^^");
			this.notifyAll();
		}
	}

	public int getSum() {
		if (!done) {
			synchronized (this) {
				try {
					System.out.println("___________ [" + Thread.currentThread().getName()
							+ "] Waiting for RESULT from <TASK-" + taskId + " > . . . ___________");
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			System.out.println("!!!!!!!!!!!!!!!!!! [" + Thread.currentThread().getName() + "] Woken Up for <TASK-"
					+ taskId + " > . . . !!!!!!!!!!!!!!!!!!");

		}
		return sum;
	}
}
