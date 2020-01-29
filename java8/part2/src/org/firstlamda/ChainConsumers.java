package org.firstlamda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ChainConsumers {

	public static void main(String[] args) {
		List<String> strings=Arrays.asList("one","two","three","four","five");

		List<String> result=new ArrayList<String>();
		
		Consumer<String> c=System.out::println;
		Consumer<String> c1=result::add;
		
		System.out.println("initial size of result:"+result.size());
		strings.forEach(c.andThen(c1)); // print and add it to result list
		System.out.println("size of result:"+result.size());
		
	}

}
