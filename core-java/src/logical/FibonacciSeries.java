package logical;

/*
Ex: 	0	1 1 2 3 5 8 13 21 . . .  N

*/
public class FibonacciSeries {

	public static void main(String[] args) {
		int sum = 0, n = 50, a = 0, b = 1;
		System.out.print("0, 1, ");

		while (sum < 50) {
			sum = a + b;
			System.out.print(sum + ", ");
			a = b;
			b = sum;
		}
	}

}
