package common;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CalculationTaskB implements Callable<TaskResult<String, Integer>> {
	private static int count = 0;
	private String taskId;
	private int instanceNumber;

	private int a;
	private int b;
	private long sleepingTime;

	public CalculationTaskB(int a, int b, long sleepingTime) {
		this.a = a;
		this.b = b;
		this.sleepingTime = sleepingTime;
		this.instanceNumber = ++count;
		this.taskId = this.getClass().getSimpleName() + "-" + instanceNumber;
	}

	@Override
	public TaskResult<String, Integer> call() throws InterruptedException {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("#####  ####### [" + currentThreadName + "] <TASK-" + taskId + " > STARTING ############");
		System.out.println(
				"[" + currentThreadName + "] <TASK-" + taskId + " > SLEEPING for " + sleepingTime + " seconds");

		TimeUnit.SECONDS.sleep(sleepingTime);

		System.out.println("********* [" + currentThreadName + "] <TASK-" + taskId + " > DONE *********");
		return new TaskResult<String, Integer>(taskId, a + b);
	}

}
