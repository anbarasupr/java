package executors.returnValue;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import common.CalculationTaskA;
import common.CalculationTaskB;
import common.LoopTaskA;
import common.NamedThreadFactory;
import common.TaskResult;

//Process task results in order of completion
public class ReturningValuesUsingExecutorSecondWay {
	public static void main(String[] args) {
		System.out.println("[" + Thread.currentThread().getName() + "] Main Thread starts here...");

		ExecutorService execService = Executors.newCachedThreadPool(new NamedThreadFactory());
		CompletionService<TaskResult<String, Integer>> tasks = new ExecutorCompletionService<TaskResult<String, Integer>>(
				execService);

		tasks.submit(new CalculationTaskB(2, 3, 1));
		tasks.submit(new CalculationTaskB(4, 3, 2));
		tasks.submit(new CalculationTaskB(5, 5, 3));

		// CompletionService does not accept single Runnable argument so commenting
		// below
		// Future<?> result4 = tasks.submit(new LoopTaskA());
		tasks.submit(new LoopTaskA(), new TaskResult<String, Integer>("task-4", 999));

		execService.shutdown();

		for (int i = 0; i < 4; i++) {
			try {
				// Completion Service gives us the Future instance of next completed tasks. Here
				// we are unable to determine to which task the returned Future belong to.
				// The only way to identify the task here is to make the task itself to return
				// the identity with the result.
				System.out.println("@@@@@@ Result = " + tasks.take().get()); // this will give the next task result completed
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("[" + Thread.currentThread().getName() + "] Main Thread ends here...");
	}
}
