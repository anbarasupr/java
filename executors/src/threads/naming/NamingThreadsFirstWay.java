package threads.naming;

import common.LoopTaskB;

public class NamingThreadsFirstWay {
	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main Thread starts here...");
		new Thread(new LoopTaskB()).start();

		new Thread(new LoopTaskB(), "XMen Thread").start();

		Thread t = new Thread(new LoopTaskB());
		t.setName("Amazing Spiderman Thread");
		t.start();

		Thread t1 = new Thread(new LoopTaskB());
		t1.start();
		t1.setName("Avenger Thread"); // not good way to set name after start
		System.out.println("[" + currentThreadName + "] Main Thread ends here...");
	}
}
