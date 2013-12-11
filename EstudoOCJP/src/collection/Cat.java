package collection;

public class Cat extends Animal {
	String raca;

	public Cat() {
		super("Dog");
	}

	public Cat(String r) {
		this();
		this.raca = r;
	}

	public void meaw() {
		System.out.println(raca + " is meawing.");
	}
}
