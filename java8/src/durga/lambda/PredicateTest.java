package durga.lambda;

import java.util.Iterator;
import java.util.function.*;

public class PredicateTest {

	public static void main(String[] args) {

		int x[] = { 0, 5, 10, 15, 20, 25, 30 };
		Predicate<Integer> p = n -> n > 10;
		Predicate<Integer> p1 = n -> n % 2 == 0; // even
		System.out.println("PRINTING Number GREATER THAN 10");
		test(p, x);
		System.out.println("PRINTING EVEN");
		test(p1, x);
		
		System.out.println("PRINTING Numbers are not greater that 10");
		test(p.negate(), x);
		
		System.out.println("PRINTING Numbers GREATER THAN 10 and EVEN");
		test(p.and(p1),x);
		
		System.out.println("PRINTING Numbers GREATER THAN 10 or EVEN");
		test(p.or(p1),x);


	}

	public static void test(Predicate<Integer> p, int x[]) {
		for (int i : x) {
			System.out.println("NO: "+i + ", RESULT: "+p.test(i));
		}
	}
}
