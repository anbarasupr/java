package threads.alive;

import java.util.concurrent.TimeUnit;

import common.LoopTaskB;

public class ThreadAliveCheck {
	public static void main(String[] args) throws InterruptedException {
		String currentThreadname = Thread.currentThread().getName();
		System.out.println("[" + currentThreadname + "] Main Thread starts here...");

		Thread t1 = new Thread(new LoopTaskB(), "MyThread-1");
		Thread t2 = new Thread(new LoopTaskB(), "MyThread-2");

		boolean t1isAlive = t1.isAlive();
		boolean t2isAlive = t2.isAlive();
		System.out
				.println("[" + currentThreadname + "] BEFORE STARTING: Is '" + t1.getName() + "' alive =" + t1isAlive);
		System.out
				.println("[" + currentThreadname + "] BEFORE STARTING: Is '" + t2.getName() + "' alive =" + t2isAlive);

		t1.start();
		t2.start();

		while (true) {
			TimeUnit.MILLISECONDS.sleep(600);
			t1isAlive = t1.isAlive();
			t2isAlive = t2.isAlive();
			System.out.println(
					"[" + currentThreadname + "] AFTER STARTING: Is '" + t1.getName() + "' alive =" + t1isAlive);
			System.out.println(
					"[" + currentThreadname + "] AFTER STARTING: Is '" + t2.getName() + "' alive =" + t2isAlive);

			if (!t1isAlive && !t2isAlive) {
				break;
			}
		}
		System.out.println("[" + currentThreadname + "] Main Thread ends here...");
	}
}
