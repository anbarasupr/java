package logical;

import java.util.Scanner;

/*


*/
public class CountVowels {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		String str = in.nextLine();
		char[] chars = str.toCharArray();
		int vowelsCount = 0, others = 0;
		for (char c : chars) {
			switch (c) {
			case 'a':
			case 'e':
			case 'i':
			case 'o':
			case 'u':
				vowelsCount++;
				break;
			default:
				others++;
				break;
			}
		}

		System.out.println(
				"Total Vowels Count:" + vowelsCount + ", others count:" + others + " in the given string :" + str);
	}

}
