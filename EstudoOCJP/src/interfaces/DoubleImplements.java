package interfaces;

interface A {
	public void doStuff();
}

class SomeA implements A {
	public void doStuff() {
		System.out.println("Stuff");
	}
}

public class DoubleImplements extends SomeA implements A {
	public static void main(String[] args) {
		new DoubleImplements().doStuff();
	}
}
