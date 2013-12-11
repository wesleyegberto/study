package collection;

public class Dog extends Animal {
	String raca;

	public Dog() {
		super("Dog");
	}

	public Dog(String r) {
		this();
		this.raca = r;
	}

	public void bark() {
		System.out.println(raca + " is barking.");
	}
}
