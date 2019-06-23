package logical;

/*

ascii code for numbers: 48 to 57
*/
public class RemoveCharactersFromNumberString {

	public static void main(String[] args) {

		String str = "98765ab78c76";
		StringBuilder sb = new StringBuilder(str);
		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) < 48 || sb.charAt(i) > 57) {
				sb.deleteCharAt(i);
				i--;
			}
		}
		System.out.println("Input: " + str + ", output:" + sb.toString());
	}

}
