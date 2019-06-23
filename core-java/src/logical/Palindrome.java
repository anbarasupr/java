package logical;

/*
	input and reverse of given input number should be same.
	Ex : 121 and reverse of 121 are same
*/
public class Palindrome {

	public static void main(String[] args) {
		int n = 121, rem, reverse = 0;
		int temp = n;
		while (n > 0) {
			rem = n % 10; // rem=3
			n = n / 10; // n=12
			reverse = reverse * 10 + rem; // 0*10+3=3, 3*10+2=32, 32*10+1=321
		}
		System.out.println("Input:" + temp + ", Reverse:" + reverse);

		if (temp == reverse) {
			System.out.println("The given input:" + temp + " is a palindrome");
		} else {
			System.out.println("The given input:" + temp + " is not a palindrome");
		}
	}

}
