package org.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/*
 * 
 * Reductions are terminal operation. They do not return stream and it triggers the processing of data
 * reductions are like sql aggregations
 * min, max, count, sum are reductions
 * Boolean reductions - allMatch(), noneMatch(), anyMatch()
 * Optional Reductions - findFirst(), findAny()
 * 
 * There is another type of reductions called mutuable reductions.
 * Instead of aggregating elements, this reduction put them in a container
 */
public class Reduction {

	public static void main(String[] args) {

		List<Integer> list = Arrays.asList(10, 10, 10);

		// Integer result = list.stream().reduce(0, (i1, i2) -> i1 + i2);
		Integer result = list.stream().reduce(0, Integer::sum); // zero is the identity element for sum
		System.out.println("sum result :" + result);
		
		list = Arrays.asList();
		//list = Arrays.asList(10, 10, 10);
		Optional<Integer> optionalResult = list.stream().reduce(Integer::max); // do not pass identity element to max
		System.out.println("max result :" + optionalResult);
		
	}

}
