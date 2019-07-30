package thread.returnValue;

import common.ResultListener;
import common.ValueReturningTaskB;
// Process task results in order of completion
public class ReturningValuesSecondWay {
	public static void main(String[] args) {
		System.out.println("[" + Thread.currentThread().getName() + "] Main Thread starts here...");

		ResultListener<Integer> listener = new SumObserver("task-1");
		ValueReturningTaskB task1 = new ValueReturningTaskB(2, 3, 1, listener);
		Thread t1 = new Thread(task1, "Thread-1");

		ValueReturningTaskB task2 = new ValueReturningTaskB(3, 4, 2, new SumObserver("task-2"));
		Thread t2 = new Thread(task2, "Thread-2");

		ValueReturningTaskB task3 = new ValueReturningTaskB(4, 5, 3, new SumObserver("task-3"));
		Thread t3 = new Thread(task3, "Thread-3");

		t1.start();
		t2.start();
		t3.start();

		System.out.println("[" + Thread.currentThread().getName() + "] Main Thread ends here...");
	}
}
