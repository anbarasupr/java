package org.firstlamda;

import java.io.File;
import java.io.FileFilter;
import java.util.Iterator;

public class RunnableLambda {

	public static void main(String[] args) throws InterruptedException {
		// anonymous class
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 3; i++) {
					System.out.println("Hello from thread [" + Thread.currentThread().getName() + "]");
				}
			}
		};

		// lambda exp
		Runnable runnableLambda= ()->{
			for (int i = 0; i < 5; i++) {
				System.out.println("Hello Lambda from thread [" + Thread.currentThread().getName() + "]");
			}
		};
		
		Thread t1=new Thread(runnable,"anonymous thread");
		t1.start();
		
		Thread t2=new Thread(runnableLambda,"lambda thread");
		t2.start();
	}

}
