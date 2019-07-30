package threads.running;

import java.util.concurrent.TimeUnit;

public class FirstWay {

	public static void main(String[] args) {
		System.out.println("Main Thread starts here...");
		new FirstTask();
		System.out.println("Main Thread ends here...");
	}
}

class FirstTask extends Thread {
	private static int count = 0;
	private int id;

	@Override
	public void run() {
		for (int i = 10; i > 0; i--) {
			System.out.println("< " + id + " > TICK TICK " + i);
			try {
				TimeUnit.MICROSECONDS.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public FirstTask() {
		this.id = ++count;
		this.start();
	}
}
