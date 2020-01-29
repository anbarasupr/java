package org.firstlamda;

import java.util.function.Consumer;

public class MethodReference {

	public static void main(String[] args) {
		// consumer
		Consumer<String> consumer=s->System.out.println(s);
		
		// method reference
		Consumer<String> consumer1=System.out::println;
		
		consumer.accept("hello world");
		consumer1.accept("hello method reference");
		
	}

}
