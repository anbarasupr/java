package thread.returnValue;

import common.ValueReturningTaskA;

public class ReturningValuesFirstWay {
	public static void main(String[] args) {
		System.out.println("[" + Thread.currentThread().getName() + "] Main Thread starts here...");
	
		ValueReturningTaskA task1=new ValueReturningTaskA(2, 3, 5);
		Thread t1=new Thread(task1,"Thread-1");
		
		ValueReturningTaskA task2=new ValueReturningTaskA(3, 4, 2);
		Thread t2=new Thread(task2,"Thread-2");
				
		ValueReturningTaskA task3=new ValueReturningTaskA(4,5, 3);
		Thread t3=new Thread(task3,"Thread-3");                                                                                                                                                                                        
		
		t1.start();
		t2.start();
		t3.start();
		
		System.out.println("Result-1 = "+task1.getSum());		
		System.out.println("Result-2 = "+task2.getSum());		
		System.out.println("Result-3 = "+task3.getSum());
		
		System.out.println("[" + Thread.currentThread().getName() + "] Main Thread ends here...");
	}
}
