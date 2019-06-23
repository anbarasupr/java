package logical;
public class NumberTriangle {
	public static void main(String[] args) {
		int rows = 5;
		int cols = 7;
		for (int i = 1; i <= rows; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print(j);
			}
			System.out.println();
		}

		System.out.println("---------------------------");
		for (int i = 1; i <= rows; i++) {

			for (int k = i; k <= rows - 1; k++) {
				System.out.print(0);
			}
			for (int j = i; j >= 1; j--) {
				System.out.print(j);
			}
			System.out.println();
		}

		System.out.println("---------------------------");

		int odd = 1;
		for (int i = 1; i <= rows; i++) {
			for (int j = 1; j <= odd; j++) {
				System.out.print(j);
			}
			odd += 2;
			System.out.println();
		}

		System.out.println("---------------------------");

		odd = 1;
		for (int i = 1; i <= rows; i++) {
			
			for (int s= i; s <= rows-1; s++) {
				System.out.print(0);
			}
			
			int k = 0;
			for (int j = 1; j <= odd; j++) {
				if(j<=i) {
					k=k+1;
				}
				else {
					k=k-1;
				}
				System.out.print(k);
			}
			odd += 2;
			
//			for (int s= i; s <= rows-1; s++) {
//				System.out.print(0);
//			}
			System.out.println();
		}

	}
}
