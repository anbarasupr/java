package logical;

/*
Ex:			153 -> 1	5	3
				1*1*1		5*5*5		3*3*3
				1		+		125		+		27	=	153
				
Ex:			370	->	3*3*3	+	7*7*7	+	0*0*0	=	370
*/
public class Armstrong {

	public static void main(String[] args) {

		int n = 372, r = 0, sum = 0;
		int temp = n;

		while (n > 0) {
			r = n % 10;
			n = n / 10;
			sum = sum + r * r * r;
		}
		if (temp == sum) {
			System.out.println("The given input:" + temp + " is a armstrong number");
		} else {
			System.out.println("The given input:" + temp + " is not a armstrong number");
		}
	}

}
