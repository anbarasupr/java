package common;

import java.util.concurrent.TimeUnit;

public class LoopTaskB implements Runnable {
	private static int count = 0;
	private int instanceNumber;
	private String taskId;

	@Override
	public void run() {
		// String currentThreadName = Thread.currentThread().getName();
		System.out.println(
				"############ [" + Thread.currentThread().getName() + "] <TASK-" + taskId + " > STARTING ############");

		for (int i = 10; i > 0; i--) {
			System.out.println("[" + Thread.currentThread().getName() + "] <TASK- " + taskId + " > TICK TICK " + i);
			try {
				TimeUnit.SECONDS.sleep(1);
				// TimeUnit.MILLISECONDS.sleep((long) Math.random() * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out
				.println("********* [" + Thread.currentThread().getName() + "] <TASK-" + taskId + " > DONE *********");
	}

	public LoopTaskB() {
		this.instanceNumber = ++count;
		this.taskId = this.getClass().getSimpleName() + instanceNumber;
	}
}
