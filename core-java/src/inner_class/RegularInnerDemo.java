package inner_class;

public class RegularInnerDemo {
	int x = 10;
	static int y = 20;

	class Inner {
		int x = 100;

		public void print() {
			int x = 1000;
			System.out.println("Inner method local x=" + x + ", inner  x=" +this. x + " , y=" + y + ", outer x="
					+ RegularInnerDemo.this.x);
		}
	}

	public void print() {
		System.out.println("Outer");
		new Inner().print();
	}

	public static void main(String[] args) {
//		RegularInnerDemo o = new RegularInnerDemo();
//		RegularInnerDemo.Inner i = o.new Inner();
//		i.print();
//
//		new RegularInnerDemo().new Inner().print();
		new RegularInnerDemo().print();
	}

}
