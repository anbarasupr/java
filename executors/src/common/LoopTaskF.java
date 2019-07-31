package common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

// terminating non block threads
public class LoopTaskF implements Runnable {
	private static final int DATA_SIZE = 100000;
	private static int count = 0;
	private int instanceNumber;
	private String taskId;

	@Override
	public void run() {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("############ [" + currentThreadName + "] <TASK-" + taskId + " > STARTING ############");

		for (int i = 1;; i++) {
			System.out.println("[" + currentThreadName + "] <TASK- " + taskId + " >  " + i);
			doSomework();
			if (Thread.interrupted()) {
				System.out.println("[" + currentThreadName + "] <TASK-" + taskId + " > INTERRUPTED. CANCELLING . . . ");
				break;
			}
		}
		System.out.println("[" + currentThreadName + "] <TASK-" + taskId + " > Retrieving 'INTERRUPTED' status again : "
				+ Thread.interrupted());
		System.out.println("********* [" + currentThreadName + "] <TASK-" + taskId + " > DONE *********");
	}

	private void doSomework() {
		for (int i = 0; i < 2; i++) {
			Collections.sort(generateDataSet());
		}
	}

	private List<Integer> generateDataSet() {
		List<Integer> list = new ArrayList<Integer>();
		Random random = new Random();
		for (int i = 0; i < DATA_SIZE; i++) {
			list.add(random.nextInt(DATA_SIZE));
		}
		return list;
	}

	public LoopTaskF() {
		this.instanceNumber = ++count;
		this.taskId = this.getClass().getSimpleName() + instanceNumber;
	}
}
