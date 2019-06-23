package logical;

/*
Prime nos are divisible by itself and 1.

*/
public class Prime {

	public static void main(String[] args) {

		int n = 29;
		boolean isPrime = true;

		for (int i = 2; i < n; i++) { // instead of i<n, we can use i<n/2 for best results or i<Math.sqrt(n)
			if (n % i == 0) { // if any of the no from 2 to less than the given no, then it is not a prime
				isPrime = false;
				break;
			}
		}

		if (isPrime) {
			System.out.println("The given input:" + n + " is a prime");
		} else {
			System.out.println("The given input:" + n + " is not a prime");
		}
	}

}
