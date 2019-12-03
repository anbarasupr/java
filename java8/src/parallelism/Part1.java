package parallelism;

import java.util.Arrays;
import java.util.List;

public class Part1 {

	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		// traditional way
		int result = 0;
		for (int e : numbers) {
			if (e % 2 == 0) {
				result += e * 2;
			}
		}
		System.out.println("result:" + result);

		// modern way - collection pipeline format
		System.out.println("NON-PARALLEL"+numbers.stream().filter(e -> e % 2 == 0).mapToInt(e -> e * 2).sum());

		// PAST - the structure of concurrent code was very different to the structure
		// of sequential code.
		// PRESENT - the structure of concurrent code is same to the structure of
		// sequential code.

		// System.out.println(numbers.parallelStream().filter(e -> e % 2 ==0).mapToInt(e -> e * 2).sum());
		System.out.println("PARALLEL"+numbers.stream().parallel().filter(e -> e % 2 == 0).mapToInt(e -> e * 2).sum());
		System.out.println("DONE");

	}

}
