package threads.running;

import java.util.concurrent.TimeUnit;

public class FourthWay {

	public static void main(String[] args) {
		System.out.println("Main Thread starts here...");
		new Thread(new FourthTask()).start();
		System.out.println("Main Thread ends here...");
	}
}

class FourthTask implements Runnable {
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

	public FourthTask() {
		this.id = ++count;		
	}
}
