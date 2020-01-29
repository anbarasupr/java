package org.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/*
 *  Streams - process efficiently large amounts of data/collection
 	efficient in 2 ways - 1) In parallel to leverage the computing power of multi core cpu
 	 				  	  2) Pipelined, to avoid unncessary intermediary computation
 	 				  	  
 	Streams do not hold data.
 	
 	The call to the filter method is lazy. 
 	And all the methods of stream that return another stream are lazy and they are intermediary operations only and it doesnt do anything. These intermediary streams are just declarations.
 	Only the final/terminal operation trigger the data processing that a stream is connected to. Ex : forEach
 	
 	peek() is like forEach() and it is an intermediary operation that operation is only a declaration and it doesnt do any.
 	
 	both peek and foreach are consumers.
 	peek and filter are lazy. that is intermediary operation
 	
 	
 	Map / Filter / Reduce operations are evaluated in one pass over the data.
 */

public class FirstPredicates {

	public static void main(String[] args) {

		Stream<String> stream=Stream.of("one","two","three","four","five");
		
		Predicate<String> p2=Predicate.isEqual("two");
		Predicate<String> p3=Predicate.isEqual("three");

		Consumer<String> c=System.out::println;
		// stream.filter(p2.or(p3)).forEach(c);
		
		//stream.peek(c).filter(p2.or(p3)); // peek and filter are intermediary operation and they are just declarions and doesnt do any
		
		
		List<String> result=new ArrayList<String>();
		Consumer<String> finalOp=result::add;

		stream.peek(c).filter(p2.or(p3)).forEach(finalOp); 
		
		System.out.println("result:"+result);
		
	}

}
