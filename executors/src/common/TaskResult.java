package common;

public class TaskResult<T, R> {

	private T taskId;
	private R result;

	public TaskResult(T taskId, R result) {
		this.taskId = taskId;
		this.result = result;
	}

	public T getTaskId() {
		return taskId;
	}

	public void setTaskId(T taskId) {
		this.taskId = taskId;
	}

	public R getResult() {
		return result;
	}

	public void setResult(R result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "TaskResult [taskId=" + taskId + ", result=" + result + "]";
	}	
	
	
}
