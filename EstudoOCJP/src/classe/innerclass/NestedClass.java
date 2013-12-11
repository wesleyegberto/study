package classe.innerclass;

public class NestedClass {
	private int myId = 120;
	private static int commonId = 111;

	private NestedClass() {

	}

	protected NestedClass(int x) {
		myId = x;
	}

	class InnerClass extends NestedClass {
		private int myId = 121;

		public InnerClass() {
			super(122);
		}

		public void get() {
			System.out.println("outer NestedClass ID: " + NestedClass.this.myId);
			System.out.println("super NestedClass ID: " + super.myId);
			System.out.println("InnerClass ID: " + this.myId);
		}
	}

	static class StaticInnerClass {
		public static boolean iCan() {
			return true;
		}

		public int get() {
			return commonId;
		}
	}

	public static void main(String[] args) {
		new NestedClass().new InnerClass().get();
	}
}

class OutterClass2 {
	public void doStuff() {
		NestedClass.InnerClass c;
		c = null;
		System.out.println(c);
	}

}
