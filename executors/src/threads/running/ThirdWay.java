package threads.running;

import java.util.concurrent.TimeUnit;

public class ThirdWay {

	public static void main(String[] args) {
		System.out.println("Main Thread starts here...");
		new ThirdTask();
		System.out.println("Main Thread ends here...");
	}
}

class ThirdTask implements Runnable {
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

	public ThirdTask() {
		this.id = ++count;
		new Thread(this).start();
	}
}
