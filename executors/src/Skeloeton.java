

public class Skeloeton {
	public static void main(String[] args) {
		String currentThreadname = Thread.currentThread().getName();
		System.out.println("[" + currentThreadname + "] Main Thread starts here...");

		System.out.println("[" + currentThreadname + "] Main Thread ends here...");
	}
}
