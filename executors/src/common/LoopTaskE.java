package common;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class LoopTaskE implements Runnable {
	private static int count = 0;
	private int instanceNumber;
	private String taskId;
	private volatile boolean shutdown = false;

	@Override
	public void run() {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("############ [" + currentThreadName + "] <TASK-" + taskId + " > STARTING ############");

		for (int i = 1;; i++) {
			System.out.println("[" + currentThreadName + "] <TASK- " + taskId + " >  " + i);
			try {
				 TimeUnit.MILLISECONDS.sleep((long) Math.random() * 3000);
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			synchronized (this) {
				if (shutdown) {
					break;
				}
			}
		}
		System.out.println("********* [" + currentThreadName + "] <TASK-" + taskId + " > DONE *********");
	}

	public LoopTaskE() {
		this.instanceNumber = ++count;
		this.taskId = this.getClass().getSimpleName() + instanceNumber;
	}

	public void cancel() {
		System.out.println("!!!!!!!!!!!!! [" + Thread.currentThread().getName() + "] <TASK-" + taskId
				+ " > SHUTTING DOWN !!!!!!!!!!!!!");
		synchronized (this) {
			this.shutdown = true;
		}
	}
}
