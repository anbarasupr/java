package logical;
public class Biggest {
	public static void main(String[] args) {

		int n[] = new int[10];

		n[0] = 1;
		n[1] = 2;
		n[2] = 1;
		n[3] = 3;
		n[4] = 7;
		n[5] = 6;
		n[6] = 6;
		n[7] = 9;
		n[8] = 7;
		n[9] = 5;

		bigs(n);

	}

	public static void small(int n[]) {
		for (int k = 0; k < n.length; k++) {
			System.out.print(n[k] + "\t");
		}
		System.out.println("\n--------------------------------------------------------------------------");
		int first = n[0];
		int second = Integer.MAX_VALUE;

		System.out.println("Initially first : \t" + first + ", \tsecond:" + second);

		for (int i = 0; i < n.length; i++) {
			System.out.print("--->pos:" + i + ", value:" + n[i] + ", \tfirst:" + first + ",\tsecond:" + second);

			if (n[i] < first) {
				second = first;
				first = n[i];
			}

			System.out.print(",\t\tcomparison:( " + second + " > " + n[i] + " < " + first + " )");

			if (n[i] < second && n[i] > first) {
				second = n[i];
			}
			System.out.println(", \t after update first:" + first + ",\tsecond:" + second);
		}
		System.out.println("first: \t" + first);
		System.out.println("second: \t" + second);

	}

	public static void big(int n[]) {
		for (int k = 0; k < n.length; k++) {
			System.out.print(n[k] + "\t");
		}
		System.out.println("\n--------------------------------------------------------------------------");
		int first = n[0];
		int second = Integer.MIN_VALUE;

		System.out.println("Initially first : \t" + first + ", \tsecond:" + second);

		for (int i = 0; i < n.length; i++) {
			System.out.print("--->pos:" + i + ", value:" + n[i] + ", \tfirst:" + first + ",\tsecond:" + second);

			if (n[i] > first) {
				second = first;
				first = n[i];
			}

			System.out.print(",\t\tcomparison:( " + second + " > " + n[i] + " < " + first + " )");

			if (n[i] > second && n[i] < first) {
				second = n[i];
			}
			System.out.println(", \t after update first:" + first + ",\tsecond:" + second);
		}
		System.out.println("first: \t" + first);
		System.out.println("second: \t" + second);

	}

	
	public static void bigs(int n[]) {
		for (int k = 0; k < n.length; k++) {
			System.out.print(n[k] + "\t");
		}
		System.out.println("\n--------------------------------------------------------------------------");
		int first = n[0];
		int second = Integer.MIN_VALUE;
		int third = Integer.MIN_VALUE;

		System.out.println("Initially first : \t" + first + ", \tsecond:" + second + ", \third:" + third);

		for (int i = 0; i < n.length; i++) {

			if (n[i] > first) {
				third = second;
				second = first;
				first = n[i];
			}
			if (n[i] > second && n[i] < first) {
				second = n[i];
			}
			if (n[i] > third && n[i] < second) {
				third = n[i];
			}
		}
		System.out.println("first: \t" + first);
		System.out.println("second: \t" + second);
		System.out.println("third: \t" + third);
		
	}

	public static void sort(int n[]) {
		// sort
		int t = 0;
		for (int k = 0; k < n.length; k++) {
			System.out.print(n[k] + "\t");
		}
		System.out.println("\n--------------------------------------------------------------------------");

		boolean isSwapped = false;
		for (int i = 0; i < n.length; i++) {
			for (int j = i + 1; j < n.length; j++) {
				if (n[i] > n[j]) {
					t = n[i];
					n[i] = n[j];
					n[j] = t;
					isSwapped = true;
				}
				for (int k = 0; k < n.length; k++) {
					System.out.print(n[k] + "\t");
				}
				System.out.print(isSwapped + ", \tp:" + i + "\n");
				isSwapped = false;
			}
			System.out.println("--------------------------------------------------------------------------");
		}
	}
}
