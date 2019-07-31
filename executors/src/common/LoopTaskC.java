package common;

import java.util.concurrent.TimeUnit;

public class LoopTaskC implements Runnable {
	private static int count = 0;
	private int instanceNumber;
	private String taskId;
	private long sleepingTime;

	@Override
	public void run() {
		boolean isDaemonThread = Thread.currentThread().isDaemon();
		String threadType = isDaemonThread ? "DAEMON" : "USER";
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("############ [" + currentThreadName + ", " + threadType + "] <TASK-" + taskId
				+ " > STARTING ############");

		for (int i = 10; i > 0; i--) {
			System.out
					.println("[" + currentThreadName + ", " + threadType + "] <TASK- " + taskId + " > TICK TICK " + i);
			try {
				TimeUnit.MILLISECONDS.sleep(sleepingTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(
				"********* [" + currentThreadName + ", " + threadType + "] <TASK-" + taskId + " > DONE *********");
	}

	public LoopTaskC(long sleepingTime) {
		this.instanceNumber = ++count;
		this.taskId = this.getClass().getSimpleName() + instanceNumber;
		this.sleepingTime = sleepingTime;
	}
}
